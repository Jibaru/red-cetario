package com.untels.redcetario.service

import com.google.gson.Gson
import com.untels.redcetario.model.Cliente

class AutenticacionService(private val gson: Gson) {
    private var cliente: Cliente? = null

    fun setCliente(cliente: Cliente) {
        this.cliente = cliente
    }

    fun isAutenticado(): Boolean {
        return cliente != null
    }

    fun getCliente(): Cliente? {
        return cliente
    }

    fun limpiarCliente() {
        cliente = null
    }

}