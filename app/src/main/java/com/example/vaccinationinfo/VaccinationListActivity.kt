package com.example.vaccinationinfo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.vaccinationinfo.databinding.ActivityVaccinationListBinding

class VaccinationListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityVaccinationListBinding
    lateinit var adapter: VaccinationAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityVaccinationListBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val tempMap: Map<String,Int> = mapOf("1/2/34" to 100000, "1/3/34" to 2000000)
        val tempVax = Vaccination("Temp Country", tempMap)
        val temp: List<Vaccination> = listOf(tempVax)

        adapter = VaccinationAdapter(temp)
        binding.recyclerViewVaccinationList.adapter = adapter
        binding.recyclerViewVaccinationList.layoutManager = LinearLayoutManager(this)
    }
}