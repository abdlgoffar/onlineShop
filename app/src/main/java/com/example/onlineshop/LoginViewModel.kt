package com.example.onlineshop

import android.text.Editable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.onlineshop.api.RetrofitConfiguration
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginViewModel: ViewModel() {


    private val _navigationEvent = MutableLiveData<Event<NavigationResult>>()
    val navigationEvent: LiveData<Event<NavigationResult>> = _navigationEvent

    fun login(username: Editable, password: Editable) {
        val json = JSONObject().apply {
            put("username", username)
            put("password", password)
        }
        val body = json.toString().toRequestBody("application/json".toMediaTypeOrNull())

        RetrofitConfiguration.getApiService().loginUser(body).enqueue(handleResponse())
    }


    private fun <T> handleResponse(): Callback<T> = object : Callback<T> {
        override fun onResponse(call: Call<T>, response: Response<T>) {
            if (response.isSuccessful) {
                _navigationEvent.postValue(Event(NavigationResult.Successfully(response.body().toString())))
            } else {
                _navigationEvent.postValue(Event(NavigationResult.Error(response.errorBody()?.string() ?: "Unknown error")))
            }
        }

        override fun onFailure(call: Call<T>, t: Throwable) {
            _navigationEvent.postValue(Event(NavigationResult.Error(t.message ?: "Unknown error")))
        }
    }

    data class Event<T>(val content: T)

    sealed class NavigationResult {

        data class Error(val message: String) : NavigationResult()
        data class Successfully(val message: String): NavigationResult()
    }
}