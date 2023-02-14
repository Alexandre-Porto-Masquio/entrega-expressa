package com.apmasquio.entrega_expressa.data.api

import org.json.JSONObject
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface UfApi {
    @GET("localidades/estados/{UF}/distritos")
    suspend fun getDistricts(): List<JSONObject>

    companion object {
        private const val BASE_URL = "https://servicodados.ibge.gov.br/api/v1/"

        fun create(): UfApi {
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            return retrofit.create(UfApi::class.java)
        }
    }
}