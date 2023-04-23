package com.example.bluetoothdetector.main.view

import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Switch
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.bluetoothdetector.R
import com.example.bluetoothdetector.common.view.containers.CardContainer
import com.example.bluetoothdetector.common.view.containers.CenteredHorizontalContainer
import com.example.bluetoothdetector.common.view.containers.CenteredVerticalContainer
import com.example.bluetoothdetector.common.view.typography.Subtitle
import com.example.bluetoothdetector.common.view.typography.Title
import com.example.bluetoothdetector.main.viewmodel.EnergyViewModel


@Composable
fun EnergyView(
    viewModel: EnergyViewModel = hiltViewModel(),
) {
    CenteredVerticalContainer {

        val text: String = stringResource(id = R.string.energy_title)
        Text(
            text,
            fontWeight = FontWeight.Bold,
            fontSize = 30.sp,
            modifier = Modifier.padding(20.dp)
        )

        ResumedOrLaunchedView(viewModel)
        val usedBatteryLevel = if (viewModel.isStatsSinceCreatedDisplayed.value)
            viewModel.energyRepository.getBatteryLevelSinceCreated()
        else
            viewModel.energyRepository.getBatteryLevelSinceResumed()

        CardContainer(
            Modifier.defaultMinSize(200.dp, 100.dp)
        ) {
            CenteredHorizontalContainer {
                UsedBatteryLevel(usedBatteryLevel)
            }
        }
        CurrentBatteryLevel(viewModel.energyRepository.currentBatteryLevel.value)
    }
}

@Composable
fun CurrentBatteryLevel(batteryLevel: Float) {
    Text(stringResource(R.string.battery_level_current, batteryLevel))
}

@Composable
fun UsedBatteryLevel(batteryPercentage: Float) {
    Text(stringResource(R.string.battery_level_used, batteryPercentage))
}

@Composable
fun ResumedOrLaunchedView(viewModel: EnergyViewModel) {
    Title(R.string.network_since_application_was)
    CenteredHorizontalContainer {
        Subtitle(R.string.resumed)
        Switch(
            checked = viewModel.isStatsSinceCreatedDisplayed.value,
            onCheckedChange = { viewModel.toggleStatsDisplayed() }
        )
        Subtitle(R.string.launched)
    }
}
