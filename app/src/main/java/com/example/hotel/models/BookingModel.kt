package com.example.hotel.models

data class BookingModel(
    var bookingId: String,
    var email: String,
    var name: String,
    var secName: String
){
    constructor(): this("", "", "", "")
}