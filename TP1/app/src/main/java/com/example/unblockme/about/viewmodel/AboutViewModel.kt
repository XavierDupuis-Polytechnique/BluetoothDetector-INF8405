package com.example.unblockme.about.viewmodel

import androidx.lifecycle.ViewModel
import com.example.unblockme.common.view.Page

class AboutViewModel: ViewModel() {
    fun navigateToMenu(navigateTo: (Page) -> Unit) {
        navigateTo(Page.Menu)
    }
}