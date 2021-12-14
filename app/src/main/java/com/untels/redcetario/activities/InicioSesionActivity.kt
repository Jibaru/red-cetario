package com.untels.redcetario.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.untels.redcetario.databinding.ActivityInicioSesionBinding
import com.untels.redcetario.service.ServiceManager
import com.untels.redcetario.utils.CargadorUtil
import kotlinx.android.synthetic.main.activity_inicio_sesion.*

class InicioSesionActivity : AppCompatActivity() {
    private lateinit var binding: ActivityInicioSesionBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInicioSesionBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
        binding.btnRegistro.setOnClickListener {
            val intent = Intent(this, RegistroActivity::class.java)
            startActivity(intent)
        }

        binding.btnIniciarSesion.setOnClickListener {
            val correoElectronico = binding.txtCorreoElectronico.text.toString()
            val contrasenia = binding.txtContrasenia.text.toString()

            if (correoElectronico.isBlank() ||
                contrasenia.isBlank()
            ) {
                Toast.makeText(
                    this,
                    "No puede existir campos vacíos",
                    Toast.LENGTH_SHORT
                ).show()
                return@setOnClickListener;
            }
            CargadorUtil.showDialog(this, false)
            Thread {
                val respuesta = ServiceManager
                    .getClienteService()
                    .iniciarSesion(correoElectronico, contrasenia)

                runOnUiThread {
                    if (respuesta.ok) {
                        respuesta.cliente?.let { it1 ->
                            ServiceManager.getAutenticacionService().setCliente(
                                it1
                            )
                            Toast.makeText(
                                this,
                                "Usuario ${it1.nombre} autenticado",
                                Toast.LENGTH_SHORT
                            ).show()
                            val intent = Intent(this, RecetasActivity::class.java)
                            intent.addFlags(
                                Intent.FLAG_ACTIVITY_CLEAR_TOP or
                                        Intent.FLAG_ACTIVITY_NEW_TASK or
                                        Intent.FLAG_ACTIVITY_CLEAR_TASK
                            )
                            startActivity(intent)
                        }
                    } else {
                        Toast.makeText(
                            this,
                            respuesta.mensaje,
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                    CargadorUtil.hideDialog()
                }
            }.start()
        }
    }
}