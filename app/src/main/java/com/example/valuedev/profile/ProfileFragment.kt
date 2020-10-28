package com.example.valuedev.profile


import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.ticketapps.util.sharedpref.Constant
import com.example.valuedev.MainActivity
import com.example.valuedev.R
import com.example.valuedev.databinding.FragmentProfileBinding
import com.example.valuedev.developer.DevAdapter
import com.example.valuedev.login.LoginScreenActivity
import com.example.valuedev.profile.port.PortActivity
import com.example.valuedev.profile.skill.SkillActivity
import com.example.valuedev.profile.viewpager.ProfileAdapter
import com.example.valuedev.util.ApiClient
import com.example.valuedev.util.sharedpref.SharedPrefProvider
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_profile.*
import kotlinx.android.synthetic.main.fragment_profile.view.*
import kotlinx.coroutines.*
import retrofit2.Response


@Suppress("DEPRECATION")
class ProfileFragment() : Fragment(R.layout.fragment_profile) {


    private lateinit var binding: FragmentProfileBinding
    private lateinit var sharedPref: SharedPrefProvider
    private lateinit var defaultPref: SharedPrefProvider
    private lateinit var pagerAdapter: ProfileAdapter
    private lateinit var dataProfile: com.example.valuedev.profile.ProfileModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        defaultPref = SharedPrefProvider(requireContext())




    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfileBinding.inflate(inflater)

        pagerAdapter = ProfileAdapter(requireActivity().supportFragmentManager)
        binding.viewPager.adapter = pagerAdapter
        binding.tabLayout.setupWithViewPager(binding.viewPager)

        callSignApi()
        return binding.root
    }

   private fun DialogOut() {
        val dialog = activity?.let {
            AlertDialog.Builder(it)
                .setTitle("Log out")
                .setMessage("Are you sure want to logout?")
                .setCancelable(false)
                .setPositiveButton("YES") { dialog: DialogInterface?, which: Int ->
                    defaultPref.clear()
                    val i = Intent(activity, LoginScreenActivity::class.java)
                    startActivity(i)
                    requireActivity().finish()
                    Toast.makeText(it, "Log Out", Toast.LENGTH_SHORT).show()

                }
                .setNegativeButton("NO") { dialogInterface, i ->
                    dialogInterface.dismiss()
                }
        }
        dialog!!.show()
    }

    private fun editProfile(){
//        (activity as MainActivity?)?.let {
//            val intent = Intent(it, EditProfileFragment::class.java)
//            it.startActivity(intent)
//        }
    }
    private fun addSkill(){
     (activity as MainActivity?)?.let {
            val intent = Intent(it, SkillActivity::class.java)
    it.startActivity(intent) }
    }
    private fun addPort(){
        (activity as MainActivity?)?.let {
            val intent = Intent(it, PortActivity::class.java)
            it.startActivity(intent) }
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        view.iv_menu.setOnClickListener {
            view.profile_drawer.openDrawer(GravityCompat.END)
        }



        iv_menu.setOnClickListener {
            profile_drawer.openDrawer(GravityCompat.END)
        }
        drawer_menu.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.add_skill -> addSkill()
                R.id.add_exp -> Toast.makeText(context, "Add Exp", Toast.LENGTH_SHORT).show()
                R.id.add_port -> addPort()
                R.id.nav_faq -> Toast.makeText(context, "Frequently Asked Question", Toast.LENGTH_SHORT).show()
                R.id.nav_help -> Toast.makeText(context, "what we can help", Toast.LENGTH_SHORT).show()
                R.id.nav_about_us -> Toast.makeText(context, "About Us", Toast.LENGTH_SHORT).show()
                R.id.edit_profile -> editProfile()
                R.id.logout -> DialogOut()
            }
            return@setNavigationItemSelectedListener true
        }
    }




    private fun callSignApi() {


        val service = ApiClient.getApiClient(this.requireContext())?.create(ProfileService::class.java)
        val idRec = defaultPref.getString(Constant.KEY_ID_ACCOUNT)?:""
        val coroutineScope = CoroutineScope(Job() + Dispatchers.Main)
        coroutineScope.launch {

            withContext(Dispatchers.IO) {



                    service?.getProfileDev(idRec)?.enqueue(object :
                            retrofit2.Callback<ProfileResponse> {
                        override fun onResponse(
                                call: retrofit2.Call<ProfileResponse>,
                                response: Response<ProfileResponse>
                        ) {

                            val data = response.body()?.data
                            binding.tvName.text = data?.get(0)?.name_company ?: ""
                            binding.tvSector.text = data?.get(0)?.sector ?: ""
                            binding.tvDesc.text = data?.get(0)?.desc ?: ""
                            binding.tvLocation.text = data?.get(0)?.city ?: ""
                            binding.tvStatus.text = data?.get(0)?.status ?: ""
                            defaultPref.putString(Constant.KEY_ID_BIO, "${data?.get(0)?.id_bio}")
                            Picasso.get().load("http://3.85.146.25:3000/uploads/${data?.get(0)?.image}").placeholder(R.drawable.blank_portrait).into(binding.dePhotoProfil)


                        }

                        override fun onFailure(
                                call: retrofit2.Call<ProfileResponse>,
                                t: Throwable
                        ) {
                            Log.d("onFailure", t.message.toString())
                            Log.d("profile", call.toString())
                        }
                        //
                    })


            }
        }
    }




}










