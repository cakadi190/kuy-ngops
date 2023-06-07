package com.example.perkopianduniawi.models

data class CafeData(
    var name: String,
    var desc: String,
    var rating: Double,
    var location: String,
    var open: String,
    var close: String,
    var link: String = "",
    var status: Boolean = true,
    var region: String = "Kota Madiun",
    var imageBanner: String = "@drawable/cafe"
)