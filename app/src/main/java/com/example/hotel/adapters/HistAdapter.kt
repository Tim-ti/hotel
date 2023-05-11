package com.example.hotel.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.hotel.R
import com.example.hotel.models.BookingModel

class HistAdapter (private val histList: ArrayList<BookingModel>) :
    RecyclerView.Adapter<HistAdapter.BookingViewHolder>() {

    private lateinit var mListener: onItemClickListener

    interface onItemClickListener{
        fun onItemClick(position: Int)
    }

    fun setOnItemClickListener(clickListener: onItemClickListener){
        mListener = clickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookingViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.emp_list_item, parent, false)
        return BookingViewHolder(itemView, mListener)
    }

    override fun onBindViewHolder(holder: BookingViewHolder, position: Int) {
        val currentEmp = histList[position]
        holder.tvEmpName.text = currentEmp.name
    }

    override fun getItemCount(): Int {
        return histList.size
    }

    class BookingViewHolder(itemView: View, clickListener: onItemClickListener) : RecyclerView.ViewHolder(itemView) {

        val tvEmpName : TextView = itemView.findViewById(R.id.tvEmpName)

        init {
            itemView.setOnClickListener {
                clickListener.onItemClick(adapterPosition)
            }
        }

    }

}