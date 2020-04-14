package com.example.prographyandroidstudy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.fragment.app.FragmentContainerView
import androidx.fragment.app.FragmentManager
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), TabLayout.OnTabSelectedListener{

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main);

        main_tabs.addOnTabSelectedListener(this)
        supportFragmentManager.beginTransaction().add(R.id.main_fragment_container, FragmentHome()).addToBackStack(null).commit()
    }

    override fun onTabReselected(tab: TabLayout.Tab?) {

    }

    override fun onTabUnselected(tab: TabLayout.Tab?) {

    }

    override fun onTabSelected(tab: TabLayout.Tab?) {
        when(tab?.position){
            0 -> supportFragmentManager.beginTransaction().replace(R.id.main_fragment_container, FragmentHome()).addToBackStack(null).commit()
            1 -> supportFragmentManager.beginTransaction().replace(R.id.main_fragment_container, FragmentPromotion()).addToBackStack(null).commit()
            2 -> supportFragmentManager.beginTransaction().replace(R.id.main_fragment_container, FragmentTrip()).addToBackStack(null).commit()
            3 -> supportFragmentManager.beginTransaction().replace(R.id.main_fragment_container, FragmentAccount()).addToBackStack(null).commit()
        }
    }
}
