package com.example.plantapphubx.core.util

import android.content.Context
import androidx.appcompat.app.AlertDialog

object DialogUtil {

    fun showAlert(
        context: Context,
        message: String,
        positiveButtonText: String = "Ok"
    ) {
        AlertDialog.Builder(context)
            .setMessage(message)
            .setPositiveButton(positiveButtonText) { dialog, _ ->
                dialog.dismiss()
            }
            .show()
    }
}