package com.example.prographyandroidstudy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.View
import androidx.core.widget.NestedScrollView
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_chat.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), TabLayout.OnTabSelectedListener, AppBarLayout.OnOffsetChangedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main);

        main_tabs.addOnTabSelectedListener(this)
        account_appbar.addOnOffsetChangedListener(this)
    }

    override fun onTabReselected(tab: TabLayout.Tab?) {

    }

    override fun onTabUnselected(tab: TabLayout.Tab?) {

    }

    override fun onTabSelected(tab: TabLayout.Tab?) {
        when(tab?.position){
            0 -> supportFragmentManager.beginTransaction().replace(R.id.main_fragment_container, FragmentHome()).commit()
            1 -> supportFragmentManager.beginTransaction().replace(R.id.main_fragment_container, FragmentPromotion()).commit()
            2 -> supportFragmentManager.beginTransaction().replace(R.id.main_fragment_container, FragmentTrip()).commit()
            3 -> supportFragmentManager.beginTransaction().replace(R.id.main_fragment_container, FragmentAccount()).commit()
        }
    }

    override fun onOffsetChanged(appBarLayout: AppBarLayout?, verticalOffset: Int) {
        if(appBarLayout!!.totalScrollRange == 0 || verticalOffset == 0){
            main_toolbar.alpha = 0f
            return
        }
        val ratio:Float = verticalOffset.toFloat() / appBarLayout.totalScrollRange.toFloat()
        main_toolbar.alpha = Math.abs(ratio)
    }
}
