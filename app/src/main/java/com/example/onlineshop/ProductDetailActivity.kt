package com.example.onlineshop

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.util.Log
import android.view.View
import android.webkit.JavascriptInterface
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.viewModels
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

    private val productDetailViewModel: ProductDetailActivityViewModel by viewModels()
    lateinit var webView: WebView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_detail)

        webView = findViewById(R.id.webView)

        val imageView: ImageView = findViewById(R.id.imageView)
        val nameTextView: TextView = findViewById(R.id.nameTextView)
        val priceTextView: TextView = findViewById(R.id.priceTextView)
        val orderButton: Button = findViewById(R.id.orderButton)

        // Get data object
        val product = intent.getParcelableExtra<Product>("product")

        // load image to UI
        Glide.with(this).load(product?.imageUrl).into(imageView)
        nameTextView.text = product?.name

        val price = product?.price ?: 0

        // create RP formatter
        val formatter = NumberFormat.getCurrencyInstance(Locale("in", "ID"))
        val formattedPrice = formatter.format(price).replace("Rp", "Rp. ")

        priceTextView.text = formattedPrice

        orderButton.setOnClickListener {
            this.productDetailViewModel.order(price, Editable.Factory.getInstance().newEditable("Random@gmail.com"))
            this.productDetailViewModel.snapToken.observe(this) { result ->
                run {
                    webView.apply {
                        settings.javaScriptEnabled = true
                        webViewClient = WebViewClient()
                        addJavascriptInterface(this@ProductDetailActivity, "AndroidInterface")
                        val htmlCode = """
                        <html>
                        <head>
                          <meta name="viewport" content="width=device-width, initial-scale=1.0">
                          <script type="text/javascript" src="https://app.stg.midtrans.com/snap/snap.js" data-client-key="SB-Mid-client-_p6kmur_V447S9Jg"></script>
                        </head>
                        <body>
                          <div id="snap-container"></div>
                          <button onclick="closeWebView()">Close</button>
                          <script type="text/javascript">
                               window.snap.pay($result);
                               function closeWebView() {
                                   AndroidInterface.closeWebView();
                               }
                          </script>
                        </body>
                        </html>
                        """.trimIndent()
                        visibility = View.VISIBLE
                        loadDataWithBaseURL(null, htmlCode, "text/html", "UTF-8", null)
                    }
                }
            }
        }
    }

    @JavascriptInterface
    fun closeWebView() {
        runOnUiThread {
            webView.visibility = View.GONE
        }
    }
}


//class ProductDetailActivity : AppCompatActivity() {
//
//
//
//    private val productDetailViewModel: ProductDetailActivityViewModel by viewModels()
//
//    lateinit var webView: WebView
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_product_detail)
//
//        webView = findViewById(R.id.webView)
//
//
//        // Enable JavaScript
////        val webSettings: WebSettings = webView.settings
////        webSettings.javaScriptEnabled = true
////
////
////        // Ensure links open within WebView and not in a browser
////        webView.webViewClient = WebViewClient()
//
//        val imageView: ImageView = findViewById(R.id.imageView)
//        val nameTextView: TextView = findViewById(R.id.nameTextView)
//        val priceTextView: TextView = findViewById(R.id.priceTextView)
//        val orderButton: Button = findViewById(R.id.orderButton)
//
//        // Get data object
//        val product = intent.getParcelableExtra<Product>("product")
//
//        // load image to UI
//        Glide.with(this).load(product?.imageUrl).into(imageView)
//        nameTextView.text = product?.name
//
//        val price = product?.price ?: 0
//
//        // create RP formater
//        val formatter = NumberFormat.getCurrencyInstance(Locale("in", "ID"))
//        val formattedPrice = formatter.format(price).replace("Rp", "Rp. ")
//
//        priceTextView.text = formattedPrice
//
//        orderButton.setOnClickListener {
//            this.productDetailViewModel.order(price, Editable.Factory.getInstance().newEditable("Random@gmail.com"))
//            this.productDetailViewModel.snapToken.observe(this) {
//                result ->
//                run {
//                    webView.apply {
//                        settings.javaScriptEnabled = true
//                        webViewClient = WebViewClient()
//                        val htmlCode = """
//                        <html>
//                        <head>
//                          <meta name="viewport" content="width=device-width, initial-scale=1.0">
//                          <script type="text/javascript" src="https://app.stg.midtrans.com/snap/snap.js" data-client-key="SB-Mid-client-_p6kmur_V447S9Jg"></script>
//                        </head>
//                        <body>
//                          <div id="snap-container"></div>
//                          <script type="text/javascript">
//                               window.snap.pay('$result');
//                          </script>
//                        </body>
//                        </html>
//                        """.trimIndent()
//                        webView.visibility = View.VISIBLE
//                        webView.loadDataWithBaseURL(null, htmlCode, "text/html", "UTF-8", null)
//
//                    }
//                }
//            }
//        }
//    }
//
//
//
//
//}
