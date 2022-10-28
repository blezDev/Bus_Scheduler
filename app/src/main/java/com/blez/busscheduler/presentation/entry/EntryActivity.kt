package com.blez.busscheduler.presentation.entry

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.blez.busscheduler.R
import com.blez.busscheduler.databinding.ActivityEntryBinding

class EntryActivity : AppCompatActivity() {
    private lateinit var binding : ActivityEntryBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_entry)

    }
}