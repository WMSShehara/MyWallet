package com.example.mywallet.data.model

import android.content.Context
import com.google.gson.annotations.SerializedName
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.Properties



    data class Rates(
        var GBP: Float,
        var USD: Float,
    )

    data class ExchangeRates(
        var success: Boolean,
        var base: String,
        var date: String,
        var rates: Rates
    )


    const val Base_URL = "https://api.exchangerate.host"
interface ExchangeRatesApi {
    @GET("latest")
    suspend fun getRates(@Query("access_key") apiKey: String): ExchangeRates

    companion object {
        var exchangeRatesServices: ExchangeRatesApi? = null

        fun getInstance(apiKey: String): ExchangeRatesApi {
            if (exchangeRatesServices == null) {
                exchangeRatesServices = Retrofit.Builder()
                    .baseUrl(Base_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(ExchangeRatesApi::class.java)
            }
            return exchangeRatesServices!!
        }
    }
}










