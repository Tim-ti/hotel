package com.example.hotel.activitys

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hotel.R
import com.example.hotel.adapters.HotelAdapter
import com.example.hotel.databinding.ActivityHotelMenuBinding
import com.example.hotel.models.HotelModel
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

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.tool_bar, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle item selection
        return when (item.itemId) {
            R.id.show_bookings -> {
                startActivity(Intent(this, BookingHistoryActivity::class.java))
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}