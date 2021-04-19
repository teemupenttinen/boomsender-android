package com.example.boomsenderandroid

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.boomsenderandroid.databinding.ActivityMainBinding
import com.example.boomsenderandroid.listviewadapter.ListViewAdapter


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val stringArray = mutableListOf<String>("Projector 1", "Projector 2", "TV 1", "TV 2", "Switcher 1", "Audio Processor 1")

        val adapter = ListViewAdapter(stringArray, this)

        adapter.onDeleteListItem = {
            adapter.notifyItemRemoved(it)
            stringArray.removeAt(it)
        }

        adapter.onEditListItem = {
            Log.d("OnEditListItem", "Index: $it")
        }

        val layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.devicesListView.addItemDecoration(
            DividerItemDecoration(
                binding.devicesListView.context,
                DividerItemDecoration.VERTICAL
            )
        )

        binding.devicesListView.layoutManager = layoutManager
        binding.devicesListView.adapter = adapter
    }
}