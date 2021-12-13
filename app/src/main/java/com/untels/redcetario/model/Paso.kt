package com.untels.redcetario.model

import java.io.Serializable

data class Paso(
    val id: Int,
    val numeroOrden: Int,
    val contenido: String
) : Serializable