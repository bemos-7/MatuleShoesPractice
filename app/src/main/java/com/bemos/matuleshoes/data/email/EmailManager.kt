package com.bemos.matuleshoes.data.email

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import io.ktor.utils.io.concurrent.shared

class EmailManager(context: Context) {

    private val sharedPreferences = context.getSharedPreferences("STR", Context.MODE_PRIVATE)

    fun get(): String {
        val email = sharedPreferences.getString("emailSave", "empty")
        return email.toString()
    }

    fun set(email: String) {
        sharedPreferences.edit {
            putString("emailSave", email)
        }
    }

}