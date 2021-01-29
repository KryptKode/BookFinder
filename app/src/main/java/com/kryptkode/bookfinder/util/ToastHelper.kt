package com.kryptkode.bookfinder.util

import android.content.Context
import android.widget.Toast

class ToastHelper(private val context: Context) {
    fun showMessage(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }
}