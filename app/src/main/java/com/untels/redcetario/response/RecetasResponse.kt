package com.untels.redcetario.response

import com.untels.redcetario.model.RecetaCabecera
import java.io.Serializable

data class RecetasResponse(
    val ok: Boolean,
    val recetas: List<RecetaCabecera>
) : Serializable