package com.example.valuedev.onboard

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.valuedev.R
import kotlinx.android.synthetic.main.intro_layout.*

@Suppress("DEPRECATION")
class IntroPage : Fragment() {
    var position = 0
    private val mResources = intArrayOf(R.drawable.ic_rocket)
    private val mTitle = arrayOf("Get Started")
    private val mDes = arrayOf("From now on, it will be a fair competition just between us."
    )

    fun newInstance(position: Int): IntroPage {
        val fragment = IntroPage()
        val arguments = Bundle()
        arguments.putInt("POSITION", position)
        fragment.arguments = arguments
        return fragment
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.intro_layout, container, false)


    @SuppressLint("UseCompatLoadingForDrawables")
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val args = arguments
            position = args!!.getInt("POSITION")


        intro_image.setImageDrawable(resources.getDrawable(mResources[position]))
        title.text = mTitle[position]
        description.text = mDes[position]

    }
}
