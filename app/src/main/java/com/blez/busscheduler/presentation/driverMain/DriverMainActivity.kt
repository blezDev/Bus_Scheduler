package com.blez.busscheduler.presentation.driverMain

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.blez.busscheduler.R
import com.blez.busscheduler.`interface`.BusAPI
import com.blez.busscheduler.adapter.CurrentScheduleAdapter
import com.blez.busscheduler.data.Auth
import com.blez.busscheduler.data.ScheduleList
import com.blez.busscheduler.databinding.ActivityDriverMainBinding
import com.blez.busscheduler.network.Retrofit
import com.blez.busscheduler.presentation.entry.EntryActivity
import com.blez.busscheduler.utils.TokenManager
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DriverMainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDriverMainBinding
    private lateinit var tokenManager: TokenManager
    private lateinit var adapter: CurrentScheduleAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_driver_main)
        tokenManager = TokenManager(applicationContext)
        binding.entryBTN.setOnClickListener {
            val intent = Intent(this,EntryActivity::class.java)
            startActivity(intent)
        }
        val token = tokenManager.getToken()!!
        val email = tokenManager.getEmail()!!
        val retService = Retrofit.getRetrofitInstance().create(BusAPI::class.java)
        retService.showSchedule(auth = Auth(email, token)).enqueue(object : Callback<ScheduleList> {
            override fun onResponse(call: Call<ScheduleList>, response: Response<ScheduleList>) {
                if(response.body()?.data!!.isNotEmpty()){
                    adapter = CurrentScheduleAdapter(this@DriverMainActivity,response.body()?.data!!)
                    binding.CurrentRecyclerView.adapter = adapter
                    binding.CurrentRecyclerView.layoutManager = LinearLayoutManager(this@DriverMainActivity)
                }
                else{
                    binding.CurrentText.text = " No buses at moment!!!"
                    binding.CurrentRecyclerView.visibility = View.INVISIBLE

                }
            }

            override fun onFailure(call: Call<ScheduleList>, t: Throwable) {
                Toast.makeText(this@DriverMainActivity, "Something went wrong try later", Toast.LENGTH_SHORT).show()
            }
        })

    }
}