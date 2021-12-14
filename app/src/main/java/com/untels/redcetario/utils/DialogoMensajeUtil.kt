package com.untels.redcetario.utils

import android.content.Context
import com.untels.redcetario.dialogos.DialogoMensaje

open class DialogoMensajeUtil {
    companion object {
        private var dialogoMensaje: DialogoMensaje? = null
        fun showDialog(
            context: Context?,
            titulo: String,
            descripcion: String
        ) {
            hideDialog()
            if (context != null) {
                try {
                    dialogoMensaje = DialogoMensaje(context, titulo, descripcion)
                    dialogoMensaje?.let { dialogoMensaje->
                        dialogoMensaje.setCanceledOnTouchOutside(true)
                        dialogoMensaje.setCancelable(true)
                        dialogoMensaje.show()
                    }

                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }

        fun hideDialog() {
            if (dialogoMensaje!=null && dialogoMensaje?.isShowing!!) {
                dialogoMensaje = try {
                    dialogoMensaje?.dismiss()
                    null
                } catch (e: Exception) {
                    null
                }
            }
        }

    }
}