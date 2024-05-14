package com.bemos.matuleshoes.data.supabase.card

import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.gotrue.auth
import io.github.jan.supabase.gotrue.providers.builtin.Email
import io.github.jan.supabase.postgrest.postgrest
import kotlinx.serialization.Serializable

class BaseFavoriteManager(
    private val supabaseClient: SupabaseClient
) {

    suspend fun putItem(
        image: String,
        name: String,
        price: String,
        id_user: String
    ) {
        supabaseClient.postgrest["orders"].insert(Product(
            image = image,
            name = name,
            price = price,
            id_user = id_user
        ))
    }

    suspend fun deleteItem(
        name: String,
        price: String
    ) {
        supabaseClient.postgrest["orders"].delete() {
            filter { eq("name", name) }
            filter { eq("price", price) }
        }
    }

    suspend fun getItems(
        id_user: String
    ): List<Product> {
        val products = supabaseClient.postgrest["orders"].select {
            filter {
                eq("id_user", id_user)
            }
        }.decodeList<Product>()
        return products
    }

}
@Serializable
data class Product(
    val id: String? = null,
    val id_user: String? = null,
    val image: String,
    val name: String,
    val price: String
)