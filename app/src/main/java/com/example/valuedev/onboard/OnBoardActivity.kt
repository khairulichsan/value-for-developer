package com.example.valuedev.onboard

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import com.example.ticketapps.util.sharedpref.Constant
import com.example.valuedev.MainActivity
import com.example.valuedev.R
import com.example.valuedev.databinding.ActivityOnBoardBinding
import com.example.valuedev.login.LoginScreenActivity
import com.example.valuedev.register.RegisterScreenActivity
import com.example.valuedev.util.sharedpref.SharedPrefProvider
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_on_board.*

class OnBoardActivity : AppCompatActivity() {

    private lateinit var binding: ActivityOnBoardBinding
    private lateinit var defaultPref: SharedPrefProvider
    private val mResources = intArrayOf(
        R.drawable.ic_rocket,
    )
    lateinit var adapter: SlidingPagerAdapter
    var currentTab = 0
    private var tabCount = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_on_board)
        defaultPref= SharedPrefProvider(this)

        binding.apply {
            login.setOnClickListener {
                val intentlogin = Intent(this@OnBoardActivity, LoginScreenActivity::class.java)
                startActivity(intentlogin)
            }
            register.setOnClickListener {
                val intentregister = Intent(this@OnBoardActivity, RegisterScreenActivity::class.java)
                startActivity(intentregister)
            }
        }

        tabCount = mResources.size
        adapter = SlidingPagerAdapter(supportFragmentManager, mResources)
        viewPager.adapter = adapter
        val pageTransformer = ParallaxTransformer()
        viewPager.setPageTransformer(true, pageTransformer)

        tabLayout.setupWithViewPager(viewPager)
        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabReselected(tab: TabLayout.Tab?) {
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabSelected(tab: TabLayout.Tab?) {
                viewPager.currentItem = tab!!.position
            }

        })
        viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {

            }

            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {

            }

            override fun onPageSelected(position: Int) {
            }

        })

    }
    override fun onStart() {
        super.onStart()
        if (defaultPref.getBoolean(Constant.PREF_IS_LOGIN)) {
//
            moveIntent()
            finish()
        }
    }

    private fun moveIntent() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }
}


@Suppress("DEPRECATION")
class SlidingPagerAdapter(fragmentManager: FragmentManager?, private val mResources: IntArray) :
    FragmentPagerAdapter(
        fragmentManager!!
    ) {

    override fun getItem(position: Int): Fragment {
        return IntroPage().newInstance(position)
    }

    override fun getCount(): Int {
        return mResources.size
    }
}