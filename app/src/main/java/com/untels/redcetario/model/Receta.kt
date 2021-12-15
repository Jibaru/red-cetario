package com.untels.redcetario.model

import java.io.Serializable

data class Receta(
    val id: Int,
    val titulo: String,
    val descripcion: String,
    val tiempoPrep: Int,
    val tiempoCoccion: Int,
    val urlImagen: String,
    val cocina: String,
    val totalFavoritos: Int,
    val totalComentarios: Int,
    val tips: String?,
    val dificultad: String,
    val calorias: String,
    val cliente: Cliente,
    val comentarios: List<Comentario>,
    val ingredientes: List<Ingrediente>,
    val materiales: List<Material>,
    val pasos: List<Paso>,
    val clientesFavoritos: List<ClienteId>
) : Serializable