package com.untels.redcetario.model

import java.io.Serializable

data class Cliente(
    val id: Int,
    val nombre: String,
    val apePaterno: String,
    val apeMaterno: String,
    val correoElectronico: String
) : Serializable