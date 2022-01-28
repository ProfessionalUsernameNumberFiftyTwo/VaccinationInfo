package com.example.vaccinationinfo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.vaccinationinfo.databinding.ActivityVaccinationListBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class VaccinationListActivity : AppCompatActivity() {
    val TAG = "VaccinationListActivity"

    private lateinit var binding: ActivityVaccinationListBinding
    lateinit var adapter: VaccinationAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityVaccinationListBinding.inflate(layoutInflater)
        setContentView(binding.root)


        var vaccineList = listOf<Vaccination>()
//        vaccineList.add(Vaccination("Fake 1",
//            sortedMapOf<String, Int>(
//                Pair("1/23/22", 100),
//                Pair("1/24/22", 105),
//                Pair("1/25/22", 110)
//            )
//        ))
//        vaccineList.add(Vaccination("Fake 2",
//            sortedMapOf<String, Int>(
//                Pair("1/23/22", 50000),
//                Pair("1/24/22", 60000),
//                Pair("1/25/22", 70000)
//            )
//        ))
        
        val vaccineApi = RetrofitHelper.getInstance().create(Covid19Service::class.java)
        val vaccineCall = vaccineApi.getVaccinations(10)
        
        vaccineCall.enqueue(object : Callback<List<Vaccination>> {
            override fun onResponse(
                call: Call<List<Vaccination>>,
                response: Response<List<Vaccination>>
            ) {
                Log.d(TAG, "onResponse: ${response.body()}")
                vaccineList = response.body() ?: listOf<Vaccination>()
                adapter = VaccinationAdapter(vaccineList)
                binding.recyclerViewVaccinationList.adapter = adapter
                binding.recyclerViewVaccinationList.layoutManager =
                    LinearLayoutManager(this@VaccinationListActivity)
            }

            override fun onFailure(call: Call<List<Vaccination>>, t: Throwable) {
                Log.d(TAG, "onFailure: ${t.message}")
            }

        })
        

    }
}