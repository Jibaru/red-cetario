package com.untels.redcetario.service

import com.google.gson.Gson
import com.untels.redcetario.constant.*
import com.untels.redcetario.model.Notificacion
import com.untels.redcetario.response.NotificacionesResponse
import okhttp3.*

class NotificacionService(private val gson: Gson, private val client: OkHttpClient) {
    fun obtenerTodos(idCliente: Int): List<Notificacion> {
        val url = "$API_BASE$OBTENER_NOTIFICACIONES?idCliente=$idCliente"
        val request = Request.Builder()
            .url(url)
            .build()

        val call: Call = client.newCall(request)
        val response: Response = call.execute()
        val body = response.body()?.string()

        val notificacionesResponse: NotificacionesResponse = gson
            .fromJson(body, NotificacionesResponse::class.java)
        return notificacionesResponse.notificaciones
    }

    fun eliminarTodos(idCliente: Int): Boolean {
        val url = API_BASE + ELIMINAR_NOTIFICACIONES.replace("{id}", idCliente.toString())

        val request = Request.Builder()
            .url(url)
            .delete()
            .build()

        val call: Call = client.newCall(request)
        val response: Response = call.execute()
        val body: String? = response.body()?.string()

        return body != null
    }

    fun actualizarFechaVisto(idNotificacion: Int): Boolean {
        val url = API_BASE + ACTUALIZAR_FECHA_VISTO_NOTIFICACION
            .replace("{id}", idNotificacion.toString())

        val map = HashMap<String, String>()
        val json = gson.toJson(map)
        val requestBody = RequestBody.create(
            MediaType.parse("application/json"), json
        )

        val request = Request.Builder()
            .url(url)
            .put(requestBody)
            .build()

        val call: Call = client.newCall(request)
        val response: Response = call.execute()
        val body: String? = response.body()?.string()

        return body != null
    }
}