package com.example.valuedev


import android.os.Handler
import androidx.databinding.DataBindingUtil
import com.example.valuedev.databinding.ActivitySplashScreenBinding
import com.example.valuedev.onboard.OnBoardActivity

@Suppress("DEPRECATION")
class SplashScreenActivity : BaseActivity() {
    private lateinit var binding: ActivitySplashScreenBinding

    override fun initBinding() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_splash_screen)
    }

    override fun onCreateActivity() {

        var handler = Handler()
        handler.postDelayed({
            baseStartActivity<OnBoardActivity>(this)
            finish()
        }, 3000)//delaying 3 seconds to open login activity

    }

    override fun initListener() {
    }

}