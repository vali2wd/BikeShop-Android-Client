package com.example.bikeshopclient

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.bikeshopclient.databinding.ShopItemBinding

class BikeAdapter : RecyclerView.Adapter<BikeAdapter.BikeViewHolder>() {

    inner class BikeViewHolder(val binding: ShopItemBinding) : RecyclerView.ViewHolder(binding.root)

    private val differCallback = object : DiffUtil.ItemCallback<Data>() {
        override fun areItemsTheSame(oldItem: Data, newItem: Data): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Data, newItem: Data): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BikeViewHolder {
        return BikeViewHolder(ShopItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: BikeViewHolder, position: Int) {
        val bike = differ.currentList[position]
        holder.binding.apply {
            Glide.with(this.root)
                .load(bike.pictureUrl)
                .into(ivItemPic)
            tvTitle.text = bike.name
            tvPrice.text = '$' + bike.price.toString()
            tvProductBrand.text = bike.productBrand
            tvProductType.text = bike.productType
            tvDesc.text = bike.description
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }
}