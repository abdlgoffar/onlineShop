package com.example.onlineshop.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.onlineshop.R
import com.example.onlineshop.models.data.Reward

class RewardAdapter(private val rewardList: List<Reward>) : RecyclerView.Adapter<RewardAdapter.RewardViewHolder>() {

    class RewardViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgReward: ImageView = itemView.findViewById(R.id.imgReward)
        val nameReward: TextView = itemView.findViewById(R.id.nameReward)
        val descriptionReward: TextView = itemView.findViewById(R.id.descriptionReward)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RewardViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_reward, parent, false)
        return RewardViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: RewardViewHolder, position: Int) {
        val currentItem = rewardList[position]
        holder.imgReward.setImageResource(currentItem.img)
        holder.nameReward.text = currentItem.name
        holder.descriptionReward.text = currentItem.description
    }

    override fun getItemCount() = rewardList.size
}
