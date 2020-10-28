package com.example.valuedev

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.valuedev.util.sharedpref.SharedPrefProvider

abstract class BaseActivity : AppCompatActivity() {


    lateinit var mActivity: AppCompatActivity
    private lateinit var defaultPref: SharedPrefProvider


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mActivity = this
        initBinding()
        onCreateActivity()
        initListener()
    }


    abstract fun initBinding()
    abstract fun onCreateActivity()
    abstract fun initListener()

    protected inline fun <reified ClassActivity> baseStartActivity(context: Context) {
        context.startActivity(Intent(context, ClassActivity::class.java))
    }
}

