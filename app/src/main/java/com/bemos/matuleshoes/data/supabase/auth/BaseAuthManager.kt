package com.bemos.matuleshoes.data.supabase.auth

import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.gotrue.OtpType
import io.github.jan.supabase.gotrue.auth
import kotlinx.serialization.Serializable
import io.github.jan.supabase.gotrue.providers.builtin.Email
import io.github.jan.supabase.gotrue.providers.builtin.OTP
import io.github.jan.supabase.postgrest.postgrest

class BaseAuthManager(
    private val supabaseClient: SupabaseClient
) {

    suspend fun signUp(
        fullname: String,
        email: String,
        password: String
    ) {
        supabaseClient.auth.signUpWith(Email) {
            this.email = email
            this.password = password
        }
        supabaseClient.postgrest["profiles"].insert(
            Profile(
                fullname = fullname,
                email = email
            )
        )
    }

    suspend fun signIn(
        email: String,
        password: String
    ) {
        supabaseClient.auth.signInWith(Email) {
            this.email = email
            this.password = password
        }
    }

    suspend fun forgotPassword(
        email: String
    ) {
        supabaseClient.auth.signInWith(OTP) {
            this.email = email
        }
    }

    suspend fun confirmOtp(
        token: String,
        email: String
    ) {
        supabaseClient.auth.verifyEmailOtp(OtpType.Email.EMAIL, email, token)
    }

    suspend fun newPassword(
        email: String,
        password: String
    ) {
        supabaseClient.auth.modifyUser {
            this.email = email
            this.password = password
        }
    }

}
@Serializable
data class Profile(
    val id: Int? = null,
    val id_: String? = null,
    val created_at: String? = null,
    val fullname: String,
    val phone: String? = null,
    val avatar: String? = null,
    val balance: Int? = 0,
    val rider: Boolean = false,
    val email: String
)