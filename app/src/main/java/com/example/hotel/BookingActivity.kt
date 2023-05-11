package com.example.hotel

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.hotel.databinding.ActivityBookingBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class BookingActivity : AppCompatActivity() {
    private lateinit var binding: ActivityBookingBinding

    private lateinit var dbRef : DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_booking)

        binding = ActivityBookingBinding.inflate(layoutInflater)
        setContentView(binding.root)
        dbRef =FirebaseDatabase.getInstance().getReference("Booking")

        binding.save.setOnClickListener {
            val email = binding.edEmail.text.toString()
            val name = binding.edName.text.toString()
            val secName = binding.edSecName.text.toString()

            if(email.isEmpty()){
                binding.edEmail.error = "Please enter Email"
            }
            if(name.isEmpty()){
                binding.edName.error = "Please enter Name"
            }
            if(secName.isEmpty()){
                binding.edSecName.error = "Please enter Second name"
            }

            val bookingId = dbRef.push().key!!

            val booking =BookingModel(bookingId,email,name, secName)

            dbRef.child(bookingId).setValue(booking)
                .addOnCompleteListener{
                    if(it.isSuccessful){
                        startActivity(Intent(this, HotelMenu::class.java))
                        finish()
                    }
                }.addOnFailureListener {
                    Toast.makeText(this, it.localizedMessage, Toast.LENGTH_LONG).show()
                }

        }



        }

    }

