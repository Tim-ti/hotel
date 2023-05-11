package com.example.hotel

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Base64

fun bitmapFromBase64(base64: String?): Bitmap? {
    if( base64 == null) return null
    val imageBytes = Base64.decode(base64, Base64.DEFAULT)
    return BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.size)
}