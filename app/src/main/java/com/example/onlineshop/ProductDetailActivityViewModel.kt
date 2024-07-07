package com.example.onlineshop

import android.text.Editable
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.onlineshop.api.RetrofitConfiguration
import com.example.onlineshop.models.response.UserOrderResponse
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProductDetailActivityViewModel: ViewModel() {

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> =  _isLoading
    fun setIsLoading(value: Boolean) {
        _isLoading.value = value
    }


    private val _snapToken = MutableLiveData<String>()
    val snapToken: LiveData<String> = _snapToken
    fun setSnapToken(value: String) {
        _snapToken.value = value
    }

    fun order(price: Int, username: Editable, productName: String?) {

        val json = JSONObject().apply {
            put("username", username)
            put("price", price)
            put("product_name", productName)
        }
        val body = json.toString().toRequestBody("application/json".toMediaTypeOrNull())

        RetrofitConfiguration.getApiService().orderUser(body).enqueue(
            object :  Callback<UserOrderResponse>  {
                override fun onResponse(
                    call: Call<UserOrderResponse>,
                    response: Response<UserOrderResponse>
                ) {
                    val result = response.body()
                    Log.d("ProductDetailActivity", "token: " + result.toString())
                    if (result != null) {
                        result.token?.let { setSnapToken(it) }
                    }
                }

                override fun onFailure(call: Call<UserOrderResponse>, t: Throwable) {
                    Log.e("ProductDetailActivity", "Request Order is failure -> " +  t.message.toString())
                }

            }
        )

    }
}