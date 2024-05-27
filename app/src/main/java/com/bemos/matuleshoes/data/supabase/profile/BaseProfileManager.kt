package com.bemos.matuleshoes.data.supabase.profile

import com.bemos.matuleshoes.data.supabase.auth.Profile
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.postgrest.from
import io.github.jan.supabase.postgrest.postgrest

class BaseProfileManager(
    private val supabaseClient: SupabaseClient
) {

    suspend fun getProfile(
        email: String
    ): Profile {
        val profile = supabaseClient.postgrest["profiles"].select {
            filter {
                eq("email", email)
            }
        }.decodeSingle<Profile>()
        return profile
    }

    suspend fun updateProfile(
        fullname: String,
        email: String,
    ) {
        supabaseClient.from("profiles").update(
            {
                set("email", email)
                set("fullname", fullname)
            }
        ) {
            filter {
                eq("email", email)
            }
        }
    }

    suspend fun updateProfileSecond(
        fullname: String,
        avatar: String,
        email: String,
    ) {
        supabaseClient.from("profiles").update(
            {
                set("email", email)
                set("fullname", fullname)
                set("avatar", avatar)
            }
        ) {
            filter {
                eq("email", email)
            }
        }
    }

}