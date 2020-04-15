package com.example.prographyandroidstudy.main.account

import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.TextView
import com.example.prographyandroidstudy.R
import com.example.prographyandroidstudy.chat.ServiceActivity
import com.google.android.material.appbar.AppBarLayout
import kotlinx.android.synthetic.main.fragment_account.*

class FragmentAccount : Fragment(), View.OnClickListener, AppBarLayout.OnOffsetChangedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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
