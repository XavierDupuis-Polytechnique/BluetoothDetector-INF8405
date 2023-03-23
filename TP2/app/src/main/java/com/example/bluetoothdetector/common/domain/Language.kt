package com.example.bluetoothdetector.common.domain

import com.example.bluetoothdetector.R
import java.util.*

data class Language(
    val denomination: Int,
    val abbreviation: Int,
    val locale: Locale
)

val French = Language(
    R.string.french,
    R.string.french_abbreviation,
    Locale.CANADA_FRENCH
)

val English = Language(
    R.string.english,
    R.string.english_abbreviation,
    Locale.ENGLISH
)

val Languages = listOf(
    English,
    French
)