package com.kumar.assignment_arachnomesh

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.kumar.assignment_arachnomesh.databinding.ActivityMainBinding
import com.kumar.assignment_arachnomesh.databinding.ActivitySecondBinding

class SecondActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySecondBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.nameTV.text = "Name: " + intent.getStringExtra("name").toString()
        binding.idTV.text = "ID: " + intent.getStringExtra("id").toString()
        binding.phoneTV.text = "Phone: " + intent.getStringExtra("phone").toString()
        binding.longitudeTV.text = "Longitude: " + intent.getStringExtra("longitude").toString()
        binding.latitudeTV.text = "Latitude: " + intent.getStringExtra("latitude").toString()
    }
}