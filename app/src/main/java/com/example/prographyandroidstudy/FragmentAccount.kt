package com.example.prographyandroidstudy

import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.TextView
import com.google.android.material.appbar.AppBarLayout
import kotlinx.android.synthetic.main.fragment_account.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [FragmentAccount.newInstance] factory method to
 * create an instance of this fragment.
 */
class FragmentAccount : Fragment(), View.OnClickListener, AppBarLayout.OnOffsetChangedListener {
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
        val view = inflater.inflate(R.layout.fragment_account, container, false)
        view.findViewById<TextView>(R.id.account_service_text).setOnClickListener(this)
        view.findViewById<AppBarLayout>(R.id.account_appbar).addOnOffsetChangedListener(this)
//        view.findViewById<Toolbar>(R.id.account_toolbar).logo = activity?.resources?.getDrawable(R.mipmap.ic_launcher_round)
        return view
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment FragmentAccount.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FragmentAccount().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun onClick(p0: View?) {
        context?.startActivity(Intent(context, ServiceActivity::class.java))
    }

    override fun onOffsetChanged(appBarLayout: AppBarLayout?, verticalOffset: Int) {
        if(appBarLayout!!.totalScrollRange == 0 || verticalOffset == 0){
            account_toolbar.alpha = 0f
            return
        }
        val ratio:Float = verticalOffset.toFloat() / appBarLayout.totalScrollRange.toFloat()
        account_toolbar.alpha = Math.abs(ratio)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return super.onOptionsItemSelected(item)
    }
}
