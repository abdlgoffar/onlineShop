package com.example.onlineshop

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.onlineshop.models.data.Product

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
        nameTextView.text = product?.name
        priceTextView.text = product?.price
    }
}