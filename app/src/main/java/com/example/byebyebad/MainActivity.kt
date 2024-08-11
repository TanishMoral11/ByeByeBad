package com.example.byebyebad

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.example.byebyebad.databinding.ActivityMainBinding
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding

    private lateinit var appDatabase: AppDatabase
    private lateinit var mistakeDao: MistakeDao
    private lateinit var adapter: MistakeAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        appDatabase = AppDatabase.getDatabase(this)
        mistakeDao = appDatabase.mistakeDao()

        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val fabAdd = findViewById<FloatingActionButton>(R.id.fab_add)

        CoroutineScope(Dispatchers.IO).launch {
            val mistakes = mistakeDao.getAllMistakes()
            runOnUiThread {
                adapter = MistakeAdapter(mistakes, mistakeDao)
                recyclerView.adapter = adapter
            }
        }

        binding.fabAdd.setOnClickListener {
            // Navigate to AddNoteActivity
            val intent = Intent(this, AddMistakeActivity::class.java)
            startActivity(intent)
        }
    }
}
