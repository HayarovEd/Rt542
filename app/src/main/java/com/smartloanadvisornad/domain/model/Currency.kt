package com.smartloanadvisornad.domain.model


data class Currency (
    val cNY: CNY,
    val eUR: EUR,
    val uSD: USD
)

data class USD (
    val name: String,
    val nominal: Int,
    val previous: Double,
    val value: Double,
    val charCode: String,
) {
    val changeValue = value - previous
}

data class EUR  (
    val name: String,
    val nominal: Int,
    val previous: Double,
    val value: Double,
    val charCode: String,
) {
    val changeValue = value - previous
}

data class CNY  (
    val name: String,
    val nominal: Int,
    val previous: Double,
    val value: Double,
    val charCode: String,
) {
    val changeValue = value - previous
}
