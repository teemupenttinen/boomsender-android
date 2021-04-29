package com.example.boomsenderandroid

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.boomsenderandroid.databinding.ActivityDeviceBinding
import com.example.boomsenderandroid.listviewadapter.ListViewAdapter

class DeviceActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDeviceBinding
    private val stringArray = mutableListOf("Power On", "Power Off", "HDMI 1", "HDMI 2")
    private var adapter: ListViewAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_MODE_CHANGED);

        binding = ActivityDeviceBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)


        binding.newDeviceAddCommandButton.setOnClickListener {
            val intent = Intent(this, CommandActivity::class.java)
            startActivityForResult(intent, 1)
        }

        adapter = ListViewAdapter(stringArray, this)

        adapter?.onDeleteListItem = {
            stringArray.removeAt(it)
            adapter?.notifyItemRemoved(it)
            adapter?.notifyItemRangeChanged(it, stringArray.count())
        }

        adapter?.onEditListItem = {
            val intent = Intent(this, CommandActivity::class.java)
            intent.putExtra("command_name", stringArray.elementAt(it))
            startActivityForResult(intent, 1)
            Log.d("OnEditListItem", "Index: $it")
        }

        val layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.newDeviceCommandsRecyclerView.addItemDecoration(
            DividerItemDecoration(
                binding.newDeviceCommandsRecyclerView.context,
                DividerItemDecoration.VERTICAL
            )
        )

        binding.newDeviceCommandsRecyclerView.layoutManager = layoutManager
        binding.newDeviceCommandsRecyclerView.adapter = adapter

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode == RESULT_OK) {
            data?.getStringExtra("command_name")?.let {
                Log.d("OnActivityResult", "Index: $it")
                stringArray.add(it)
                adapter?.notifyDataSetChanged()
            }
        }

    }
}