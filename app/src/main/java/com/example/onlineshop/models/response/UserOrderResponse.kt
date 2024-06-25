package com.example.onlineshop.models.response

import com.example.onlineshop.models.data.User
import com.google.gson.annotations.SerializedName

data class UserOrderResponse(
    @field:SerializedName("token")
    val token: String? = null,

    @field:SerializedName("redirect_url")
    val redirectUrl: String? = null

)
