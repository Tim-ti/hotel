package com.example.hotel.activitys

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.hotel.R
import com.example.hotel.adapters.HistAdapter
import com.example.hotel.adapters.HotelAdapter
import com.example.hotel.databinding.ActivityHotelDetailsBinding
import com.example.hotel.databinding.ActivityHotelMenuBinding
import com.example.hotel.models.BookingModel
import com.example.hotel.models.HotelModel
import com.google.firebase.database.DatabaseReference

import org.json.JSONArray


class HotelMenuActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHotelMenuBinding
    private val hotelList = arrayListOf<HotelModel>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHotelMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.rvEmp.layoutManager = LinearLayoutManager(this)
        binding.rvEmp.setHasFixedSize(true)


        getEmployeesData()
    }


    private fun getEmployeesData() {
        val fileInString: String =
            applicationContext.assets.open("hotels.json").bufferedReader().use { it.readText() }
        val json = JSONArray(fileInString)

        for (i in 0 until json.length())
            hotelList.add(HotelModel(json.getJSONObject(i)))


        val mAdapter = HotelAdapter(hotelList)
        binding.rvEmp.adapter = mAdapter

        mAdapter.setOnItemClickListener(object : HotelAdapter.onItemClickListener {
            override fun onItemClick(position: Int) {

                val intent = Intent(this@HotelMenuActivity, HotelDetailsActivity::class.java)


                intent.putExtra("id", hotelList[position].id)
                intent.putExtra("rating", hotelList[position].rating)
                intent.putExtra("features", hotelList[position].features)
                intent.putExtra("location", hotelList[position].location)
                intent.putExtra("contact", hotelList[position].contact)
                intent.putExtra("name", hotelList[position].name)
                intent.putExtra("image", hotelList[position].image)
                startActivity(intent)
            }

        })


    }


}