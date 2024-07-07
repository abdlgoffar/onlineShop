package com.example.onlineshop

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.webkit.WebView
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.os.LocaleListCompat
import com.bumptech.glide.Glide
import com.example.onlineshop.models.data.Product
import com.midtrans.sdk.corekit.core.MidtransSDK
import com.midtrans.sdk.uikit.api.model.CustomColorTheme
import com.midtrans.sdk.uikit.api.model.TransactionResult
import com.midtrans.sdk.uikit.external.UiKitApi
import com.midtrans.sdk.uikit.internal.util.UiKitConstants
import java.text.NumberFormat
import java.util.Locale


class ProductDetailActivity : AppCompatActivity() {

    private val productDetailViewModel: ProductDetailActivityViewModel by viewModels()
    lateinit var webView: WebView
    private lateinit var paymentLauncher: ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_detail)

        // Initialize Midtrans SDK
        initMidtransSdk()

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
        val productName = product?.name

        // create RP formatter
        val formatter = NumberFormat.getCurrencyInstance(Locale("in", "ID"))
        val formattedPrice = formatter.format(price).replace("Rp", "Rp. ")

        priceTextView.text = formattedPrice

        // Register the activity result launcher
        paymentLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result?.resultCode == RESULT_OK) {
                result.data?.let {
                    val transactionResult = it.getParcelableExtra<TransactionResult>(
                        UiKitConstants.KEY_TRANSACTION_RESULT)
                    Toast.makeText(this, "${transactionResult?.transactionId}", Toast.LENGTH_LONG).show()
                }
            }
        }

        orderButton.setOnClickListener {
            this.productDetailViewModel.order(
                price,
                Editable.Factory.getInstance().newEditable("Random@gmail.com"),
                productName
            )
            this.productDetailViewModel.snapToken.observe(this) { token ->
                UiKitApi.getDefaultInstance().startPaymentUiFlow(
                    this,
                    paymentLauncher,
                    token
                )
            }
        }
    }

    private fun initMidtransSdk() {
        UiKitApi.Builder()
            .withMerchantClientKey("SB-Mid-client-_p6kmur_V447S9Jg")
            .withContext(this) // context is mandatory
            .withMerchantUrl("http://192.168.1.18:8000/api/users/order/")
            .enableLog(true) // enable sdk log (optional)
            .withColorTheme(CustomColorTheme("#FFE51255", "#B61548", "#FFE51255"))
            .build()
        setLocaleNew("id") //`en` for English and `id` for Indonesian
    }

    private fun setLocaleNew(languageCode: String?) {
        val locales = LocaleListCompat.forLanguageTags(languageCode)
        AppCompatDelegate.setApplicationLocales(locales)
    }
}


//
//class ProductDetailActivity : AppCompatActivity() {
//
//    private val productDetailViewModel: ProductDetailActivityViewModel by viewModels()
//    lateinit var webView: WebView
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_product_detail)
//
//        // Initialize Midtrans SDK
//        initMidtransSdk()
//
//        webView = findViewById(R.id.webView)
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
//        // create RP formatter
//        val formatter = NumberFormat.getCurrencyInstance(Locale("in", "ID"))
//        val formattedPrice = formatter.format(price).replace("Rp", "Rp. ")
//
//        priceTextView.text = formattedPrice
//
//        orderButton.setOnClickListener {
//            this.productDetailViewModel.order(
//                price,
//                Editable.Factory.getInstance().newEditable("Random@gmail.com")
//            )
//            this.productDetailViewModel.snapToken.observe(this) { token ->
//
//                val launcher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
//                    if (result?.resultCode == RESULT_OK) {
//                        result.data?.let {
//                            val transactionResult = it.getParcelableExtra<TransactionResult>(
//                                UiKitConstants.KEY_TRANSACTION_RESULT)
//                            Toast.makeText(this,"${transactionResult?.transactionId}", Toast.LENGTH_LONG).show()
//                        }
//                    }
//                }
//                UiKitApi.getDefaultInstance().startPaymentUiFlow(
//                    this,
//                    launcher,
//                    token
//                )
//            }
//        }
//    }
//
//    private fun initMidtransSdk() {
//        UiKitApi.Builder()
//            .withMerchantClientKey("SB-Mid-client-_p6kmur_V447S9Jg")
//            .withContext(this) // context is mandatory
//            .withMerchantUrl("https://jualankopihamizan.000webhostapp.com/api/users/order/")
//            .enableLog(true) // enable sdk log (optional)
//            .withColorTheme(CustomColorTheme("#FFE51255", "#B61548", "#FFE51255"))
//            .build()
//        setLocaleNew("id") //`en` for English and `id` for Indonesian
//    }
//
//    private fun setLocaleNew(languageCode: String?) {
//        val locales = LocaleListCompat.forLanguageTags(languageCode)
//        AppCompatDelegate.setApplicationLocales(locales)
//    }
//}

//class ProductDetailActivity : AppCompatActivity() {
//
//    private val productDetailViewModel: ProductDetailActivityViewModel by viewModels()
//    lateinit var webView: WebView
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_product_detail)
//
//        webView = findViewById(R.id.webView)
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
//        // create RP formatter
//        val formatter = NumberFormat.getCurrencyInstance(Locale("in", "ID"))
//        val formattedPrice = formatter.format(price).replace("Rp", "Rp. ")
//
//        priceTextView.text = formattedPrice
//
//        orderButton.setOnClickListener {
//            this.productDetailViewModel.order(
//                price,
//                Editable.Factory.getInstance().newEditable("Random@gmail.com")
//            )
//            this.productDetailViewModel.snapToken.observe(this) { result ->
//
//                UiKitApi.Builder()
//                    .withMerchantClientKey("SB-Mid-client-_p6kmur_V447S9Jg")
//                    .withContext(applicationContext) // context is mandatory
//                    .withMerchantUrl("https://jualankopihamizan.000webhostapp.com/api/users/order/")
//                    .enableLog(true) // enable sdk log (optional)
//                    .build()
//                setLocaleNew("id") //`en` for English and `id` for Indonesian
//
//
//                MidtransSDK.getInstance().startPaymentUiFlow(applicationContext, result)
//            }
//
//
//        }
//    }
//    private fun setLocaleNew(languageCode: String?) {
//        val locales = LocaleListCompat.forLanguageTags(languageCode)
//        AppCompatDelegate.setApplicationLocales(locales)
//    }
//
//}


// Payment use WebView
//class ProductDetailActivity : AppCompatActivity() {
//
//    private val productDetailViewModel: ProductDetailActivityViewModel by viewModels()
//    lateinit var webView: WebView
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_product_detail)
//
//        webView = findViewById(R.id.webView)
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
//        // create RP formatter
//        val formatter = NumberFormat.getCurrencyInstance(Locale("in", "ID"))
//        val formattedPrice = formatter.format(price).replace("Rp", "Rp. ")
//
//        priceTextView.text = formattedPrice
//
//        orderButton.setOnClickListener {
//            this.productDetailViewModel.order(price, Editable.Factory.getInstance().newEditable("Random@gmail.com"))
//            this.productDetailViewModel.snapToken.observe(this) { result ->
//                run {
//                    webView.apply {
//                        settings.javaScriptEnabled = true
//                        webViewClient = WebViewClient()
//                        addJavascriptInterface(this@ProductDetailActivity, "AndroidInterface")
//                        val htmlCode = """
//                        <html>
//                        <head>
//                          <meta name="viewport" content="width=device-width, initial-scale=1.0">
//                          <script type="text/javascript" src="https://app.stg.midtrans.com/snap/snap.js" data-client-key="SB-Mid-client-_p6kmur_V447S9Jg"></script>
//                        </head>
//                        <body>
//                          <div id="snap-container"></div>
//                          <button onclick="closeWebView()">Close</button>
//                          <script type="text/javascript">
//                               window.snap.pay("$result");
//                               function closeWebView() {
//                                   AndroidInterface.closeWebView();
//                               }
//                          </script>
//                        </body>
//                        </html>
//                        """.trimIndent()
//                        visibility = View.VISIBLE
//                        loadDataWithBaseURL(null, htmlCode, "text/html", "UTF-8", null)
//                    }
//                }
//            }
//        }
//    }
//
//    @JavascriptInterface
//    fun closeWebView() {
//        runOnUiThread {
//            webView.visibility = View.GONE
//        }
//    }
//}

