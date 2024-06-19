package com.example.onlineshop.models.response

import com.example.onlineshop.models.data.User
import com.google.gson.annotations.SerializedName

data class UserLoginResponse(

    @field:SerializedName("user")
    val user: User? = null
)
