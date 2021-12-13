package com.untels.redcetario.model

import java.io.Serializable

data class Material(
    val id: Int,
    val nombre: String,
    val descripcion: String
) : Serializable