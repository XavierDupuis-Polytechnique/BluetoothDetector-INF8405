package com.example.bluetoothdetector.menu.viewmodel

import androidx.compose.material.DrawerState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import com.example.bluetoothdetector.common.domain.Page
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class MenuViewModel : ViewModel() {

    val selectedTab = mutableStateOf(Page.MAIN)

    fun isMenuOpened(menuState: DrawerState): Boolean {
        return menuState.isOpen
    }

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

    private fun openMenu(
        menuState: DrawerState,
        menuScope: CoroutineScope
    ) {
        menuScope.launch { menuState.open() }
    }

    private fun closeMenu(
        menuState: DrawerState,
        menuScope: CoroutineScope
    ) {
        menuScope.launch { menuState.close() }
    }

    fun navigate(
        navController: NavHostController,
        menuState: DrawerState,
        menuScope: CoroutineScope,
        page: Page
    ) {
        selectedTab.value = page
        navController.navigate(page.route)
        closeMenu(menuState, menuScope)
    }

    fun isSelectedTab(page: Page): Boolean {
        return page == selectedTab.value
    }
}