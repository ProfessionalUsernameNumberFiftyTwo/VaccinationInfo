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


        val vaccineList = mutableListOf<Vaccination>()
        vaccineList.add(Vaccination("Fake 1",
            sortedMapOf<String, Int>(
                Pair("1/23/22", 100),
                Pair("1/24/22", 105),
                Pair("1/25/22", 110)
            )
        ))
        vaccineList.add(Vaccination("Fake 2",
            sortedMapOf<String, Int>(
                Pair("1/23/22", 50000),
                Pair("1/24/22", 60000),
                Pair("1/25/22", 70000)
            )
        ))

        adapter = VaccinationAdapter(vaccineList)
        binding.recyclerViewVaccinationList.adapter = adapter
        binding.recyclerViewVaccinationList.layoutManager = LinearLayoutManager(this)
    }
}