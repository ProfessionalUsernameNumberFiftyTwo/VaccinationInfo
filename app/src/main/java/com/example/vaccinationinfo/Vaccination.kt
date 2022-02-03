package com.example.vaccinationinfo

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.util.*

@Parcelize
data class Vaccination(
    val country: String,
    val timeline: SortedMap<String,Long>
) : Parcelable, Comparable<Vaccination> {
    override fun compareTo(other: Vaccination): Int {
        return this.country.compareTo(other.country)
    }

    fun largestIncrease(): Long {
        var largestIncrease: Long = 0
        val values = timeline.values.toList()
        var i = 0
        while (i<values.size-1) {
            if (values[i+1] - values[i] > largestIncrease) {
                largestIncrease = values[i+1] - values[i]
            }
            i++
        }
        return largestIncrease
    }
}