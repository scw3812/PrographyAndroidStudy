package com.example.prographyandroidstudy.chat

import android.content.Context
import android.graphics.Color
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.prographyandroidstudy.R
import kotlinx.android.synthetic.main.chat_row.view.*


class ChatRecyclerAdapter(val context: Context, val chatList: List<ChatData>): RecyclerView.Adapter<ChatRecyclerAdapter.MyChatViewHolder>() {

    class MyChatViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
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
        if(chatData.userId == "me"){
            holder.tripTextView.visibility = View.GONE
            holder.thumbUp.visibility = View.GONE
            holder.thumbDown.visibility = View.GONE
            holder.messageTextView.setBackgroundResource(R.drawable.corner_radius_chat_me)
            holder.messageTextView.setTextColor(Color.WHITE)
            val size = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,50f,context.resources.displayMetrics).toInt()
            val layoutParamsImage = ConstraintLayout.LayoutParams(size, size)
            layoutParamsImage.rightToRight = ConstraintLayout.LayoutParams.PARENT_ID
            layoutParamsImage.topToTop = ConstraintLayout.LayoutParams.PARENT_ID
            holder.chatImage.layoutParams = layoutParamsImage
            val layoutParamsText = ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.WRAP_CONTENT, ConstraintLayout.LayoutParams.WRAP_CONTENT)
            layoutParamsText.rightToLeft = R.id.recycler_image
            layoutParamsText.bottomToBottom = ConstraintLayout.LayoutParams.PARENT_ID
            holder.messageTextView.layoutParams = layoutParamsText
        }
        holder.messageTextView.text = chatData.message
    }
}