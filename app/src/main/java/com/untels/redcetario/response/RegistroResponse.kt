package com.untels.redcetario.response

import com.untels.redcetario.model.Cliente
import java.io.Serializable

data class RegistroResponse(
    val ok: Boolean,
    val cliente: Cliente
) : Serializable