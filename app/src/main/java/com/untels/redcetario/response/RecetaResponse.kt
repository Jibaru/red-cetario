package com.untels.redcetario.response

import com.untels.redcetario.model.Receta
import java.io.Serializable

data class RecetaResponse(
    val ok: Boolean,
    val receta: Receta
) : Serializable