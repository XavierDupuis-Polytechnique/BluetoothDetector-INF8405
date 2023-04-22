package com.example.bluetoothdetector.menu.viewmodel

import androidx.compose.material.DrawerState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import com.example.bluetoothdetector.common.domain.Page
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MenuViewModel @Inject constructor() : ViewModel() {

    // Holds the current viewed page
    val currentPage = mutableStateOf(Page.StartPage)

    // Returns true if the menu drawer is visible
    fun isMenuOpened(menuState: DrawerState): Boolean {
        return menuState.isOpen
    }

    // Toggle the current menu drawer visibility
    fun toggleMenu(
        menuState: DrawerState,
        menuScope: CoroutineScope
    ) {
        if (isMenuOpened(menuState)) {
            closeMenu(menuState, menuScope)
        } else {
            openMenu(menuState, menuScope)
        }
    }

    // Shows the menu drawer
    private fun openMenu(
        menuState: DrawerState,
        menuScope: CoroutineScope
    ) {
        menuScope.launch { menuState.open() }
    }

    // Hides the menu drawer
    private fun closeMenu(
        menuState: DrawerState,
        menuScope: CoroutineScope
    ) {
        menuScope.launch { menuState.close() }
    }

    // Navigate to a menu page
    //      Update the current viewed page
    //      Close the menu drawer
    fun navigate(
        navController: NavHostController,
        menuState: DrawerState,
        menuScope: CoroutineScope,
        page: Page
    ) {
        currentPage.value = page
        navController.navigate(page.route)
        closeMenu(menuState, menuScope)
    }

    // Compares the provided page to the current page
    fun isSelectedTab(page: Page): Boolean {
        return page == currentPage.value
    }
}