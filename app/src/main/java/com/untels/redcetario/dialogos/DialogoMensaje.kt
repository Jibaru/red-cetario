package com.untels.redcetario.dialogos

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.TextView
import com.untels.redcetario.R
import com.untels.redcetario.model.Notificacion
import kotlinx.android.synthetic.main.dialogo_mensaje.*
import kotlinx.android.synthetic.main.dialogo_notificacion.*

class DialogoMensaje(
    context: Context,
    private val titulo: String,
    private val descripcion: String
) : Dialog(context) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialogo_mensaje)

        val titulo: TextView = findViewById(R.id.tvTituloMensaje)
        val descripcion: TextView = findViewById(R.id.tvContenidoMensaje)

        titulo.text = this.titulo
        descripcion.text = this.descripcion

        window?.setLayout(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.MATCH_PARENT
        )
        window?.setBackgroundDrawableResource(R.color.black_transparent)

        btnAceptarMensaje.setOnClickListener {
            dismiss()
        }
    }
}