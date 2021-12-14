package com.untels.redcetario.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.untels.redcetario.databinding.ActivityPerfilBinding
import com.untels.redcetario.model.Cliente
import com.untels.redcetario.service.ServiceManager
import com.untels.redcetario.utils.CargadorUtil

class PerfilActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPerfilBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPerfilBinding.inflate(layoutInflater)
        setContentView(binding.root)
        title = "Perfil"

        binding.btnGuardarPerfil.setOnClickListener {
            if (ServiceManager.getAutenticacionService().isAutenticado()) {
                val idCliente = ServiceManager.getAutenticacionService().getCliente()!!.id
                val cliente = Cliente(
                    id = idCliente,
                    nombre = binding.txtNombrePrefil.text.toString(),
                    apePaterno = binding.txtApePaternoPerfil.text.toString(),
                    apeMaterno = binding.txtApeMaternoPerfil.text.toString(),
                    correoElectronico = binding.txtCorreoElectronicoPerfil.text.toString()
                )
                CargadorUtil.showDialog(this, false)
                Thread {
                    val resultado = ServiceManager.getClienteService().actualizar(cliente)

                    runOnUiThread {
                        if (resultado) {
                            ServiceManager.getAutenticacionService().setCliente(cliente)
                            Toast.makeText(
                                this,
                                "Perfil actualizado",
                                Toast.LENGTH_SHORT
                            ).show()
                            finish()
                            startActivity(intent)
                        } else {
                            Toast.makeText(
                                this,
                                "No se pudo actualizar",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                        CargadorUtil.hideDialog()
                    }
                }.start()
            }
        }

        setupCliente()
    }

    private fun setupCliente() {
        ServiceManager.getAutenticacionService().getCliente()?.let {
            binding.tvNombreCompleto.text = (it.nombre + " " + it.apePaterno + " " + it.apeMaterno)
            binding.txtNombrePrefil.setText(it.nombre)
            binding.txtApePaternoPerfil.setText(it.apePaterno)
            binding.txtApeMaternoPerfil.setText(it.apeMaterno)
            binding.txtCorreoElectronicoPerfil.setText(it.correoElectronico)
        }
    }
}