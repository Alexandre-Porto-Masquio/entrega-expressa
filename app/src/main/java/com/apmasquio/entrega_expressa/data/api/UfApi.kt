package com.apmasquio.entrega_expressa.data.api

import com.apmasquio.entrega_expressa.data.models.Uf
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface UfApi {

    @GET("localidades/estados")
    suspend fun getUfs(): List<Uf>

    @GET("localidades/estados/{uf}/municipios")
    suspend fun getCities(@Path("uf") uf: String)

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