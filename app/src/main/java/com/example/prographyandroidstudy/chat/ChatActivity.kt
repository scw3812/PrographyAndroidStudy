package com.example.prographyandroidstudy.chat

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.prographyandroidstudy.R
import com.github.nkzawa.emitter.Emitter
import com.github.nkzawa.socketio.client.IO
import com.github.nkzawa.socketio.client.Socket
import kotlinx.android.synthetic.main.activity_chat.*
import org.json.JSONObject
import java.net.URISyntaxException
import kotlin.reflect.typeOf

class ChatActivity : AppCompatActivity() {

    lateinit var socket: Socket

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)

        val list = mutableListOf<ChatData>()
        var adapter = ChatRecyclerAdapter(
            this,
            list
        )

        chat_recycler.layoutManager = LinearLayoutManager(this)
        chat_recycler.adapter = adapter

        try{
            socket = IO.socket("https://prography6androidstudychat.herokuapp.com")
        }catch (e : URISyntaxException){
            e.printStackTrace()
        }
        socket.connect()
        var flag = true
        socket.on(Socket.EVENT_CONNECT, Emitter.Listener {
            socket.emit("login", "me")
            Log.d("login", "connected with me")
        })

        chat_send_btn.setOnClickListener {
            val msg = chat_edit_text.text
            if(!flag){
                socket.emit("login", "me")
                flag = true
            }
            socket.emit("chat", msg)
            if(flag){
                socket.emit("login", "you")
                flag = false
            }
            socket.emit("chat", "reply: $msg")
        }

        socket.on("chat", object: Emitter.Listener {
            override fun call(vararg args: Any?) {
                val chat = (args[0] as String).split(" : ")
                val chatData = ChatData(chat[1], chat[0])
                list.add(chatData)
                runOnUiThread {
                    Log.d("chat", list.toString())
                    adapter.notifyDataSetChanged()
                }
            }
        })

        setSupportActionBar(chat_toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            android.R.id.home -> {
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
