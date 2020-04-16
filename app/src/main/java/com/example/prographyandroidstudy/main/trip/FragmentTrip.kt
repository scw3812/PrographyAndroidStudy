package com.example.prographyandroidstudy.main.trip

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.example.prographyandroidstudy.FavoriteActivity
import com.example.prographyandroidstudy.LocalDatabase
import com.example.prographyandroidstudy.R
import com.example.prographyandroidstudy.main.RemoteService
import com.google.android.material.appbar.AppBarLayout
import kotlinx.android.synthetic.main.fragment_trip.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class FragmentTrip : Fragment(), AppBarLayout.OnOffsetChangedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_trip, container, false)
        view.findViewById<AppBarLayout>(R.id.trip_appbar).addOnOffsetChangedListener(this)

        view.findViewById<TextView>(R.id.trip_favorite_text).setOnClickListener{
            startActivity(Intent(activity, FavoriteActivity::class.java))
        }

        val db = activity?.let { Room.databaseBuilder(it, LocalDatabase::class.java, "local_db")
            .build() }
        val bookmarkDao = db?.BookmarkDao()
        var body: TripData? = null
        var adapter: TripRecyclerAdapter?
        var recyclerView: RecyclerView? = null

        val retrofit = Retrofit.Builder().baseUrl("https://progserver.herokuapp.com")
            .addConverterFactory(GsonConverterFactory.create()).build()
        val result = retrofit.create(RemoteService::class.java).getTrips()
        result.enqueue(object : Callback<TripData> {
            override fun onFailure(call: Call<TripData>, t: Throwable) {

            }

            override fun onResponse(call: Call<TripData>, response: Response<TripData>) {
                if(response.isSuccessful){
                    body = response.body()!!
                    recyclerView = view.findViewById(R.id.trip_recycler)
                    val layoutManager = LinearLayoutManager(activity)
                    recyclerView?.setHasFixedSize(true)
                    layoutManager.orientation = LinearLayoutManager.HORIZONTAL
                    recyclerView?.layoutManager = layoutManager

                    lifecycleScope.launch(Dispatchers.IO){
                        for(b in bookmarkDao?.getAll()!!){
                            body!!.cities[b.id].isMark = true
                        }
                    }

                    adapter = TripRecyclerAdapter(activity, body!!,
                        object : BookmarkListener {
                            override fun clickBookmark(position: Int, isBookmark: Boolean) {
                                body!!.cities[position].isMark = isBookmark
                                lifecycleScope.launch(Dispatchers.IO){
                                    if(bookmarkDao?.getCity(position) == null){
                                        bookmarkDao?.insert(BookmarkEntity(position, isBookmark, body!!.cities[position].city, body!!.cities[position].url))
                                    }else{
                                        bookmarkDao.delete(bookmarkDao.getCity(position))
                                    }
                                }
                            }
                        })
                    recyclerView?.adapter = adapter
                }
            }
        })

//        if (bookmarkDao != null) {
//            activity?.let {
//                bookmarkDao.getAll().observe(it, Observer {
//                    Log.d("dk", it.toString())
//                })
//            }
//        }

        return view
    }

    override fun onOffsetChanged(appBarLayout: AppBarLayout?, verticalOffset: Int) {
        if(appBarLayout!!.totalScrollRange == 0 || verticalOffset == 0){
            trip_toolbar.alpha = 0f
            return
        }
        val ratio:Float = verticalOffset.toFloat() / appBarLayout.totalScrollRange.toFloat()
        trip_toolbar.alpha = Math.abs(ratio)
    }
}
