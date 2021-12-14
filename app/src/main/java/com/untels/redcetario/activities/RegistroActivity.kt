package com.untels.redcetario.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.untels.redcetario.databinding.ActivityRegistroBinding
import com.untels.redcetario.model.Cliente
import com.untels.redcetario.service.ServiceManager
import com.untels.redcetario.utils.CargadorUtil
import kotlinx.android.synthetic.main.activity_registro.*

class RegistroActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegistroBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistroBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
        binding.btnInicioSesion.setOnClickListener {
            val intent = Intent(this, InicioSesionActivity::class.java)
            startActivity(intent)
        }
        binding.btnRegistrar.setOnClickListener {
            val nombre = binding.txtNombre.text.toString()
            val apePaterno = binding.txtApeParterno.text.toString()
            val apeMaterno = binding.txtApeMaterno.text.toString()
            val correoElectronico = binding.txtCorreo.text.toString()
            val contrasenia = binding.txtContra.text.toString()
            val contraseniaRepe = binding.txtContraRepe.text.toString()

            if (nombre.isBlank() ||
                apePaterno.isBlank() ||
                apeMaterno.isBlank() ||
                correoElectronico.isBlank() ||
                contrasenia.isBlank() ||
                contraseniaRepe.isBlank()) {
                Toast.makeText(
                    this,
                    "No puede existir campos vacíos",
                    Toast.LENGTH_SHORT
                ).show()
                return@setOnClickListener;
            }

            if (!isLettersOrDigits(contrasenia)) {
                Toast.makeText(
                    this,
                    "Contraseña debe poseer números y letras",
                    Toast.LENGTH_SHORT
                ).show()
                return@setOnClickListener;
            }

            if (contrasenia !=contraseniaRepe) {
                Toast.makeText(
                    this,
                    "Contraseña repetida diferente",
                    Toast.LENGTH_SHORT
                ).show()
                return@setOnClickListener;
            }
            val cliente = Cliente(
                -1,
                nombre,
                apePaterno,
                apeMaterno,
                correoElectronico,
            )
            CargadorUtil.showDialog(this, false)
            Thread {
                val registroResponse = ServiceManager
                    .getClienteService()
                    .registro(cliente, contrasenia)

                runOnUiThread {
                    if (registroResponse.ok) {
                        Toast.makeText(
                            this,
                            "Registro correcto",
                            Toast.LENGTH_SHORT
                        ).show()
                    } else {
                        Toast.makeText(
                            this,
                            "Registro erróneo",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                    CargadorUtil.hideDialog()
                }
            }.start()

        }

    }

    private fun isLettersOrDigits(chars: String): Boolean {
        return chars.none { it !in 'A'..'Z' && it !in 'a'..'z' && it !in '0'..'9'  }
    }
}