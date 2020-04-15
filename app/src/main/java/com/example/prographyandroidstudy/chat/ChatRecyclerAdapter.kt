package com.example.prographyandroidstudy.chat

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.prographyandroidstudy.R
import kotlinx.android.synthetic.main.chat_row.view.*

class ChatRecyclerAdapter(val context: Context, val chatList: List<ChatData>): RecyclerView.Adapter<ChatRecyclerAdapter.MyChatViewHolder>() {

    class MyChatViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val recyclerLayout = itemView.recycler_layout
        val tripTextView = itemView.recycler_text_tripcom
        val messageTextView = itemView.recycler_message
        val chatImage = itemView.recycler_image
        val thumbUp = itemView.recycler_up
        val thumbDown = itemView.recycler_down
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyChatViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.chat_row, parent, false)
        return MyChatViewHolder(
            view
        )
    }

    override fun getItemCount(): Int {
        return chatList.size
    }

    override fun onBindViewHolder(holder: MyChatViewHolder, position: Int) {
        val chatData = chatList.get(position)
        if(chatData.isMe){
            holder.tripTextView.visibility = View.GONE
            holder.thumbUp.visibility = View.GONE
            holder.thumbDown.visibility = View.GONE
        }
        holder.messageTextView.text = chatData.message
    }
}