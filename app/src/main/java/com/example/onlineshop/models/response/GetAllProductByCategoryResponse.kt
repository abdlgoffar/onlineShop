package com.example.onlineshop.models.response

import android.os.Parcelable
import com.example.onlineshop.models.data.Product
import com.google.gson.annotations.SerializedName

data class GetAllProductByCategoryResponse(
    @field:SerializedName("data")
    val data: List<Product>? = null,
)
