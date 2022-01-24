package com.example.vaccinationinfo

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Vaccination(
    val country: String,
    val timeline: Map<String,Int>
) : Parcelable, Comparable<Vaccination> {
    override fun compareTo(other: Vaccination): Int {
        return this.country.compareTo(other.country)
    }
}