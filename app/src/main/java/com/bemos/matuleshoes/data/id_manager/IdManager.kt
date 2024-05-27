package com.bemos.matuleshoes.data.id_manager

import android.content.Context
import androidx.core.content.edit

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