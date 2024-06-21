package com.example.onlineshop

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.onlineshop.api.RetrofitConfiguration
import com.example.onlineshop.models.data.Product
import com.example.onlineshop.models.response.UserOrderResponse
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.NumberFormat
import java.util.Locale

class ProductDetailActivity : AppCompatActivity() {

    private lateinit var webView: WebView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_detail)

        webView = findViewById(R.id.webView)

        // Enable JavaScript
        val webSettings: WebSettings = webView.settings
        webSettings.javaScriptEnabled = true


        // Ensure links open within WebView and not in a browser
        webView.webViewClient = WebViewClient()

        val imageView: ImageView = findViewById(R.id.imageView)
        val nameTextView: TextView = findViewById(R.id.nameTextView)
        val priceTextView: TextView = findViewById(R.id.priceTextView)
        val orderButton: Button = findViewById(R.id.orderButton)

        // Mendapatkan data dari intent
        val product = intent.getParcelableExtra<Product>("product")

        // Menampilkan data ke layout detail
        Glide.with(this).load(product?.imageUrl).into(imageView)
        nameTextView.text = product?.name

        val price = product?.price ?: 0

        // Membuat format Rupiah
        val formatter = NumberFormat.getCurrencyInstance(Locale("in", "ID"))
        val formattedPrice = formatter.format(price).replace("Rp", "Rp. ")

        priceTextView.text = formattedPrice

        orderButton.setOnClickListener {
            val json = JSONObject().apply {
                put("username", "Random_Username@gmail.com")
                put("price", price)
            }
            val body = json.toString().toRequestBody("application/json".toMediaTypeOrNull())

            val userOrder = RetrofitConfiguration.getApiService().orderUser(body)
            userOrder.enqueue(object : Callback<UserOrderResponse> {
                override fun onResponse(
                    call: Call<UserOrderResponse>,
                    response: Response<UserOrderResponse>
                ) {
                    val result = response.body()?.token.toString()
                    Log.d("ProductDetailActivity", "token: " + result)

                    // Load the HTML content
                    val htmlContent = """
                        <html>
                        <head>
                          <meta name="viewport" content="width=device-width, initial-scale=1.0">
                          <script type="text/javascript" src="https://app.stg.midtrans.com/snap/snap.js" data-client-key="SB-Mid-client-_p6kmur_V447S9Jg"></script>
                        </head>
                        <body>
                          <div id="snap-container"></div>
                          <script type="text/javascript">
                               window.snap.pay('$result');
                          </script>
                        </body>
                        </html>
                    """.trimIndent()
                    webView.visibility = View.VISIBLE
                    webView.loadDataWithBaseURL(null, htmlContent, "text/html", "UTF-8", null)

                }

                override fun onFailure(call: Call<UserOrderResponse>, t: Throwable) {
                    Log.e("ProductDetailActivity", "Response order product is Error", t)
                }
            })
        }
    }
}
