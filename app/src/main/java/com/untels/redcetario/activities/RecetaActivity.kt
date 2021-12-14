package com.untels.redcetario.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.squareup.picasso.Picasso
import com.untels.redcetario.adapter.ComentarioAdapter
import com.untels.redcetario.adapter.IngredienteAdapter
import com.untels.redcetario.adapter.MaterialAdapter
import com.untels.redcetario.adapter.PasoAdapter
import com.untels.redcetario.databinding.ActivityRecetaBinding
import com.untels.redcetario.model.Receta
import com.untels.redcetario.service.ServiceManager
import com.untels.redcetario.utils.CargadorUtil

class RecetaActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRecetaBinding
    private lateinit var adaptadorIngredientes: IngredienteAdapter
    private lateinit var adaptadorMateriales: MaterialAdapter
    private lateinit var adaptadorPasos: PasoAdapter
    private lateinit var adaptadorComentarios: ComentarioAdapter
    private lateinit var receta: Receta

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecetaBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
        setupIngredientesAdapter()
        setupMaterialesAdapter()
        setupPasosAdapter()
        setupComentariosAdapter()

        val bundle = intent.extras
        bundle?.let {
            val idReceta: Int = it.getInt("id_receta")
            cargarReceta(idReceta)
        }

        binding.btnComentar.setOnClickListener {
            if (ServiceManager.getAutenticacionService().isAutenticado()) {
                CargadorUtil.showDialog(this, false)
                Thread {
                    val idCliente = ServiceManager.getAutenticacionService().getCliente()!!.id
                    val comentario = binding.txtComentario.text.toString()
                    val resultado = ServiceManager
                        .getRecetaService()
                        .comentar(receta.id, idCliente, comentario)

                    runOnUiThread {
                        if (resultado) {
                            Toast.makeText(
                                this,
                                "Comentado",
                                Toast.LENGTH_SHORT
                            ).show()
                            finish()
                            startActivity(intent)
                        }
                        CargadorUtil.hideDialog()
                    }
                }.start()
            } else {
                Toast.makeText(
                    this,
                    "Necesita estar autenticado para comentar",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    fun cargarReceta(idReceta: Int) {
        CargadorUtil.showDialog(this, false)
        Thread {
            val receta: Receta = ServiceManager
                .getRecetaService()
                .obtener(idReceta)
            runOnUiThread {
                this.receta = receta
                binding.tvTituloR.text = receta.titulo
                binding.tvCocinaR.text = receta.cocina
                binding.tvAutorR.text = (receta.cliente.nombre + " " + receta.cliente.apePaterno)
                binding.tvDificultadR.text = receta.dificultad
                val tiempoTotal = receta.tiempoCoccion + receta.tiempoPrep
                binding.tvDuracionR.text = "$tiempoTotal minutos"

                if (receta.tips == null) {
                    binding.tvTipsR.text = "Ninguno"
                } else {
                    binding.tvTipsR.text = receta.tips
                }

                binding.tvCaloriasR.text = receta.calorias

                binding.tvCantidadFavoritosR.text = receta.totalFavoritos.toString() + " ❤"
                Picasso.get()
                    .load(receta.urlImagen)
                    .into(binding.tvImagenR)
                adaptadorIngredientes.updateList(receta.ingredientes)
                adaptadorMateriales.updateList(receta.materiales)
                val pasos = receta.pasos.sortedBy { it.numeroOrden }
                adaptadorPasos.updateList(pasos)
                adaptadorComentarios.updateList(receta.comentarios)
                CargadorUtil.hideDialog()
            }
        }.start()
    }

    private fun setupIngredientesAdapter() {
        adaptadorIngredientes = IngredienteAdapter(context = this)
        binding.rvIngredientes.adapter = adaptadorIngredientes
        binding.rvIngredientes.layoutManager = LinearLayoutManager(this)
    }

    private fun setupMaterialesAdapter() {
        adaptadorMateriales = MaterialAdapter(context = this)
        binding.rvMateriales.adapter = adaptadorMateriales
        binding.rvMateriales.layoutManager = LinearLayoutManager(this)
    }

    private fun setupPasosAdapter() {
        adaptadorPasos = PasoAdapter(context = this)
        binding.rvPasos.adapter = adaptadorPasos
        binding.rvPasos.layoutManager = LinearLayoutManager(this)
    }

    private fun setupComentariosAdapter() {
        adaptadorComentarios = ComentarioAdapter(context = this)
        binding.rvComentarios.adapter = adaptadorComentarios
        binding.rvComentarios.layoutManager = LinearLayoutManager(this)
    }
}