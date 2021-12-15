package com.untels.redcetario.service

import com.google.gson.Gson
import com.untels.redcetario.constant.*
import com.untels.redcetario.model.Receta
import com.untels.redcetario.model.RecetaCabecera
import com.untels.redcetario.response.RecetaResponse
import com.untels.redcetario.response.RecetasResponse
import com.untels.redcetario.response.RegistroResponse
import okhttp3.*

class RecetaService(private val gson: Gson, private val client: OkHttpClient) {

    fun obtenerTodos(): List<RecetaCabecera> {
        val request = Request.Builder()
            .url(API_BASE + OBTENER_RECETAS)
            .build()

        val call: Call = client.newCall(request)
        val response: Response = call.execute()
        val body = response.body()?.string()
        println(body)
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
        println(body)
        val recetaResponse: RecetaResponse = gson.fromJson(body, RecetaResponse::class.java)
        return recetaResponse.receta
    }

    fun comentar(idReceta: Int, idCliente: Int, comentario: String): Boolean {
        val url = API_BASE + PUBLICAR_COMENTARIO.replace("{id}", idReceta.toString())
        val map = HashMap<String, String>()

        map.put("descripcion", comentario)
        map.put("id_cliente", idCliente.toString())

        val json = gson.toJson(map)

        val requestBody = RequestBody.create(
            MediaType.parse("application/json"), json
        )

        val request = Request.Builder()
            .url(url)
            .post(requestBody)
            .build()

        val call: Call = client.newCall(request)
        val response: Response = call.execute()
        val body: String? = response.body()?.string()

        println(body)

        return body != null
    }

    fun marcarFavorito(idReceta: Int, idCliente: Int): Boolean {
        val url = API_BASE + MARCAR_RECETA_FAVORITA.replace("{id}", idReceta.toString())
        val map = HashMap<String, String>()

        map.put("id_cliente", idCliente.toString())

        val json = gson.toJson(map)

        val requestBody = RequestBody.create(
            MediaType.parse("application/json"), json
        )

        val request = Request.Builder()
            .url(url)
            .post(requestBody)
            .build()

        val call: Call = client.newCall(request)
        val response: Response = call.execute()
        val body: String? = response.body()?.string()

        return body != null
    }
}