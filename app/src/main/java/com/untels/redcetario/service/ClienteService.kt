package com.untels.redcetario.service

import com.google.gson.Gson
import com.untels.redcetario.constant.*
import com.untels.redcetario.model.Cliente
import com.untels.redcetario.response.ActualizarClienteResponse
import com.untels.redcetario.response.InicioSesionResponse
import com.untels.redcetario.response.RegistroResponse
import okhttp3.*
import okhttp3.RequestBody

class ClienteService(private val gson: Gson, private val client: OkHttpClient) {

    fun iniciarSesion(
        correoElectronico: String,
        contrasenia: String
    ): InicioSesionResponse {
        val map = HashMap<String, String>()

        map.put("correo_electronico", correoElectronico)
        map.put("contrasenia", contrasenia)

        val json = gson.toJson(map)

        val requestBody = RequestBody.create(
            MediaType.parse("application/json"), json
        )

        val request = Request.Builder()
            .url(API_BASE + LOGIN)
            .post(requestBody)
            .build()

        val call: Call = client.newCall(request)
        val response: Response = call.execute()
        val body: String? = response.body()?.string()

        val inicioSesionResponse: InicioSesionResponse =
            gson.fromJson(body, InicioSesionResponse::class.java)
        return inicioSesionResponse
    }

    fun registro(
        cliente: Cliente,
        contrasenia: String
    ): RegistroResponse {
        val map = HashMap<String, String>()

        map.put("correo_electronico", cliente.correoElectronico)
        map.put("nombre", cliente.nombre)
        map.put("contrasenia", contrasenia)
        map.put("ape_paterno", cliente.apePaterno)
        map.put("ape_materno", cliente.apeMaterno)

        val json = gson.toJson(map)

        val requestBody = RequestBody.create(
            MediaType.parse("application/json"), json
        )

        val request = Request.Builder()
            .url(API_BASE + REGISTRO)
            .post(requestBody)
            .build()

        val call: Call = client.newCall(request)
        val response: Response = call.execute()
        val body: String? = response.body()?.string()

        val registroResponse: RegistroResponse =
            gson.fromJson(body, RegistroResponse::class.java)
        return registroResponse
    }

    fun actualizar(
        cliente: Cliente
    ): Boolean {
        val url = API_BASE + ACTUALIZAR_PERFIL.replace("{id}", cliente.id.toString())
        val map = HashMap<String, String>()

        map.put("correo_electronico", cliente.correoElectronico)
        map.put("nombre", cliente.nombre)
        map.put("ape_paterno", cliente.apePaterno)
        map.put("ape_materno", cliente.apeMaterno)

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

        val respuesta: ActualizarClienteResponse =
            gson.fromJson(body, ActualizarClienteResponse::class.java)
        return respuesta.ok
    }
}