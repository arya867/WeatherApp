package com.example.weatherapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.weatherapp.adapter.FavoritesAdapter
import com.example.weatherapp.data.models.FavoriteLocation
import com.example.weatherapp.databinding.ActivityFavoritesBinding
import com.example.weatherapp.utils.SharedPrefUtil

class FavoritesActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFavoritesBinding
    private lateinit var adapter: FavoritesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoritesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val favorites = SharedPrefUtil.getFavorites(this).toMutableList()

        adapter = FavoritesAdapter(
            favorites,
            onItemClick = { location ->
                // Kirimkan data kota yang dipilih ke MainActivity
                val intent = Intent()
                intent.putExtra("selected_city", location.city)
                setResult(RESULT_OK, intent)
                finish() // Kembali ke MainActivity
            },
            onDeleteClick = { location ->
                val position = favorites.indexOf(location)
                if (position != -1) {
                    favorites.removeAt(position)
                    SharedPrefUtil.saveFavorites(this, favorites)
                    adapter.notifyItemRemoved(position)
                }
            }
        )

        binding.recyclerViewFavorites.adapter = adapter
        binding.recyclerViewFavorites.layoutManager = LinearLayoutManager(this)
    }
}
