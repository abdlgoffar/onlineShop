package com.example.onlineshop

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.onlineshop.models.data.Product
import java.text.NumberFormat
import java.util.Locale

class ProductDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_detail)


        val imageView: ImageView = findViewById(R.id.imageView)
       val nameTextView: TextView = findViewById(R.id.nameTextView)
        val priceTextView: TextView = findViewById(R.id.priceTextView)

        // Mendapatkan data dari intent
        val product = intent.getParcelableExtra<Product>("product")

        // Menampilkan data ke layout detail
        Glide.with(this).load(product?.imageUrl).into(imageView)
        nameTextView.text =  product?.name

        val price = product?.price ?: 0

        // Membuat format Rupiah
        val formatter = NumberFormat.getCurrencyInstance(Locale("in", "ID"))
        val formattedPrice = formatter.format(price).replace("Rp", "Rp. ")

        priceTextView.text = formattedPrice
    }
}