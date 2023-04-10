package com.example.bluetoothdetector.main.view

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.bluetoothdetector.common.view.containers.CenteredVerticalContainer
import com.example.bluetoothdetector.main.viewmodel.NetworkViewModel


@Composable
fun NetworkView(
    viewModel: NetworkViewModel = hiltViewModel()
) {
    CenteredVerticalContainer {

        viewModel.networkRepository.bytesSinceCreated.value.run {
            Text("MOBILE")
            Text(text = mobile.Tx.toString())
            Text(text = mobile.Rx.toString())
            Text("NETWORK")
            Text(text = network.Tx.toString())
            Text(text = network.Rx.toString())
            Text("TOTAL")
            Text(text = total.Tx.toString())
            Text(text = total.Rx.toString())
            Text("PROCESS")
            Text(text = process.Tx.toString())
            Text(text = process.Rx.toString())
        }

        viewModel.networkRepository.bytesSinceResumed.value.run {
            Text("MOBILE")
            Text(text = mobile.Tx.toString())
            Text(text = mobile.Rx.toString())
            Text("NETWORK")
            Text(text = network.Tx.toString())
            Text(text = network.Rx.toString())
            Text("TOTAL")
            Text(text = total.Tx.toString())
            Text(text = total.Rx.toString())
            Text("PROCESS")
            Text(text = process.Tx.toString())
            Text(text = process.Rx.toString())
        }

        IconButton(onClick = { viewModel.refresh() }) {
            Icon(imageVector = Icons.Default.Refresh, contentDescription = null)
        }


    }
}