package com.example.prographyandroidstudy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import androidx.room.Room
import kotlinx.android.synthetic.main.activity_favorite.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FavoriteActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorite)

        val db = Room.databaseBuilder(this, LocalDatabase::class.java, "local_db").build()

        lifecycleScope.launch(Dispatchers.IO){
            favorite_text.setText(db.BookmarkDao().getAll().toString())
        }
    }
}
