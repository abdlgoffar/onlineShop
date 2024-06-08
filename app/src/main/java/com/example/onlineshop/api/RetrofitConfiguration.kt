package com.example.onlineshop.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitConfiguration {
    companion object{
        private const val url = "http://192.168.1.6:8000/api/"

        fun getApiService(): ApiRequest{
            val retrofit = Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            return retrofit.create(ApiRequest::class.java)
        }
    }
}