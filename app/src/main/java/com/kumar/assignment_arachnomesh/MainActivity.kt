package com.kumar.assignment_arachnomesh

import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.kumar.assignment_arachnomesh.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var locationProvider: FusedLocationProviderClient
    private var latitude: String = ""
    private var longitude: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        locationProvider = LocationServices.getFusedLocationProviderClient(this)

        binding.sendButton.setOnClickListener {
            val intent = Intent(this, SecondActivity::class.java)

            intent.putExtra("name", binding.editTextName.text.toString())
            intent.putExtra("id", binding.editTextId.text.toString())
            intent.putExtra("phone", binding.editTextPhone.text.toString())

            getLocation()

            intent.putExtra("latitude", latitude)
            intent.putExtra("longitude", longitude)

            var phone = binding.editTextPhone.text.toString()
            if(phone.length != 10)
                Toast.makeText(this, "Invalid Phone Number", Toast.LENGTH_SHORT).show()
            else {
                startActivity(intent)
                Toast.makeText(this, "Form submitted", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun getLocation(){
        if(
            ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
            ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
            ){
                ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION, android.Manifest.permission.ACCESS_COARSE_LOCATION), 100)
                return
        }

        val location = locationProvider.lastLocation
        location.addOnSuccessListener {
            if(it != null){
                latitude = it.latitude.toString()
                longitude = it.longitude.toString()
            }
        }
    }
}