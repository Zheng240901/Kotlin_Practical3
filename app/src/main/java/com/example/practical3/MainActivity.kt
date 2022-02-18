package com.example.practical3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.practical3.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding:ActivityMainBinding
    val testing = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        reset()
        binding.btnReset.setOnClickListener() { reset() }
        binding.btnSubmit.setOnClickListener() { submit() }

    }

    private fun reset() {

        binding.edtName.text.clear()
        binding.rgpGender.check(R.id.radFemale)
        binding.spnProgram.setSelection(0)
        binding.edtYear.text.clear()
        binding.chkSponsorship.isChecked = false

        binding.edtName.requestFocus()
    }

    private fun submit() {
        //get input
        val name = binding.edtName.text.toString().trim()
        val gender = getGender()
        val program = binding.spnProgram.selectedItem as String
        val sponsorship = binding.chkSponsorship.isChecked
        val year = binding.edtYear.text.toString().toIntOrNull() ?: 0
        //meaning of this line of code above ↑,
        // if(binding.edtYear.text.toString().toIntOrNull() != NULL)
        // return year;
        // else
        // return 0;


        //calculation
        val fee = getFee(program, year, sponsorship)

        //validation
        //name validation
        if(name == "")
        {
            toast(getString(R.string.invalid_name))
            return
        }

        //year validation
        if(year < 1 || year > 3)
        {
            toast(getString(R.string.invalid_year))
            return
        }



        //Explicit intent
        val intent = Intent(this,SubActivity::class.java)
            .putExtra("name",name)
            .putExtra("gender",gender)
            .putExtra("program",program)
            .putExtra("year",year)
            .putExtra("sponsorship",sponsorship)
            .putExtra("fee",fee)

        startActivity(intent)
    }
//----------------------------------------------------------------------------------------
    //functions
    private fun getFee(program: String, year: Int, sponsorship: Boolean): Double {
        val base = when(program) {
            "RIT" -> when (year) {
                1 -> 11000.0
                2 -> 12000.0
                3 -> 13000.0
                else -> 0.0
            }
            "RST" -> when (year) {
                1 -> 14000.0
                2 -> 15000.0
                3 -> 16000.0
                else -> 0.0
            }
            "RSF" -> when (year) {
                1 -> 17000.0
                2 -> 18000.0
                3 -> 19000.0
                else -> 0.0
            }
            else -> 0.0
        }

        val rate = if(sponsorship) 0.5 else 1.0 //if sponsorship checkbox is checked then give 50% off
        return base * rate
    }

    private fun toast(text: String) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }

    private fun getGender(): String {
        //return buttonId when it is chosen return nothing if both not chosen
        return when(binding.rgpGender.checkedRadioButtonId) {
            R.id.radFemale -> getString(R.string.female)
            R.id.radMale -> getString(R.string.male)
            else -> ""
        }

    }
}