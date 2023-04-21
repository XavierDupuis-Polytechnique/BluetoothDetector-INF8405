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

    val currentPage = mutableStateOf(Page.StartPage)

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
        currentPage.value = page
        navController.navigate(page.route)
        closeMenu(menuState, menuScope)
    }

    fun isSelectedTab(page: Page): Boolean {
        return page == currentPage.value
    }
}