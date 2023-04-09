package com.example.bluetoothdetector.common.domain

enum class Page(
    val denomination: String,
    val route: String,
    val inMenu: Boolean = true,
) {
    SPLASH("L-SPLASH", "SPLASH", false),
    MAIN("L-MAIN", "MAIN"),
    MAP("L-MAP", "MAP"),
    DEVICES("L-DEVICES", "DEVICES"),
    ACCOUNT("L-ACCOUNT", "ACCOUNT"),
    ENERGY("L-ENERGY", "ENERGY"),
    NETWORK("L-NETWORK", "NETWORK"),
    PERMISSIONS("L-PERMISSIONS", "PERMISSIONS");

    companion object {
        val Pages = Page.values()
        val MenuPages = Pages.filter { it.inMenu }
    }
}