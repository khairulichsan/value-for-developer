package com.example.valuedev.onboard

import android.view.View
import android.widget.ImageView
import androidx.viewpager.widget.ViewPager
import com.example.valuedev.R
import kotlin.math.abs

class ParallaxTransformer : ViewPager.PageTransformer {
    override fun transformPage(view: View, position: Float) {
        val absPosition = abs(position)
        if (position < -1) {
            // This page is way off-screen to the left.
            view.alpha = 1f
        } else if (position <= 1) {
            val image = view.findViewById<ImageView>(R.id.intro_image)
            image?.apply {
                scaleX = 1.0f - absPosition * 2
                scaleY = 1.0f - absPosition * 2
                alpha = 1.0f - absPosition * 2
            }

        } else {
            // This page is way off-screen to the right.
            view.alpha = 1f
        }


    }
}

