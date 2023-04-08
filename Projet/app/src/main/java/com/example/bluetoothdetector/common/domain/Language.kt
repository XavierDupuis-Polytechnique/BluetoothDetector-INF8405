package com.example.bluetoothdetector.common.domain

import androidx.compose.runtime.saveable.listSaver
import androidx.compose.runtime.saveable.mapSaver
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

val German = Language(
    R.string.german,
    R.string.german_abbreviation,
    Locale.GERMAN
)

val Languages = listOf(
    English,
    French,
    German
)

val LanguageStateSaver = run {
    listSaver(
        save = {
            listOf(
                it.denomination,
                it.abbreviation,
                it.locale
            )
        },
        restore = { restorationList: List<Any?> ->
            Language(
                denomination = restorationList[0] as Int,
                abbreviation = restorationList[1] as Int,
                locale = restorationList[2] as Locale
            )
        }
    )
}

