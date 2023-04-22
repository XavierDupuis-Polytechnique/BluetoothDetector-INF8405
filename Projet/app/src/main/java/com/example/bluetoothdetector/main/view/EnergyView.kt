package com.example.bluetoothdetector.main.view

import android.os.BatteryManager
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.Switch
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDownward
import androidx.compose.material.icons.filled.ArrowUpward
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
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
import com.example.bluetoothdetector.main.domain.BidirectionalBytes
import com.example.bluetoothdetector.main.domain.BytesStats
import com.example.bluetoothdetector.main.viewmodel.EnergyViewModel
import com.example.bluetoothdetector.main.viewmodel.NetworkViewModel


@Composable
fun EnergyView(
    viewModel: EnergyViewModel = hiltViewModel(),
){
    CenteredVerticalContainer(content = {

        val text: String = stringResource(id = R.string.energy_title)
        Text(text, fontWeight = FontWeight.Bold, fontSize = 30.sp, modifier = Modifier.padding(20.dp))

        ResumedOrLaunchedView(viewModel)

        val batteryPercentage = if( viewModel.isStatsSinceCreatedDisplayed.value)
            viewModel.energyRepository.batteryPctSinceCreated.value
        else
            viewModel.energyRepository.batteryPctSinceResumed.value

        CardContainer(Modifier.defaultMinSize(200.dp,100.dp )) {
            CenteredHorizontalContainer() {
                if(batteryPercentage != null)
                    BatteryPercentageView(batteryPercentage as Float)
                else BatteryPercentageView(batteryPercentage = 0.00F)
            }
        }

        actualState(viewModel.energyRepository.batteryPct.value)

        refreshButton(viewModel)

    })

}
@Composable
fun actualState(batteryPct: Float?) {
    if(batteryPct == null) return
    val text: String = stringResource(id = R.string.battery_actual_state_string) +" "+ batteryPct + "% !"
    Text(text, fontWeight = FontWeight.Bold, modifier = Modifier.padding(20.dp) )
}

@Composable
fun refreshButton(viewModel: EnergyViewModel) {
    Button(onClick = { viewModel.refresh() }) {
        Icon(imageVector = Icons.Default.Refresh, contentDescription = null)
        Text(stringResource(R.string.refresh))
    }
}

@Composable
fun BatteryPercentageView(batteryPercentage:Float) {
    val text: String = batteryPercentage.toString()+ "% " + stringResource(id = R.string.used_string)
    Text(text)
}

@Composable
fun ResumedOrLaunchedView(viewModel: EnergyViewModel) {
    Title(R.string.network_since_application_was)
    CenteredHorizontalContainer  {
        Subtitle(R.string.resumed)
        Switch(
            checked = viewModel.isStatsSinceCreatedDisplayed.value,
            onCheckedChange = { viewModel.toggleStatsDisplayed() }
        )
        Subtitle(R.string.launched)
    }
}
