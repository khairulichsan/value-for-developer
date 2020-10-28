package com.example.valuedev.profile.skill

import android.content.Intent
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.ticketapps.util.sharedpref.Constant
import com.example.valuedev.BaseActivity
import com.example.valuedev.R
import com.example.valuedev.databinding.ActivityRegisterScreenBinding
import com.example.valuedev.databinding.ActivitySkillBinding
import com.example.valuedev.login.LoginScreenActivity
import com.example.valuedev.profile.ProfileService
import com.example.valuedev.register.RegisterApiService
import com.example.valuedev.register.RegisterViewModel
import com.example.valuedev.util.ApiClient
import com.example.valuedev.util.sharedpref.SharedPrefProvider


class SkillActivity : BaseActivity() {

    private lateinit var binding: ActivitySkillBinding
    private lateinit var viewModel: SkillViewModel
    private lateinit var defaultPref: SharedPrefProvider



    override fun initBinding() {

        binding = DataBindingUtil.setContentView(this, R.layout.activity_skill)
    }

    override fun onCreateActivity() {
        defaultPref = SharedPrefProvider(this)
        val service = ApiClient.getApiClient(this)?.create(ProfileService::class.java)

        viewModel = ViewModelProvider(this).get(SkillViewModel::class.java)

        if (service != null) {
            viewModel.setRegisterService(service)
        }
        subscribeLiveData()
        binding.btnSkill.setOnClickListener {
            val idbio = defaultPref.getString(Constant.KEY_ID_BIO)?:""
            viewModel.callSkillApi(
                    "$idbio" ,
                    binding.etSkill.text.toString()
            )
        }


    }

    override fun initListener() {


    }

    private fun subscribeLiveData() {
        viewModel.isLoadingLiveData.observe(this, {
            binding.progressBar.visibility = if (it) View.VISIBLE else View.GONE
        })

        viewModel.isMessageLiveData.observe(this, {
            Toast.makeText(this, it +" "+ binding.etSkill.text.toString(), Toast.LENGTH_SHORT).show()
        })

    }
}
