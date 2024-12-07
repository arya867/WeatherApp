package com.example.weatherapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.data.models.FavoriteLocation
import com.example.weatherapp.databinding.ItemFavoriteBinding

class FavoritesAdapter(
    private val favorites: MutableList<FavoriteLocation>,
    private val onItemClick: (FavoriteLocation) -> Unit,
    private val onDeleteClick: (FavoriteLocation) -> Unit
) : RecyclerView.Adapter<FavoritesAdapter.FavoriteViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
        val binding = ItemFavoriteBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FavoriteViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        val favorite = favorites[position]
        holder.bind(favorite)

        // Tambahkan klik listener
        holder.itemView.setOnClickListener {
            onItemClick(favorite)
        }
    }

    override fun getItemCount(): Int = favorites.size

    inner class FavoriteViewHolder(private val binding: ItemFavoriteBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(favoriteLocation: FavoriteLocation) {
            binding.tvCity.text = favoriteLocation.city
            binding.tvCountry.text = favoriteLocation.country
            binding.tvTime.text = favoriteLocation.time
            binding.tvTemperature.text = favoriteLocation.temperature

            binding.root.setOnClickListener {
                onItemClick(favoriteLocation)
            }

            binding.btnDelete.setOnClickListener {
                onDeleteClick(favoriteLocation)
            }
        }
    }
}