package com.untels.redcetario.model

import java.io.Serializable

data class Ingrediente(
    val id: Int,
    val nombre: String,
    val descripcion: String,
    val pivot: PivotIngrediente
) : Serializable