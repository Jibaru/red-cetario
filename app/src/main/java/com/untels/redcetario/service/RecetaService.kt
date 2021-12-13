package com.untels.redcetario.service

import com.google.gson.Gson
import com.untels.redcetario.constant.API_BASE
import com.untels.redcetario.constant.OBTENER_RECETA
import com.untels.redcetario.constant.OBTENER_RECETAS
import com.untels.redcetario.model.Receta
import com.untels.redcetario.model.RecetaCabecera
import com.untels.redcetario.response.RecetaResponse
import com.untels.redcetario.response.RecetasResponse
import okhttp3.*

class RecetaService(private val gson: Gson, private val client: OkHttpClient) {

    fun obtenerTodos(): List<RecetaCabecera> {
        val request = Request.Builder()
            .url(API_BASE + OBTENER_RECETAS)
            .build()

        val call: Call = client.newCall(request)
        val response: Response = call.execute()
        val body = response.body()?.string()

        val recetasResponse: RecetasResponse = gson.fromJson(body, RecetasResponse::class.java)
        return recetasResponse.recetas
    }

    fun obtener(idReceta: Int): Receta {
        val url = API_BASE + OBTENER_RECETA.replace("{id}", idReceta.toString())
        val request = Request.Builder()
            .url(url)
            .build()

        val call: Call = client.newCall(request)
        val response: Response = call.execute()
        val body = response.body()?.string()

        val recetaResponse: RecetaResponse = gson.fromJson(body, RecetaResponse::class.java)
        return recetaResponse.receta
    }
}