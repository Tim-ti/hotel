package com.example.hotel.adapters

import android.graphics.BitmapFactory
import android.util.Base64
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.hotel.R
import com.example.hotel.models.HotelModel

class HotelAdapter (private val hotelList: ArrayList<HotelModel>) :
    RecyclerView.Adapter<HotelAdapter.HotelViewHolder>() {

    private lateinit var mListener: onItemClickListener

    interface onItemClickListener{
        fun onItemClick(position: Int)
    }

    fun setOnItemClickListener(clickListener: onItemClickListener){
        mListener = clickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HotelViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.hotel_card, parent, false)
        return HotelViewHolder(itemView, mListener)
    }

    override fun onBindViewHolder(holder: HotelViewHolder, position: Int) {
        val currentEmp = hotelList[position]
        val title = holder.itemView.findViewById<TextView>(R.id.title)
        val rating = holder.itemView.findViewById<TextView>(R.id.rating)
        val location = holder.itemView.findViewById<TextView>(R.id.location)
        val imageView = holder.itemView.findViewById<ImageView>(R.id.thumbnail)
        title.text = currentEmp.name
        rating.text = currentEmp.rating
        location.text = currentEmp.location

        if(currentEmp.image != null) {
            println("AAAAAAAAAAAA")
            println(currentEmp.image)
            val imageBytes = Base64.decode(currentEmp.image, Base64.DEFAULT)
            val decodedImage = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.size)
            imageView.setImageBitmap(decodedImage)
        } else {
            imageView.setImageBitmap(null)
        }
    }

    override fun getItemCount(): Int {
        return hotelList.size
    }

    class HotelViewHolder(val itemView: View, var clickListener: onItemClickListener) : RecyclerView.ViewHolder(itemView){
        init {
            val button = itemView.findViewById<Button>(R.id.viewbutton)
            button.setOnClickListener {
                clickListener.onItemClick(adapterPosition)
            }
        }
    }
}