package com.example.bluetoothdetector.main.view

import android.os.BatteryManager
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.Switch
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDownward
import androidx.compose.material.icons.filled.ArrowUpward
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.bluetoothdetector.R
import com.example.bluetoothdetector.common.view.containers.CardContainer
import com.example.bluetoothdetector.common.view.containers.CenteredHorizontalContainer
import com.example.bluetoothdetector.common.view.containers.CenteredVerticalContainer
import com.example.bluetoothdetector.common.view.typography.Subtitle
import com.example.bluetoothdetector.common.view.typography.Title
import com.example.bluetoothdetector.main.domain.BidirectionalBytes
import com.example.bluetoothdetector.main.domain.BytesStats
import com.example.bluetoothdetector.main.viewmodel.EnergyViewModel
import com.example.bluetoothdetector.main.viewmodel.NetworkViewModel



@Composable
fun EnergyView(
    viewModel: EnergyViewModel = hiltViewModel(),
){
    CenteredVerticalContainer() {
        Title("allo")

        Text(text = viewModel.energyRepository.batteryPct)
        viewModel.energyRepository.batteryPct


    }

}