package com.example.onlineshop.api

import com.example.onlineshop.models.response.GetAllProductByCategoryResponse
import com.example.onlineshop.models.response.GetAllProductResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiRequest {

    @GET("products/get/all")
    fun getAllProduct(): Call<GetAllProductResponse>

    @GET("products/search/{categoryName}")
    fun getAllProductByCategory(@Path(value="categoryName") categoryName: String?): Call<GetAllProductByCategoryResponse>



}