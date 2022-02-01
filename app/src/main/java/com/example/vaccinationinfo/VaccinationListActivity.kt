package com.example.vaccinationinfo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
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

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.vaccination_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle item selection
        return when (item.itemId) {
            R.id.sort_by_country_name -> {
                sortByName()
                adapter.notifyDataSetChanged()
                true
            }
            R.id.sort_by_vaccine_count -> {
                sortByCount()
                adapter.notifyDataSetChanged()
                true
            }
            R.id.sort_by_country_name_descending -> {
                sortByNameDescending()
                adapter.notifyDataSetChanged()
                true
            }
            R.id.sort_by_vaccine_count_descending -> {
                sortByCountDescending()
                adapter.notifyDataSetChanged()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun sortByCount() {
        adapter.dataSet = adapter.dataSet.sortedBy { it.timeline.values.last() }
    }

    private fun sortByCountDescending() {
        adapter.dataSet = adapter.dataSet.sortedByDescending { it.timeline.values.last() }
    }

    private fun sortByName() {
        adapter.dataSet = adapter.dataSet.sorted()
    }

    private fun sortByNameDescending() {
        adapter.dataSet = adapter.dataSet.sortedDescending()
    }

}