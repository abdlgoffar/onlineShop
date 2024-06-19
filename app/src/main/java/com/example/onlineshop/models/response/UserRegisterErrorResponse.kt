package com.example.onlineshop.models.response

import com.google.gson.annotations.SerializedName

data class UserRegisterErrorResponse(

	@field:SerializedName("errors")
	val errors: Errors? = null
)

data class Errors(
	@field:SerializedName("messages")
	val messages: Messages? = null
)

data class Messages(

	@field:SerializedName("username")
	val username: List<String?>? = null,

	@field:SerializedName("password")
	val password: List<String?>? = null
)

