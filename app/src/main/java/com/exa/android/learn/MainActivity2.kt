package com.exa.android.learn

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import androidx.viewpager2.widget.ViewPager2
import coil.load
import com.exa.android.learn.adapter.ViewPagerAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment)

        val image_url = intent.getStringExtra("image_res")

        val image = findViewById<ImageView>(R.id.lar_image)
        val tabLayout = findViewById<TabLayout>(R.id.tab)
        val viewPager = findViewById<ViewPager2>(R.id.view_pager)

        image.load(image_url)
    // creation of again a new instance of fragment class we can create three different fragments too
    // and it is definetly required when you have different task for each fragment
    // let's discuss it in brief in fragment section

        val fragment =  listOf(
            fragmentTab.newInstance("this is first tab"),// calling fragments and passing data as string in them
            fragmentTab.newInstance("this is second tab"),
            fragmentTab.newInstance("this is third tab")
        )

        viewPager.adapter = ViewPagerAdapter(this, fragment)

        TabLayoutMediator(tabLayout, viewPager){tab, position ->
            tab.text = "TAB ${position + 1}"
        }.attach()
    }
}