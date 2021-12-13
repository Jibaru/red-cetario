package com.untels.redcetario.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.untels.redcetario.adapter.RecetaCabeceraAdapter
import com.untels.redcetario.databinding.ActivityRecetasBinding
import com.untels.redcetario.response.RecetasResponse
import com.untels.redcetario.service.RecetaService
import okhttp3.Call
import okhttp3.Callback
import okhttp3.Response
import java.io.IOException
import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder

class RecetasActivity : AppCompatActivity() {
    private lateinit var adaptador: RecetaCabeceraAdapter
    private lateinit var binding: ActivityRecetasBinding
    private val recetaService: RecetaService = RecetaService()
    private val gson = GsonBuilder()
        .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
        .create()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecetasBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupAdapter()
        cargarRecetas()
    }

    private fun cargarRecetas() {
        val that = this
        this.recetaService.obtenerTodos(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                runOnUiThread {
                    Toast.makeText(
                        that,
                        "Intente de nuevo",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
            override fun onResponse(call: Call, response: Response) {
                val jsonString = response.body()?.string()
                val recetasResponse: RecetasResponse = gson.fromJson(jsonString, RecetasResponse::class.java)

                runOnUiThread {
                    if (recetasResponse.recetas.size == 0) {
                        Toast.makeText(
                            that,
                            "No hay recetas para mostrar",
                            Toast.LENGTH_SHORT
                        ).show()
                    } else {
                        adaptador.updateList(recetasResponse.recetas)
                    }
                }
            }
        })
    }

    private fun setupAdapter() {
        adaptador = RecetaCabeceraAdapter(context = this)
        binding.rvRecetas.adapter = adaptador
        binding.rvRecetas.layoutManager = LinearLayoutManager(this)
    }
}