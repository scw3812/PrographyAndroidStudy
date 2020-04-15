package com.example.prographyandroidstudy.main.trip

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.prographyandroidstudy.LocalDatabase
import com.example.prographyandroidstudy.R
import com.example.prographyandroidstudy.main.RemoteService
import com.google.android.material.appbar.AppBarLayout
import kotlinx.android.synthetic.main.fragment_trip.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class FragmentTrip : Fragment(), AppBarLayout.OnOffsetChangedListener {

    private var db:LocalDatabase? = null

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

        var body:TripData? = null
        val retrofit = Retrofit.Builder().baseUrl("https://progserver.herokuapp.com")
            .addConverterFactory(GsonConverterFactory.create()).build()
        val result = retrofit.create(RemoteService::class.java).getTrips()
        result.enqueue(object : Callback<TripData>{
            override fun onFailure(call: Call<TripData>, t: Throwable) {

            }

            override fun onResponse(call: Call<TripData>, response: Response<TripData>) {
                if(response.isSuccessful){
                    body = response.body()
                }
            }
        })

        db = activity?.let { LocalDatabase.getInstance(it) }

        val r = Runnable {

        }
        val thread = Thread(r)
        thread.start()

        val recyclerView = view.findViewById<RecyclerView>(R.id.trip_recycler)
        val layoutManager = LinearLayoutManager(activity)
        recyclerView.setHasFixedSize(true)
        layoutManager.orientation = LinearLayoutManager.HORIZONTAL
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = body?.let { TripRecyclerAdapter(activity, it) }

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
