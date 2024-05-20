package com.example.onlineshop

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView


import androidx.viewpager2.widget.ViewPager2
import com.example.onlineshop.adapters.CategoryAdapter
import com.example.onlineshop.adapters.ProductAdapter
import com.example.onlineshop.adapters.RewardAdapter
import com.example.onlineshop.adapters.SliderAdapter
import com.example.onlineshop.models.data.Category
import com.example.onlineshop.models.data.Product
import com.example.onlineshop.models.data.Reward

class MainActivity : AppCompatActivity() {

    lateinit var imgProfile: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        imgProfile = findViewById(R.id.circularImageView)
        imgProfile.setImageResource(R.drawable.man)

//      Slider Adapter
        val viewPager: ViewPager2 = findViewById(R.id.viewPager)
        val imageUrls = listOf(
            "https://images.unsplash.com/photo-1557858310-9052820906f7?w=600&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8M3x8YWR2ZXJ0aXNlbWVudHxlbnwwfHwwfHx8MA%3D%3D",
        )
        val adapter = SliderAdapter(imageUrls)
        viewPager.adapter = adapter


//      Products Adapter
        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = GridLayoutManager(this, 2)
        val productList = listOf(
            Product("Headphone", "https://images.unsplash.com/photo-1505740420928-5e560c06d30e?w=600&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8M3x8cHJvZHVjdHxlbnwwfHwwfHx8MA%3D%3D", "$100"),
            Product("Watch", "https://images.unsplash.com/photo-1523275335684-37898b6baf30?w=600&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8Mnx8cHJvZHVjdHxlbnwwfHwwfHx8MA%3D%3D", "$150"),
            Product("Camera", "https://images.unsplash.com/photo-1526170375885-4d8ecf77b99f?w=600&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8Nnx8cHJvZHVjdHxlbnwwfHwwfHx8MA%3D%3D", "$700"),
            Product("Glasses", "https://images.unsplash.com/photo-1572635196237-14b3f281503f?w=600&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8NHx8cHJvZHVjdHxlbnwwfHwwfHx8MA%3D%3D", "$50"),
            Product("Shoes", "https://images.unsplash.com/photo-1560343090-f0409e92791a?q=80&w=1964&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D", "$700"),
            Product("Car Toy", "https://images.unsplash.com/photo-1581235720704-06d3acfcb36f?w=600&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8MTV8fHByb2R1Y3R8ZW58MHx8MHx8fDA%3D", "$50"),
            Product("Headphone", "https://images.unsplash.com/photo-1505740420928-5e560c06d30e?w=600&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8M3x8cHJvZHVjdHxlbnwwfHwwfHx8MA%3D%3D", "$100"),
            Product("Watch", "https://images.unsplash.com/photo-1523275335684-37898b6baf30?w=600&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8Mnx8cHJvZHVjdHxlbnwwfHwwfHx8MA%3D%3D", "$150"),
            Product("Camera", "https://images.unsplash.com/photo-1526170375885-4d8ecf77b99f?w=600&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8Nnx8cHJvZHVjdHxlbnwwfHwwfHx8MA%3D%3D", "$700"),
            Product("Glasses", "https://images.unsplash.com/photo-1572635196237-14b3f281503f?w=600&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8NHx8cHJvZHVjdHxlbnwwfHwwfHx8MA%3D%3D", "$50"),
            Product("Shoes", "https://images.unsplash.com/photo-1560343090-f0409e92791a?q=80&w=1964&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D", "$700"),
            Product("Car Toy", "https://images.unsplash.com/photo-1581235720704-06d3acfcb36f?w=600&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8MTV8fHByb2R1Y3R8ZW58MHx8MHx8fDA%3D", "$50"),

        )
        recyclerView.adapter = ProductAdapter(productList)


//      Categories Adapter
        val recyclerViewCategory: RecyclerView = findViewById(R.id.recycler_view_category)
        recyclerViewCategory.layoutManager = GridLayoutManager(this, 5)
        val categories = listOf(
            Category("Bag", R.drawable.bag),
            Category("clothes", R.drawable.clothes),
            Category("Computer", R.drawable.computer),
            Category("Glasses", R.drawable.glasses),
            Category("Bowl", R.drawable.bowl),
            Category("Dress", R.drawable.dress),
            Category("Hat", R.drawable.hat),
            Category("Lipstick", R.drawable.ipstick),
            Category("Shoes", R.drawable.shoes),
            Category("Television", R.drawable.television)
        )
        val adapterCategory = CategoryAdapter(categories)
        recyclerViewCategory.adapter = adapterCategory



//      Reward Adapter
        val recyclerViewReward: RecyclerView = findViewById(R.id.recyclerViewReward)
        recyclerViewReward.layoutManager = GridLayoutManager(this, 4)
        val rewards = listOf(
            Reward("Token", R.drawable.baseline_generating_tokens_24, "Beli token listrik"),
            Reward("Coupon Discount", R.drawable.baseline_discount_24, "Dapatkan Potongan harga"),
            Reward("Coin", R.drawable.baseline_monetization_on_24, "Kumpulkan hadiah coin"),
            Reward("Cashback", R.drawable.money_24, "Dapatkan hadiah cahback"),
        )
        recyclerViewReward.adapter = RewardAdapter(rewards)
    }
}
