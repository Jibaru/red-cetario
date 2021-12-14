package com.untels.redcetario.utils

import android.content.Context
import com.untels.redcetario.dialogos.DialogoCarga

open class CargadorUtil {
    companion object {
        private var dialogoCarga: DialogoCarga? = null
        fun showDialog(
            context: Context?,
            isCancelable: Boolean
        ) {
            hideDialog()
            if (context != null) {
                try {
                    dialogoCarga = DialogoCarga(context)
                    dialogoCarga?.let { dialogoCarga->
                        dialogoCarga.setCanceledOnTouchOutside(true)
                        dialogoCarga.setCancelable(isCancelable)
                        dialogoCarga.show()
                    }

                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }

        fun hideDialog() {
            if (dialogoCarga!=null && dialogoCarga?.isShowing!!) {
                dialogoCarga = try {
                    dialogoCarga?.dismiss()
                    null
                } catch (e: Exception) {
                    null
                }
            }
        }

    }

}