package com.untels.redcetario.service

import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import java.security.Provider

object ServiceManager {
    private val client = OkHttpClient()
    private val gson = GsonBuilder()
        .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
        .create()
    private val recetaService: RecetaService = RecetaService(gson, client)
    private val clienteService: ClienteService = ClienteService(gson, client)
    private val notificacionService: NotificacionService = NotificacionService(gson, client)
    private val autenticacionService: AutenticacionService = AutenticacionService(gson)

    fun getRecetaService(): RecetaService {
        return recetaService
    }

    fun getClienteService(): ClienteService {
        return clienteService
    }

    fun getNotificacionService(): NotificacionService {
        return notificacionService
    }

    fun getAutenticacionService(): AutenticacionService {
        return autenticacionService
    }

}