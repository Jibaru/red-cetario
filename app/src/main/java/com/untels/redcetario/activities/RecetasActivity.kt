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
import com.untels.redcetario.utils.CargadorUtil

class RecetasActivity : AppCompatActivity() {
    private lateinit var adaptador: RecetaCabeceraAdapter
    private lateinit var binding: ActivityRecetasBinding
    private lateinit var recetas: List<RecetaCabecera>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecetasBinding.inflate(layoutInflater)
        setContentView(binding.root)
        title = "Red-Cetario"
        setupAdapter()
        cargarRecetas()

        binding.btnBuscar.setOnClickListener {
            val busqueda = binding.edtBusqueda.text.toString().trim().lowercase()
            val recetasFiltradas = this.recetas.filter { it.titulo.lowercase().contains(busqueda) }
            adaptador.updateList(recetasFiltradas)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.item_menu, menu)
        val itemsDesactivar = mutableListOf<Int>()
        if (ServiceManager.getAutenticacionService().isAutenticado()) {
            itemsDesactivar.addAll(listOf(R.id.inicioSesion))
        } else {
            itemsDesactivar.addAll(listOf(R.id.perfil, R.id.cerrarSesion, R.id.notificaciones))
        }

        for (i: Int in itemsDesactivar) {
            val menuItem = menu?.findItem(i)
            menuItem?.isVisible = false
        }
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
                ServiceManager.getAutenticacionService().limpiarCliente()
                val intent = Intent(this, RecetasActivity::class.java)
                intent.addFlags(
                    Intent.FLAG_ACTIVITY_CLEAR_TOP or
                            Intent.FLAG_ACTIVITY_NEW_TASK or
                            Intent.FLAG_ACTIVITY_CLEAR_TASK
                )
                startActivity(intent)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun cargarRecetas() {
        CargadorUtil.showDialog(this, false)
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
                    this.recetas = recetas
                    adaptador.updateList(recetas)
                }
                CargadorUtil.hideDialog()
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