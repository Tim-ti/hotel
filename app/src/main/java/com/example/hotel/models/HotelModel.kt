package com.example.hotel.models

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Base64
import org.json.JSONException
import org.json.JSONObject


class HotelModel(
    var name: String,
    var contact: String,
    var location: String,
    var features: String,
    var rating: String,
    var image: String?,
    var id: Int
) {
    constructor(json: JSONObject) : this("", "", "", "", "", null, 0) {
        id = json.getInt("id")
        rating = json.getString("rating")
        features = json.getString("features")
        location = json.getString("location")
        contact = json.getString("contact")
        name = json.getString("name")

        try {
            image = json.getString("image")
        }catch (ignored: JSONException){
        }
    }
}
