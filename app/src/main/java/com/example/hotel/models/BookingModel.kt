package com.example.hotel.models

data class BookingModel(
    var id: String,
    var email: String,
    var name: String,
    var secName: String,
    var date: String,
    var hotelId: Int
)