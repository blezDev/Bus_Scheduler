package com.blez.busscheduler.presentation.entry

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import cn.pedant.SweetAlert.SweetAlertDialog
import com.blez.busscheduler.R
import com.blez.busscheduler.`interface`.BusAPI
import com.blez.busscheduler.data.EntryData
import com.blez.busscheduler.data.EntryDataSTATUS
import com.blez.busscheduler.databinding.ActivityEntryBinding
import com.blez.busscheduler.network.Retrofit
import com.blez.busscheduler.presentation.driverMain.DriverMainActivity
import com.blez.busscheduler.utils.TokenManager
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EntryActivity : AppCompatActivity() {
    private lateinit var tokenManager: TokenManager
    private lateinit var binding : ActivityEntryBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_entry)
        tokenManager= TokenManager(applicationContext)
        binding.regProgressBar.visibility = View.INVISIBLE
        val retService = Retrofit.getRetrofitInstance().create(BusAPI::class.java)
        binding.submitBTN.setOnClickListener {
            val token = tokenManager.getToken()!!
            binding.regProgressBar.visibility = View.VISIBLE
            val busNumber = binding.etBusNumber.text.toString()
            val destination = binding.etAt.text.toString()
            val depature = binding.etDepature.text.toString()
            val stoppage = listOf<String>(binding.etStoppage.text.toString())
            if(busNumber.isNotEmpty() && destination.isNotEmpty() && depature.isNotEmpty() && stoppage.isNotEmpty()){
                binding.regProgressBar.visibility = View.INVISIBLE
                retService.addSchedule(EntryData(token = token,busNumber,destination,depature,stoppage)).enqueue(object : Callback<EntryDataSTATUS>{
                    override fun onResponse(
                        call: Call<EntryDataSTATUS>,
                        response: Response<EntryDataSTATUS>
                    ) {
                        when(response.code()){
                            200->{
                                    SweetAlertDialog(this@EntryActivity,SweetAlertDialog.SUCCESS_TYPE)
                                        .setTitleText("Success!!")
                                        .setContentText("data added!")
                                        .setConfirmClickListener { val intent = Intent(this@EntryActivity,DriverMainActivity::class.java)
                                        startActivity(intent)
                                        finish()}
                                        .show()

                            }
                        }
                    }

                    override fun onFailure(call: Call<EntryDataSTATUS>, t: Throwable) {
                        Toast.makeText(this@EntryActivity, "Something went wrong", Toast.LENGTH_SHORT).show()
                    }
                })
            }
            else{
                SweetAlertDialog(this@EntryActivity, SweetAlertDialog.WARNING_TYPE)
                    .setTitleText("!!WARNING!!")
                    .setContentText("Please fill up the details!!!")
                    .show()
            }
        }

    }
}