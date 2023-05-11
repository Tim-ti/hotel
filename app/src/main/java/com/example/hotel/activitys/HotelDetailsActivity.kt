package com.example.hotel.activitys

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.example.hotel.R
import com.example.hotel.bitmapFromBase64
import com.example.hotel.databinding.ActivityHotelDetailsBinding
import com.example.hotel.databinding.ActivityMainBinding

class HotelDetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHotelDetailsBinding



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHotelDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.ViewBook.setOnClickListener {
            startActivity(Intent(this, BookingActivity::class.java))
            finish()
        }
        setValuesToViews()
    }

    private fun setValuesToViews() {
        binding.ViewTitle.text = intent.getStringExtra("name")
        binding.ViewLocation.text = intent.getStringExtra("location")
        binding.ViewRating.text = intent.getStringExtra("rating")
        binding.ViewFeatures.text = intent.getStringExtra("features")
        binding.ViewContact.text = intent.getStringExtra("contact")
        binding.imageView2.setImageBitmap(bitmapFromBase64(intent.getStringExtra("image")))

    }
}