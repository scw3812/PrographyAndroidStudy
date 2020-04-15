package com.example.prographyandroidstudy.main.trip

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.prographyandroidstudy.R

class TripRecyclerAdapter(val context: Context?, private val tripList: TripData): RecyclerView.Adapter<TripRecyclerAdapter.TripViewHolder>() {
    class TripViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val textView:TextView = itemView.findViewById(R.id.trip_recycler_text)
        val imageView: ImageView = itemView.findViewById(R.id.trip_recycler_image)
        val bookmark: ImageView = itemView.findViewById(R.id.trip_recycler_bookmark)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TripViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.trip_row, parent, false)
        return TripViewHolder(
            view
        )
    }

    override fun getItemCount(): Int {
        return tripList.cities.size
    }

    override fun onBindViewHolder(holder: TripViewHolder, position: Int) {
        holder.textView.text = tripList.cities[position].city
        context?.let { Glide.with(it).load(tripList.cities[position].url).placeholder(
            R.drawable.river
        ).into(holder.imageView) }
        holder.bookmark.setOnClickListener {
            holder.bookmark.setImageResource(R.drawable.ic_star_white_18dp)
        }
    }
}