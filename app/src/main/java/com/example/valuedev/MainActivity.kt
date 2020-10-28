package com.example.valuedev

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.valuedev.bidding.BiddingFragment
import com.example.valuedev.databinding.ActivityMainBinding
import com.example.valuedev.home.HomeFragment
import com.example.valuedev.profile.ProfileFragment
import com.example.valuedev.search.SearchFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)


    makeCurrent(HomeFragment())

    button_navigation_view.setOnNavigationItemSelectedListener {
        when(it.itemId){
            R.id.home -> makeCurrent(HomeFragment())
            R.id.offer -> makeCurrent(SearchFragment())
            R.id.project -> makeCurrent(BiddingFragment())
            R.id.profile -> makeCurrent(ProfileFragment())
        }
        return@setOnNavigationItemSelectedListener true
    }

}
private fun makeCurrent (fragment: Fragment){
    supportFragmentManager.beginTransaction().apply{
        replace(R.id.fragment,fragment)
        commit()
    }
}
}