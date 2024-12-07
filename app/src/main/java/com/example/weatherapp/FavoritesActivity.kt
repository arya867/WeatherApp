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

//        adapter = FavoritesAdapter(
//            favorites,
//            onItemClick = { favorite ->
//                // Kirimkan data kota yang dipilih ke MainActivity
//                val resultIntent = Intent().apply {
//                    putExtra("selected_city", favorite.city)
//                }
//                setResult(RESULT_OK, resultIntent)
//                finish() // Kembali ke MainActivity
//            },
//            onDeleteClick = { favorite ->
//                val position = favorites.indexOf(favorite)
//                if (position != -1) {
//                    favorites.removeAt(position)
//                    SharedPrefUtil.saveFavorites(this, favorites)
//                    adapter.notifyItemRemoved(position)
//                }
//            }
//        )
//        binding.recyclerViewFavorites.adapter = adapter

        binding.recyclerViewFavorites.adapter = FavoritesAdapter(
            favorites,
            onItemClick = { favorite ->
                // Kirim data kembali ke MainActivity
                val resultIntent = Intent().apply {
                    putExtra("selected_city", favorite.country)
                }
                setResult(RESULT_OK, resultIntent)
                finish()
            },
            onDeleteClick = { favorite ->
                val position = favorites.indexOf(favorite)
                if (position != -1) {
                    favorites.removeAt(position)
                    SharedPrefUtil.saveFavorites(this, favorites)
                    adapter.notifyItemRemoved(position)
                }
            }
        )

        binding.recyclerViewFavorites.layoutManager = LinearLayoutManager(this)

    }
}
