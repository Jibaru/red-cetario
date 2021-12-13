package com.untels.redcetario.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.untels.redcetario.databinding.ActivityPerfilBinding
import com.untels.redcetario.service.ServiceManager

class PerfilActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPerfilBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPerfilBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnGuardarPerfil.setOnClickListener {
            // TODO: Guardar perfil y actualizar activity
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