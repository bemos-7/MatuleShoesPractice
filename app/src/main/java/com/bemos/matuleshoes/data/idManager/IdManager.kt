package com.bemos.matuleshoes.data.idManager

import android.content.Context
import androidx.core.content.edit
import io.ktor.utils.io.concurrent.shared

class IdManager(context: Context) {

    private val sharedPreferences = context.getSharedPreferences("Id", Context.MODE_PRIVATE)

    fun get(): String {
        val id = sharedPreferences.getString("idKey", "")
        return id.toString()
    }

    fun set(id: String) {
        sharedPreferences.edit {
            putString("idKey", id)
        }
    }

}