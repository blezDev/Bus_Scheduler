package com.blez.busscheduler.presentation.login

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import cn.pedant.SweetAlert.SweetAlertDialog
import com.blez.busscheduler.R
import com.blez.busscheduler.`interface`.BusAPI
import com.blez.busscheduler.data.LoginStatus
import com.blez.busscheduler.data.login
import com.blez.busscheduler.databinding.ActivityLoginBinding
import com.blez.busscheduler.network.Retrofit
import com.blez.busscheduler.presentation.driverMain.DriverMainActivity
import com.blez.busscheduler.presentation.main.MainActivity
import com.blez.busscheduler.presentation.register.RegisterActivity
import com.blez.busscheduler.utils.TokenManager
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {
    private lateinit var tokenManager: TokenManager
    private lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_login)
        val retService = Retrofit.getRetrofitInstance().create(BusAPI::class.java)
        if(!isOnline(this))
        {
            SweetAlertDialog(this, SweetAlertDialog.WARNING_TYPE)
                .setTitleText("WARNING")
                .setContentText("!! PLEASE CONNECT TO INTERNET !!")
                .setConfirmClickListener { recreate() }
                .show()
        }
        binding.tvSignup.setOnClickListener {
            val intent = Intent(this@LoginActivity,RegisterActivity::class.java)
            startActivity(intent)
        }

        tokenManager = TokenManager(applicationContext)


        if(tokenManager.getToken() != null){
            val userType = tokenManager.getUserType()
            when(userType)
            {
                "Student"->{
                    val intent = Intent(this@LoginActivity,MainActivity::class.java)
                    startActivity(intent)
                    finish()
                }
                "Driver"->{
                    val intent = Intent(this@LoginActivity,DriverMainActivity::class.java)
                    startActivity(intent)
                    finish()
                }
            }
        }

        binding.btnLogin.setOnClickListener {
            val email = binding.etEmail.text.toString().trim()
            val password = binding.etPassword.text.toString()
            if(email.isNotEmpty() && password.isNotEmpty())
            {
                retService.login(login(email, password)).enqueue(object : Callback<LoginStatus>{
                    override fun onResponse(
                        call: Call<LoginStatus>,
                        response: Response<LoginStatus>
                    ) {
                        Log.e("TAG",response.body()?.message.toString())
                        when (response.code()) {
                            404 -> {
                                Toast.makeText(this@LoginActivity,"User does not exist. \n Please create a account",Toast.LENGTH_LONG).show()
                                val intent = Intent(this@LoginActivity,RegisterActivity::class.java)
                                startActivity(intent)
                            }
                            400 -> {
                                Toast.makeText(this@LoginActivity,"!!ACCOUNT IS NOT ACTIVE!!",Toast.LENGTH_LONG).show()
                                val intent = Intent(this@LoginActivity,RegisterActivity::class.java)
                                startActivity(intent)
                            }
                            401 ->
                            {
                                SweetAlertDialog(this@LoginActivity,SweetAlertDialog.WARNING_TYPE)
                                    .setTitleText("!!OPPS!!")
                                    .setContentText("INVALID CREDENTIAL")
                                    .show()
                            }
                            200 -> {
                                tokenManager = TokenManager(applicationContext)
                                tokenManager.saveToken(response.body()?.token!!)
                                tokenManager.saveUserType(response.body()?.usrtype!!)
                                tokenManager.saveEmail(email)
                                Toast.makeText(this@LoginActivity,response.body()?.message.toString(),Toast.LENGTH_LONG).show()
                                when(response.body()?.usrtype){
                                    "Student"->{
                                        val intent = Intent(this@LoginActivity,MainActivity::class.java)
                                        startActivity(intent)
                                        finish()
                                    }
                                    "Driver"->{
                                        val intent = Intent(this@LoginActivity,DriverMainActivity::class.java)
                                        startActivity(intent)
                                        finish()
                                    }
                                }


                            }
                            500 ->{
                                SweetAlertDialog(this@LoginActivity,SweetAlertDialog.ERROR_TYPE)
                                    .setTitleText("!!OPPS!!")
                                    .setContentText("Server Error")
                                    .show()
                            }
                        }

                    }

                    override fun onFailure(call: Call<LoginStatus>, t: Throwable) {
                        Toast.makeText(this@LoginActivity, "Something went wrong pls try later", Toast.LENGTH_SHORT).show()
                    }

                })
            }
            else    {
                SweetAlertDialog(this@LoginActivity,SweetAlertDialog.WARNING_TYPE)
                    .setTitleText("!!WARNING!!")
                    .setContentText("Please fill up the details!!!")
                    .show()
            }
        }

    }




    fun isOnline(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (connectivityManager != null) {
            val capabilities =
                connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
            if (capabilities != null) {
                if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) {
                    Log.i("Internet", "NetworkCapabilities.TRANSPORT_CELLULAR")
                    return true
                } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {
                    Log.i("Internet", "NetworkCapabilities.TRANSPORT_WIFI")
                    return true
                } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)) {
                    Log.i("Internet", "NetworkCapabilities.TRANSPORT_ETHERNET")
                    return true
                }
            }
        }
        return false
    }
}