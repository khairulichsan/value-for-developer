package com.example.valuedev.register

import android.content.Intent
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.ticketapps.util.sharedpref.Constant
import com.example.valuedev.BaseActivity
import com.example.valuedev.R
import com.example.valuedev.databinding.ActivityRegisterScreenBinding
import com.example.valuedev.login.LoginScreenActivity
import com.example.valuedev.util.ApiClient
import com.example.valuedev.util.sharedpref.SharedPrefProvider


class RegisterScreenActivity : BaseActivity() {

    private lateinit var binding: ActivityRegisterScreenBinding
    private lateinit var viewModel: RegisterViewModel
    private lateinit var defaultPref : SharedPrefProvider


    override fun initBinding() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_register_screen)
    }

    override fun onCreateActivity() {
        val service = ApiClient.getApiClient(this)?.create(RegisterApiService::class.java)
        defaultPref = SharedPrefProvider(this)

        viewModel = ViewModelProvider(this).get(RegisterViewModel::class.java)

        if (service != null) {
            viewModel.setRegisterService(service)
        }

        subscribeLiveData()


    }

    override fun initListener() {
        binding.btnReg.setOnClickListener {
            viewModel.callRegisterApi(
                binding.etNameReg.text.toString(),
                binding.etEmailReg.text.toString(),
                binding.etPassReg.text.toString(),
                binding.etNoReg.text.toString(),
            )
        }
        binding.btnToLogin.setOnClickListener {
            onBackPressed()
        }
    }

    private fun subscribeLiveData() {
        viewModel.isLoadingLiveData.observe(this, {
            binding.progressBar.visibility = if (it) View.VISIBLE else View.GONE
        })

        viewModel.isMessageLiveData.observe(this, {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        })

        viewModel.isRegisterLiveData.observe(this, {
            defaultPref.putBoolean(Constant.PREF_REG, true)
            if (it) {
                val intent = Intent(this, LoginScreenActivity::class.java)
                startActivity(intent)
                finish()
            }
        })
    }
}
