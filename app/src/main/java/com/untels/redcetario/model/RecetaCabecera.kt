package com.untels.redcetario.model

import java.io.Serializable

data class RecetaCabecera (
    val id: Int,
    val titulo: String,
    val urlImagen: String,
    val totalFavoritos: Int,
    val tiempoPrep: Int,
    val tiempoCoccion: Int,
    val cocina: String,
    val cliente: Cliente
) : Serializable