package com.example.vaccinationinfo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.vaccinationinfo.databinding.ActivityVaccinationDetailBinding

class VaccinationDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityVaccinationDetailBinding

    companion object {
        val EXTRA_VAX = "Vaccine"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityVaccinationDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

    val vaccination = intent.getParcelableExtra<Vaccination>(EXTRA_VAX)
    binding.textViewVaccinationDetailCountry.text = vaccination?.country
    binding.textViewVaccinationDetailCount.text = vaccination?.timeline?.toList().toString()
    }
}