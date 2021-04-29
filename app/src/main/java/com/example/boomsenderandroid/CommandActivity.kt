package com.example.boomsenderandroid

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import com.example.boomsenderandroid.databinding.ActivityCommandBinding

class CommandActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCommandBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        binding = ActivityCommandBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val commandName = intent.getStringExtra("command_name")
        commandName?.let {
            binding.newCommandNameEditText.setText(it)
        }

        binding.newCommandAddButton.setOnClickListener {
            val intent = Intent()
            intent.putExtra("command_name", binding.newCommandNameEditText.text.toString())
            intent.putExtra("command", binding.newCommandEditText.text.toString())
            setResult(RESULT_OK, intent)
            finish()
        }
    }
}