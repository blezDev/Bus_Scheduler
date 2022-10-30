package com.blez.busscheduler.presentation.register

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import cn.pedant.SweetAlert.SweetAlertDialog
import com.blez.busscheduler.R
import com.blez.busscheduler.`interface`.BusAPI
import com.blez.busscheduler.data.Register
import com.blez.busscheduler.data.RegisterStatus
import com.blez.busscheduler.databinding.ActivityRegisterBinding
import com.blez.busscheduler.network.Retrofit
import com.blez.busscheduler.presentation.driverMain.DriverMainActivity
import com.blez.busscheduler.presentation.login.LoginActivity
import com.blez.busscheduler.presentation.main.MainActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding : ActivityRegisterBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_register)
        binding.regProgressBar.visibility = View.INVISIBLE
        val retService = Retrofit.getRetrofitInstance().create(BusAPI::class.java)
        var options = arrayOf("Student","Driver")
        binding.typeop.adapter = ArrayAdapter(this,R.layout.signupspinner,options)



        binding.btnSignup.setOnClickListener {
            binding.regProgressBar.visibility = View.VISIBLE
            val email = binding.etEmail.text.toString().trim()
            val name = binding.etName.text.toString()

            val password = binding.etPassword.text.toString()
            val phoneNumber = binding.etPhone.text.toString()
            val userType = binding.typeop.selectedItem.toString()
          retService.registerUser(Register(email = email, name = name, password = password, number = phoneNumber, usrtype =  userType)).enqueue(object : Callback<RegisterStatus>{
              override fun onResponse(
                  call: Call<RegisterStatus>,
                  response: Response<RegisterStatus>
              ) {
                  binding.regProgressBar.visibility = View.INVISIBLE
                 when(response.code()){
                     200->{
                         Toast.makeText(this@RegisterActivity, "Register Success!! \n Please Login", Toast.LENGTH_SHORT).show()
                         val intent = Intent(this@RegisterActivity,LoginActivity::class.java)
                         startActivity(intent)
                         finish()

                     }
                     400-> {
                         SweetAlertDialog(this@RegisterActivity,SweetAlertDialog.WARNING_TYPE)
                             .setTitleText("!!OPPS!!")
                             .setContentText("Email already Exist!!")
                             .show()
                     }
                     else ->{
                         SweetAlertDialog(this@RegisterActivity,SweetAlertDialog.WARNING_TYPE)
                             .setTitleText("!!OPPS!!")
                             .setContentText(response.body()?.message.toString())
                             .show()
                     }
                     }
                 }


              override fun onFailure(call: Call<RegisterStatus>, t: Throwable) {
                  Toast.makeText(this@RegisterActivity, "Something went wrong!!", Toast.LENGTH_SHORT).show()
              }

          })

        }
    }
}