package com.untels.redcetario.response

import com.untels.redcetario.model.Notificacion
import java.io.Serializable

data class NotificacionesResponse(
    val ok: Boolean,
    val notificaciones: List<Notificacion>
) : Serializable