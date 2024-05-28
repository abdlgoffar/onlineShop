package com.example.onlineshop

import android.content.Intent
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
            "https://media.istockphoto.com/id/1213742276/id/foto/wanita-arab-dengan-tas-belanja-berbicara-di-ponsel-di-jalan-kota.jpg?s=612x612&w=0&k=20&c=oqUcL77wHlt8U7NdALohTCX1EHIEkNcw3THZ2Y-_0fs="  )
        val adapter = SliderAdapter(imageUrls)
        viewPager.adapter = adapter


//      Products Adapter
        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = GridLayoutManager(this, 2)
        val productList = listOf(
            Product("GELANG TASBIH 33BUTIR KAYU CENDANA WANGI PROFIL 100% NATURAL", "https://images.tokopedia.net/img/cache/200-square/VqbcmM/2021/9/18/bd58e2e3-0ff5-462b-af33-0966ca25f9ea.jpg", "Rp.100.000.00"),
            Product("TASBIH KAYU JUMBO CENDANA 12MM 99BUTIR NATURAL", "https://images.tokopedia.net/img/cache/250-square/VqbcmM/2021/9/24/986ff978-02d0-4488-be95-28034dcc0080.jpg", "Rp.35.000.00"),
            Product("Gelang Tasbih Kayu Cendana Asli", "https://images.tokopedia.net/img/cache/250-square/product-1/2018/3/14/21952058/21952058_3154801e-84c8-48b2-a287-b4f9da1da0fe_800_800.jpg", "Rp.3000.00"),
            Product("HANYA HARI INI PROMO!! SURBAN BABAGAF KASHMIRI SORBAN PRIA DEWASA", "https://images.tokopedia.net/img/cache/200-square/hDjmkQ/2023/12/11/56965cac-bb63-4a8b-b5b8-2a6ff3383188.jpg", "RP.350.000.00"),
            Product("Sorban Arafat Surban Haji Paling Murah - Putih Polos", "https://images.tokopedia.net/img/cache/200-square/product-1/2020/9/29/83983058/83983058_497b600a-9132-4ec6-b733-797197ba529f_2048_2048", "Rp.200.000.00"),
            Product("Gamis Pria Dewasa Kancing Import Fadlan Series Assuffah - Putih, S", "https://images.tokopedia.net/img/cache/200-square/VqbcmM/2023/7/9/24959bc1-5a13-4f64-9f2d-6f56f449d819.jpg", "Rp.1000.000.00"),
            Product("AL Haramain Jubah / Gamis Pria Dewasa RS Premium Zanzibar (09) | V1 - Navy, 54/24", "https://images.tokopedia.net/img/cache/200-square/VqbcmM/2024/1/29/7548745d-c8c6-40bb-a25d-316813ec88c6.jpg", "Rp.33.000.00"),
            Product("Gamis Lebaran Cantik Buat Ke Rumah Mertua", "https://images.tokopedia.net/img/cache/200-square/VqbcmM/2024/2/10/211b85de-19b9-412b-adfa-5590d2cea0c8.jpg", "Rp.100.000.00"),
            Product("Gamis Warna Army Ijo", "https://images.tokopedia.net/img/cache/200-square/VqbcmM/2024/2/9/845e4d01-21f0-4e16-992c-59232b8f5ddc.jpg", "Rp.133.000.00"),
            Product("Gamis ENHA Abaya Wanita Terbaru Bahan Rayon Crienkle Premium Ld 110 cm - mustard", "https://images.tokopedia.net/img/cache/200-square/VqbcmM/2024/2/11/90fe90a3-0d12-42a6-88e6-b767dbf4abd4.jpg", "Rp.156.000.00"),
            Product("Sajadah Turki Dewasa", "https://images.tokopedia.net/img/cache/200-square/VqbcmM/2024/4/29/e307e30b-2c20-4414-a7a0-d80580353f2a.jpg", "Rp.56.000.00"),
            Product("Sajadah Kubah Busa", "https://images.tokopedia.net/img/cache/200-square/VqbcmM/2023/1/4/87278bb1-b0a4-46d0-82d5-a09f078da42f.jpg", "Rp.44.000.00"),
            Product("Peci Anak Buat Ngaji Di Masjid", "https://images.tokopedia.net/img/cache/200-square/product-1/2018/2/23/0/0_204cd9e6-26b5-4b60-a988-d0e24bc5789a_1440_1440.jpg", "78.000.00"),
            Product("Songkok Asagosah Spons Anak", "https://images.tokopedia.net/img/cache/200-square/VqbcmM/2022/10/8/2d63e95b-8f19-47fd-aa4c-9de451cd10c3.jpg", "Rp.45.000.00"),
            Product("Songkok Pria Dewasa Model Palestine", "https://images.tokopedia.net/img/cache/200-square/VqbcmM/2023/9/1/dbccfc81-08d3-4a66-a907-f681f883f812.jpg", "Rp.5.000.00"),
            Product("Hiab Segi Enam Cewek Pondokan", "https://images.tokopedia.net/img/cache/200-square/product-1/2020/3/6/32108236/32108236_c65b0d24-3d5f-457d-a031-35fea9a0c5cb_1000_1000", "Rp.76.000.00"),
            Product("Hijab Arabian", "https://images.tokopedia.net/img/cache/200-square/hDjmkQ/2023/3/1/8c43d017-4f92-42a5-b9bb-01798c4071aa.jpg", "Rp.900.000.00")
        )
        recyclerView.adapter = ProductAdapter(productList)

        val productAdapter = recyclerView.adapter as ProductAdapter

        productAdapter.setOnItemClickListener { product ->
            val intent = Intent(this, ProductDetailActivity::class.java)
            intent.putExtra("product", product)
            startActivity(intent)
        }



//      Categories Adapter
        val recyclerViewCategory: RecyclerView = findViewById(R.id.recycler_view_category)
        recyclerViewCategory.layoutManager = GridLayoutManager(this, 5)
        val categories = listOf(

//            Category("Bag", R.drawable.bag),
//            Category("clothes", R.drawable.clothes),
//            Category("Computer", R.drawable.computer),
//            Category("Glasses", R.drawable.glasses),
//            Category("Bowl", R.drawable.bowl),
//            Category("Dress", R.drawable.dress),
//            Category("Hat", R.drawable.hat),
//            Category("Lipstick", R.drawable.ipstick),
//            Category("Shoes", R.drawable.shoes),
//            Category("Television", R.drawable.television)
            Category("Muslim pria", R.drawable.man_clothes),
            Category("Tasbih", R.drawable.tasbih),
            Category("Sajadah", R.drawable.sajadah),
            Category("Surban", R.drawable.cap_surban),
            Category("Gelang tasbih", R.drawable.gelang_tasbih),
            Category("Muslim wanita", R.drawable.woman_clothes),
            Category("Songkok", R.drawable.cap_man),
            Category("Hijab", R.drawable.hijab),
            Category("Songkok Anak", R.drawable.cap_kids),
            Category("Surban kasmiri", R.drawable.kasmiri_surban),
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
