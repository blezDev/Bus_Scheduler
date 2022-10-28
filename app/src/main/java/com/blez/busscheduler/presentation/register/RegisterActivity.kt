package com.blez.busscheduler.presentation.register

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import androidx.databinding.DataBindingUtil
import com.blez.busscheduler.R
import com.blez.busscheduler.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding : ActivityRegisterBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_register)
        binding.regProgressBar.visibility = View.INVISIBLE
        var options = arrayOf("Student","Driver")
        binding.typeop.adapter = ArrayAdapter(this,R.layout.signupspinner,options)







    }
}