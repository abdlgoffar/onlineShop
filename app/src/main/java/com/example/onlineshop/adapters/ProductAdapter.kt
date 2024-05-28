package com.example.onlineshop.adapters
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.onlineshop.R
import com.example.onlineshop.models.data.Product


// OLD CLASS ADAPTER

//class ProductAdapter(private val productList: List<Product>) :
//    RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {
//
//    class ProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
//        val imageViewProduct: ImageView = itemView.findViewById(R.id.imageViewProduct)
//        val textViewProductName: TextView = itemView.findViewById(R.id.textViewProductName)
//        val textViewProductPrice: TextView = itemView.findViewById(R.id.textViewProductPrice)
//    }
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
//        val itemView = LayoutInflater.from(parent.context)
//            .inflate(R.layout.item_product, parent, false)
//        return ProductViewHolder(itemView)
//    }
//
//    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
//        val currentProduct = productList[position]
//        holder.textViewProductName.text = currentProduct.name
//        holder.textViewProductPrice.text = currentProduct.price
//        Glide.with(holder.itemView.context)
//            .load(currentProduct.imageUrl)
//            .into(holder.imageViewProduct)
//    }
//
//    override fun getItemCount() = productList.size
//}


//NEW CLASS ADAPTER
class ProductAdapter(private val productList: List<Product>) :
    RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    // Listener untuk menangani klik pada setiap item
    private var onItemClickListener: ((Product) -> Unit)? = null

    // Metode untuk menetapkan listener
    fun setOnItemClickListener(listener: (Product) -> Unit) {
        onItemClickListener = listener
    }

    inner class ProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageViewProduct: ImageView = itemView.findViewById(R.id.imageViewProduct)
        val textViewProductName: TextView = itemView.findViewById(R.id.textViewProductName)
        val textViewProductPrice: TextView = itemView.findViewById(R.id.textViewProductPrice)

        init {
            // Menambahkan onClickListener ke itemView (item dalam recyclerView)
            itemView.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val product = productList[position]
                    // Memanggil listener saat item diklik dan meneruskan objek Product yang dipilih
                    onItemClickListener?.invoke(product)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_product, parent, false)
        return ProductViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val currentProduct = productList[position]
        holder.textViewProductName.text = currentProduct.name
        holder.textViewProductPrice.text = currentProduct.price
        Glide.with(holder.itemView.context)
            .load(currentProduct.imageUrl)
            .into(holder.imageViewProduct)
    }

    override fun getItemCount() = productList.size
}
