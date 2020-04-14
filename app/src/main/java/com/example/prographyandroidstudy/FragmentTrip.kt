package com.example.prographyandroidstudy

import android.app.ActionBar
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.fragment_account.*
import kotlinx.android.synthetic.main.fragment_trip.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [FragmentTrip.newInstance] factory method to
 * create an instance of this fragment.
 */
class FragmentTrip : Fragment(), AppBarLayout.OnOffsetChangedListener {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_trip, container, false)
        view.findViewById<AppBarLayout>(R.id.trip_appbar).addOnOffsetChangedListener(this)
        val recyclerView = view.findViewById<RecyclerView>(R.id.trip_recycler)
        val layoutManager = LinearLayoutManager(container?.context)
        layoutManager.orientation = LinearLayoutManager.HORIZONTAL
        recyclerView.layoutManager = layoutManager
        var list : List<TripData> = arrayListOf<TripData>(
            TripData("서울"),
            TripData("제주"),
            TripData("부산"),
            TripData("강원")
        )
        recyclerView.adapter = TripRecyclerAdapter(container?.context, list)
        return view
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment FragmentTrip.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FragmentTrip().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
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
