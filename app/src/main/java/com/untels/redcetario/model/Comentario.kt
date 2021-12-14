package com.untels.redcetario.model

import java.io.Serializable

data class Comentario(
    val id: Int,
    val descripcion: String,
    val createdAt: String
) : Serializable