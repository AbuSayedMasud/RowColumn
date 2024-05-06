package com.imtiaz.rowcolumn.demoTry.model

import kotlinx.serialization.Serializable

@Serializable
data class BrandWiseData(
    val brand: String,
    val budget: String,
    val tpSale: String,
    val ach: String,
    val netAch: String
)