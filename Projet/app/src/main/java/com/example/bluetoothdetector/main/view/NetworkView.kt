package com.example.bluetoothdetector.main.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDownward
import androidx.compose.material.icons.filled.ArrowUpward
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.bluetoothdetector.R
import com.example.bluetoothdetector.common.view.containers.CardContainer
import com.example.bluetoothdetector.common.view.containers.CenteredHorizontalContainer
import com.example.bluetoothdetector.common.view.containers.CenteredVerticalContainer
import com.example.bluetoothdetector.common.view.containers.WeightedCard
import com.example.bluetoothdetector.common.view.typography.Subtitle
import com.example.bluetoothdetector.main.domain.BidirectionalBytes
import com.example.bluetoothdetector.main.domain.BytesStats
import com.example.bluetoothdetector.main.viewmodel.NetworkViewModel


@Composable
fun NetworkView(
    viewModel: NetworkViewModel = hiltViewModel()
) {
    CenteredVerticalContainer {

        IconButton(onClick = { viewModel.refresh() }) {
            Icon(imageVector = Icons.Default.Refresh, contentDescription = null)
        }

        CenteredHorizontalContainer {
            WeightedCard(weight = 0.5f) {
                BytesStatsView(
                    R.string.network_since_activity_created,
                    viewModel.networkRepository.bytesSinceCreated.value
                )
            }

            WeightedCard(weight = 0.5f) {
                BytesStatsView(
                    R.string.network_since_activity_resumed,
                    viewModel.networkRepository.bytesSinceResumed.value
                )
            }
        }

        Subtitle(R.string.network_stats_disclaimer)
    }
}

@Composable
fun BytesStatsView(
    bytesOrigin: Int,
    bytesStats: BytesStats
) {
    CenteredVerticalContainer {
        Subtitle(bytesOrigin)
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
