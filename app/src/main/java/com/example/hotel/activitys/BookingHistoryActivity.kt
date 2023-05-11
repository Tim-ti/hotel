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
import com.example.hotel.models.BookingModel
import com.google.firebase.database.*

class BookingHistoryActivity : AppCompatActivity() {
    private lateinit var histRecyclerView: RecyclerView
    private lateinit var tvLoadingData: TextView
    private lateinit var histList: ArrayList<BookingModel>
    private lateinit var dbRef: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_booking_history)

        histRecyclerView = findViewById(R.id.rvEmp)
        histRecyclerView.layoutManager = LinearLayoutManager(this)
        histRecyclerView.setHasFixedSize(true)
        tvLoadingData = findViewById(R.id.tvLoadingData)

        histList = arrayListOf()

        getEmployeesData()

    }

    private fun getEmployeesData() {

        histRecyclerView.visibility = View.GONE
        tvLoadingData.visibility = View.VISIBLE

        dbRef = FirebaseDatabase.getInstance().getReference("Booking")

        dbRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                histList.clear()
                if (snapshot.exists()){
                    for (empSnap in snapshot.children){
                        val empData = empSnap.getValue(BookingModel::class.java)
                        histList.add(empData!!)
                    }
                    val mAdapter = HistAdapter(histList)
                    histRecyclerView.adapter = mAdapter

                    mAdapter.setOnItemClickListener(object : HistAdapter.onItemClickListener{
                        override fun onItemClick(position: Int) {

                            val intent = Intent(this@BookingHistoryActivity, HistoryDetailsActivity::class.java)


                            intent.putExtra("bookingId", histList[position].bookingId)
                            intent.putExtra("Email", histList[position].email)
                            intent.putExtra("Name", histList[position].name)
                            intent.putExtra("SecName", histList[position].secName)
                            startActivity(intent)
                        }

                    })

                    histRecyclerView.visibility = View.VISIBLE
                    tvLoadingData.visibility = View.GONE
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }
}
