package com.example.prographyandroidstudy.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.prographyandroidstudy.R
import com.example.prographyandroidstudy.main.account.FragmentAccount
import com.example.prographyandroidstudy.main.home.FragmentHome
import com.example.prographyandroidstudy.main.promotion.FragmentPromotion
import com.example.prographyandroidstudy.main.trip.FragmentTrip
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), TabLayout.OnTabSelectedListener{

    private val fragments: Array<Fragment> = arrayOf(FragmentHome(), FragmentPromotion(), FragmentTrip(), FragmentAccount())
    var active:Fragment = fragments[0]

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main);

        main_tabs.addOnTabSelectedListener(this)

        supportFragmentManager.beginTransaction().add(R.id.main_fragment_container, fragments[0]).commit()
        supportFragmentManager.beginTransaction().add(R.id.main_fragment_container, fragments[1]).hide(fragments[1]).commit()
        supportFragmentManager.beginTransaction().add(R.id.main_fragment_container, fragments[2]).hide(fragments[2]).commit()
        supportFragmentManager.beginTransaction().add(R.id.main_fragment_container, fragments[3]).hide(fragments[3]).commit()
    }

    override fun onTabReselected(tab: TabLayout.Tab?) {

    }

    override fun onTabUnselected(tab: TabLayout.Tab?) {

    }

    override fun onTabSelected(tab: TabLayout.Tab?) {
        when(tab?.position){
            0 -> fragmentChange(0)
            1 -> fragmentChange(1)
            2 -> fragmentChange(2)
            3 -> fragmentChange(3)
        }
    }

    private fun fragmentChange(fragmentShow:Int){
        supportFragmentManager.beginTransaction().hide(active).show(fragments[fragmentShow]).commit()
        active = fragments[fragmentShow]
    }
}
