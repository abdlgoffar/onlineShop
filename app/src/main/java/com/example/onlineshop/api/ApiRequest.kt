package com.example.onlineshop.api

import com.example.onlineshop.models.response.GetAllProductByCategoryResponse
import com.example.onlineshop.models.response.GetAllProductResponse
import com.example.onlineshop.models.response.UserLoginResponse
import com.example.onlineshop.models.response.UserOrderResponse
import com.example.onlineshop.models.response.UserRegisterResponse
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiRequest {

    @GET("products/get/all")
    fun getAllProduct(): Call<GetAllProductResponse>

    @GET("products/search/{categoryName}")
    fun getAllProductByCategory(@Path(value="categoryName") categoryName: String?): Call<GetAllProductByCategoryResponse>

    @POST("users/register")
    fun registerUser(@Body requestBody: RequestBody): Call<UserRegisterResponse>

    @POST("users/login")
    fun loginUser(@Body requestBody: RequestBody): Call<UserLoginResponse>

    @POST("users/order")
    fun orderUser(@Body requestBody: RequestBody): Call<UserOrderResponse>
}