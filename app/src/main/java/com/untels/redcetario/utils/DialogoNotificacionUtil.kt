package com.untels.redcetario.utils

import android.content.Context
import com.untels.redcetario.dialogos.DialogoNotificacion
import com.untels.redcetario.model.Notificacion

open class DialogoNotificacionUtil {
    companion object {
        private var dialogoNotificacion: DialogoNotificacion? = null
        fun showDialog(
            context: Context?,
            notificacion: Notificacion
        ) {
            hideDialog()
            if (context != null) {
                try {
                    dialogoNotificacion = DialogoNotificacion(context, notificacion)
                    dialogoNotificacion?.let { dialogoNotificacion->
                        dialogoNotificacion.setCanceledOnTouchOutside(true)
                        dialogoNotificacion.setCancelable(true)
                        dialogoNotificacion.show()
                    }

                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }

        fun hideDialog() {
            if (dialogoNotificacion!=null && dialogoNotificacion?.isShowing!!) {
                dialogoNotificacion = try {
                    dialogoNotificacion?.dismiss()
                    null
                } catch (e: Exception) {
                    null
                }
            }
        }

    }
}