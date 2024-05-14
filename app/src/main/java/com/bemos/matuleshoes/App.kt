package com.bemos.matuleshoes

import android.app.Application
import android.net.Uri
import android.util.Log
import com.bemos.matuleshoes.data.email.EmailManager
import com.bemos.matuleshoes.data.supabase.auth.BaseAuthManager
import com.bemos.matuleshoes.data.supabase.card.BaseFavoriteManager
import com.bemos.matuleshoes.data.supabase.profile.BaseProfileManager
import io.github.jan.supabase.SupabaseClientBuilder
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.gotrue.Auth
import io.github.jan.supabase.postgrest.Postgrest
import kotlinx.serialization.Serializable

class App : Application() {

    companion object {
        lateinit var instance: App
            private set

        var email = ""
        var id = ""

        var image: String? = null
    }

    override fun onCreate() {
        super.onCreate()
        instance = this

        val emailManager = EmailManager(this)
        Log.d("emailSaveManager", emailManager.get())
    }

    private val supabaseClient by lazy {
        createSupabaseClient(
            "https://jyigbjgcdvmxtjwixcea.supabase.co",
            "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6Imp5aWdiamdjZHZteHRqd2l4Y2VhIiwicm9sZSI6ImFub24iLCJpYXQiOjE3MDgyNTU0MjksImV4cCI6MjAyMzgzMTQyOX0.oUtvji7CjDbYw_rk4OIKJO7Yz9fhgz8d1Rw9-_BnVEs"
        ) {
            install(Postgrest)
            install(Auth)
        }
    }

    val baseAuthManager by lazy {
        BaseAuthManager(supabaseClient)
    }

    val baseProfileManager by lazy {
        BaseProfileManager(supabaseClient)
    }

    val baseFavoriteManager by lazy {
        BaseFavoriteManager(supabaseClient)
    }

}