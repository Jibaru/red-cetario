package com.untels.redcetario.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.untels.redcetario.R
import com.untels.redcetario.adapter.NotificacionAdapter
import com.untels.redcetario.databinding.ActivityNotificacionesBinding
import com.untels.redcetario.model.Notificacion
import com.untels.redcetario.service.ServiceManager

class NotificacionesActivity : AppCompatActivity() {
    private lateinit var adaptador: NotificacionAdapter
    private lateinit var binding: ActivityNotificacionesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNotificacionesBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupAdapter()
        cargarNotificaciones()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.item_menu_notificaciones, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.limpiarNotificaciones -> {
                // TODO: Llamar al servicio de notificaciones y limpiat por idCliente
                if (ServiceManager.getAutenticacionService().isAutenticado()) {
                    val idCliente = ServiceManager.getAutenticacionService().getCliente()!!.id

                    Thread {
                        val resultado = ServiceManager.getNotificacionService().eliminarTodos(idCliente)

                        runOnUiThread {
                            if (resultado) {
                                Toast.makeText(
                                    this,
                                    "Notificaciones eliminadas",
                                    Toast.LENGTH_SHORT
                                ).show()
                                finish()
                                startActivity(intent)
                            }
                        }
                    }.start()
                }
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun cargarNotificaciones() {
        ServiceManager.getAutenticacionService().getCliente()?.let {
            Thread {
                val notificaciones: List<Notificacion> = ServiceManager
                    .getNotificacionService()
                    .obtenerTodos(it.id)
                runOnUiThread {
                    if (notificaciones.isEmpty()) {
                        Toast.makeText(
                            this,
                            "No hay notificaciones para mostrar",
                            Toast.LENGTH_SHORT
                        ).show()
                    } else {
                        adaptador.updateList(notificaciones)
                    }
                }
            }.start()
        }
    }

    private fun setupAdapter() {
        adaptador = NotificacionAdapter(context = this)
        binding.rvNotificaciones.adapter = adaptador
        binding.rvNotificaciones.layoutManager = LinearLayoutManager(this)
    }
}