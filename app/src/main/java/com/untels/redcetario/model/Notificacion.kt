package com.untels.redcetario.model

import java.io.Serializable

data class Notificacion(
    val id: Int,
    val titulo: String,
    val descripcion: String,
    val fechaEnvio: String,
    val fechaVisto: String?
) : Serializable