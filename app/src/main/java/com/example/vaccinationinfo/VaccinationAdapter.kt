package com.example.vaccinationinfo

import android.content.Intent

class VaccinationAdapter(var dataSet: List<Vaccination>) :
    androidx.recyclerview.widget.RecyclerView.Adapter<com.example.vaccinationinfo.VaccinationAdapter.ViewHolder>(){

     class ViewHolder(view: android.view.View) : androidx.recyclerview.widget.RecyclerView.ViewHolder(view) {
         val textViewCountry: android.widget.TextView
         val textViewCount: android.widget.TextView
         val layout: androidx.constraintlayout.widget.ConstraintLayout

         init {
             textViewCountry = view.findViewById(R.id.textView_vaccinationItem_country)
             textViewCount = view.findViewById(R.id.textView_vaccinationItem_count)
             layout = view.findViewById(R.id.layout_vaccinationItem)
         }
     }

    override fun onCreateViewHolder(viewGroup: android.view.ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = android.view.LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_vaccination, viewGroup, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        val vaccination = dataSet[position]
        viewHolder.textViewCountry.text = vaccination.country
        viewHolder.textViewCount.text = vaccination.timeline.values.last().toString()
        viewHolder.layout.setOnClickListener {
            // Toast.makeText(it.context , "Hi, you clicked on ${hero.name}", Toast.LENGTH_SHORT).show()
            val context = viewHolder.layout.context
            val heroDetailIntent = Intent(context, VaccinationDetailActivity::class.java).apply {
                putExtra(VaccinationDetailActivity.EXTRA_VAX, vaccination)
            }
            context.startActivity(heroDetailIntent)
        }
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = dataSet.size

 }