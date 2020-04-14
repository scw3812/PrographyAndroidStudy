package com.example.prographyandroidstudy

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class TripRecyclerAdapter(val context: Context?, val tripList: List<TripData>): RecyclerView.Adapter<TripRecyclerAdapter.TripViewHolder>() {
    class TripViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val textView:TextView = itemView.findViewById(R.id.trip_recycler_text)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TripViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.trip_row, parent, false)
        return TripViewHolder(view)
    }

    override fun getItemCount(): Int {
        return tripList.size
    }

    override fun onBindViewHolder(holder: TripViewHolder, position: Int) {
        holder.textView.setText(tripList.get(position).tripName)
    }
}