package com.untels.redcetario.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.untels.redcetario.R
import com.untels.redcetario.databinding.ActivityInicioSesionBinding
import com.untels.redcetario.databinding.ActivityRecetasBinding
import kotlinx.android.synthetic.main.activity_inicio_sesion.*

class InicioSesionActivity : AppCompatActivity() {
    private lateinit var binding: ActivityInicioSesionBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // setContentView(R.layout.activity_inicio_sesion)
        binding = ActivityInicioSesionBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnRegistro.setOnClickListener {
            val intent = Intent(this, RegistroActivity::class.java)
            startActivity(intent)
        }
    }
}