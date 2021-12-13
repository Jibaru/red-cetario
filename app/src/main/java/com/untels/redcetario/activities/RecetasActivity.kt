package com.untels.redcetario.activities

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.untels.redcetario.R
import com.untels.redcetario.adapter.RecetaCabeceraAdapter
import com.untels.redcetario.databinding.ActivityRecetasBinding
import com.untels.redcetario.model.RecetaCabecera
import com.untels.redcetario.service.ServiceManager

class RecetasActivity : AppCompatActivity() {
    private lateinit var adaptador: RecetaCabeceraAdapter
    private lateinit var binding: ActivityRecetasBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecetasBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupAdapter()
        cargarRecetas()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.item_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.perfil -> {
                val intent = Intent(this, PerfilActivity::class.java)
                startActivity(intent)
                true
            }
            R.id.inicioSesion -> {
                val intent = Intent(this, InicioSesionActivity::class.java)
                startActivity(intent)
                true
            }
            R.id.notificaciones -> {
                val intent = Intent(this, NotificacionesActivity::class.java)
                startActivity(intent)
                true
            }
            R.id.cerrarSesion -> {
                // TODO: Cerrar sesión
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun cargarRecetas() {
        val thread = Thread {
            val recetas: List<RecetaCabecera> = ServiceManager
                .getRecetaService()
                .obtenerTodos()
            runOnUiThread {
                if (recetas.isEmpty()) {
                    Toast.makeText(
                        this,
                        "No hay recetas para mostrar",
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    adaptador.updateList(recetas)
                }
            }
        }
        thread.start();
    }

    private fun setupAdapter() {
        adaptador = RecetaCabeceraAdapter(context = this)
        binding.rvRecetas.adapter = adaptador
        binding.rvRecetas.layoutManager = LinearLayoutManager(this)
    }
}