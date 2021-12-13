package com.untels.redcetario.response

import com.untels.redcetario.model.Cliente
import java.io.Serializable

data class InicioSesionResponse(
    val ok: Boolean,
    val cliente: Cliente?,
    val mensaje: String
) : Serializable