package com.untels.redcetario.service

import com.untels.redcetario.constant.API_BASE
import com.untels.redcetario.constant.OBTENER_RECETA
import com.untels.redcetario.constant.OBTENER_RECETAS
import okhttp3.*

class RecetaService {
    private val client = OkHttpClient()

    fun obtenerTodos(callback: Callback) {
        val request = Request.Builder()
            .url(API_BASE + OBTENER_RECETAS)
            .build()

        client.newCall(request).enqueue(callback)
    }

    fun obtener(idReceta: Int, callback: Callback) {
        val url = API_BASE + OBTENER_RECETA.replace("{id}", idReceta.toString())
        val request = Request.Builder()
            .url(url)
            .build()

        client.newCall(request).enqueue(callback)
    }
}