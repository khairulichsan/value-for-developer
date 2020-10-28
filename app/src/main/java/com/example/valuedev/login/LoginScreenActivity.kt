package com.example.valuedev.login

import android.content.Intent
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.ticketapps.util.sharedpref.Constant
import com.example.valuedev.BaseActivity
import com.example.valuedev.MainActivity
import com.example.valuedev.R
import com.example.valuedev.biodata.BiodataActivity
import com.example.valuedev.databinding.ActivityLoginScreenBinding
import com.example.valuedev.register.RegisterScreenActivity
import com.example.valuedev.util.ApiClient
import com.example.valuedev.util.sharedpref.SharedPrefProvider

class LoginScreenActivity : BaseActivity() {

    private lateinit var binding: ActivityLoginScreenBinding
    private lateinit var viewModel: LoginViewModel
    private lateinit var defaultPref : SharedPrefProvider

    override fun initBinding() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login_screen)
    }

    override fun onCreateActivity() {
        val service = ApiClient.getApiClient(this)?.create(LoginApiService::class.java)
        defaultPref = SharedPrefProvider(this)

        viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
        if (service != null) {
            viewModel.setLoginService(service)
        }

        subscribeLiveData()
    }

    override fun initListener() {
        binding.btnLogin.setOnClickListener {
            viewModel.callLoginApi(
                binding.etEmail.text.toString(),
                binding.inputPass.text.toString()
            )
        }
        binding.tvToReg.setOnClickListener {
            intent = Intent(this, RegisterScreenActivity::class.java)
            startActivity(intent)
        }

    }

    private fun subscribeLiveData() {

        viewModel.isLoadingLiveData.observe(this, {
            binding.progressBar.visibility = if (it) View.VISIBLE else View.GONE
        })

        viewModel.isMessageLiveData.observe(this, {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        })

        viewModel.isLoginLiveData.observe(this, {
            if (it) {
                viewModel.putSharedPreferences(this)
                defaultPref.putBoolean(Constant.PREF_IS_LOGIN, true)
                if (defaultPref.getBoolean(Constant.PREF_REG) === true){
                    viewModel.putSharedPreferences(this)
                    val intent = Intent(this, BiodataActivity::class.java)
                    startActivity(intent)
                    Toast.makeText(this@LoginScreenActivity, "Biodata", Toast.LENGTH_SHORT).show()
                }else {
                    viewModel.putSharedPreferences(this)
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                    Toast.makeText(this@LoginScreenActivity, "Login Success", Toast.LENGTH_SHORT).show()
                }
            }
        })
    }
}