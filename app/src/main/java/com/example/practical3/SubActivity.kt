package com.example.practical3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.practical3.databinding.ActivitySubBinding

class SubActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySubBinding
    val testing2 = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivitySubBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnClose.setOnClickListener(){ finish() }

        //get results
        val name = intent.getStringExtra("name") ?: ""
        val gender = intent.getStringExtra("gender") ?: ""
        val program = intent.getStringExtra("program") ?: ""
        val year = intent.getIntExtra("year",0)
        val sponsorship = intent.getBooleanExtra("sponsorship", false)
        val fee = intent.getDoubleExtra("fee",0.0)

        //print out results
        binding.txtName.text = name
        binding.txtGender.text = gender
        binding.txtProgram.text = program
        binding.txtYear.text = year.toString()
        binding.txtSponsorship.text = if(sponsorship) getString(R.string.yes) else getString(R.string.no)
        binding.txtFees.text = "RM %.2f".format(fee)
    }
}