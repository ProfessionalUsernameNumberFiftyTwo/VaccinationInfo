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
}