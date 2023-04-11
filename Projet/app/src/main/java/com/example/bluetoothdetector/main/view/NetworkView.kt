package com.example.bluetoothdetector.main.view

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
import com.example.bluetoothdetector.main.viewmodel.NetworkViewModel


@Composable
fun NetworkView(
    viewModel: NetworkViewModel = hiltViewModel()
) {
    CenteredVerticalContainer {

        Title(R.string.network_since_application_was)
        CenteredHorizontalContainer {
            Subtitle(R.string.resumed)
            Switch(
                checked = viewModel.isStatsSinceCreatedDisplayed.value,
                onCheckedChange = { viewModel.toggleStatsDisplayed() }
            )
            Subtitle(R.string.launched)
        }

        val bytesStats = if (viewModel.isStatsSinceCreatedDisplayed.value)
            viewModel.networkRepository.bytesSinceCreated
        else
            viewModel.networkRepository.bytesSinceResumed

        CardContainer {
            BytesStatsView(bytesStats.value)
        }

        Subtitle(R.string.network_stats_disclaimer)

        Button(onClick = { viewModel.refresh() }) {
            Icon(imageVector = Icons.Default.Refresh, contentDescription = null)
            Text(stringResource(R.string.refresh))
        }
    }
}

@Composable
fun BytesStatsView(
    bytesStats: BytesStats
) {
    CenteredVerticalContainer {
        BidirectionalBytesStatsView(
            R.string.network_mobile,
            bytesStats.mobile
        )
        BidirectionalBytesStatsView(
            R.string.network_network,
            bytesStats.network
        )
        BidirectionalBytesStatsView(
            R.string.network_total,
            bytesStats.total
        )
        BidirectionalBytesStatsView(
            R.string.network_process,
            bytesStats.process
        )
        Text(stringResource(R.string.network_percent_was_used_for_process, bytesStats.processRatio))
    }
}

@Composable
fun BidirectionalBytesStatsView(
    bytesOrigin: Int,
    bytes: BidirectionalBytes
) {
    CardContainer {
        CenteredHorizontalContainer {
            Subtitle(bytesOrigin)
            Column(verticalArrangement = Arrangement.Center) {
                UpByteStatView(bytes.Tx)
                DownByteStatView(bytes.Rx)
            }
        }
    }
}

@Composable
fun UpByteStatView(bytes: Long) = ByteStatView(bytes, Icons.Default.ArrowUpward)

@Composable
fun DownByteStatView(bytes: Long) = ByteStatView(bytes, Icons.Default.ArrowDownward)

@Composable
private fun ByteStatView(
    bytes: Long,
    icon: ImageVector
) {
    CenteredHorizontalContainer {
        Icon(
            imageVector = icon,
            contentDescription = icon.toString()
        )
        Text(text = "$bytes bytes")
    }
}
