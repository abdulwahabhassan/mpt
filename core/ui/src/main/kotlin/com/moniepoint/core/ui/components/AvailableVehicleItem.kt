package com.moniepoint.core.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.moniepoint.core.designsystem.R
import com.moniepoint.core.designsystem.theme.DecorativeColor
import com.moniepoint.core.designsystem.theme.MoniePointTheme
import com.moniepoint.core.model.enums.Vehicle

@Composable
fun AvailableVehicleItem(
    vehicle: Vehicle,
    isVisible: Boolean = false
) {
    Card(shape = MaterialTheme.shapes.small) {
        Box(
            modifier = Modifier
                .size(height = 170.dp, width = 130.dp)
                .padding(start = 12.dp, top = 12.dp, bottom = 12.dp)
        ) {
            Column(
                modifier = Modifier.align(Alignment.BottomEnd)
            ) {
                AnimatedVisibility(
                    isVisible,
                    enter = slideInHorizontally(
                        initialOffsetX = { it },
                    ),
                    exit = fadeOut()
                ) {
                    Image(
                        modifier = Modifier
                            .size(120.dp)
                            .offset(x = 30.dp),
                        painter = painterResource(
                            when (vehicle) {
                                Vehicle.OceanFreight -> R.drawable.ic_cargo_ship
                                Vehicle.CargoFreight -> R.drawable.ic_cargo_truck
                                Vehicle.AirFreight -> R.drawable.ic_airplane
                            }
                        ),
                        contentDescription = null
                    )
                }
            }
            Column {
                Text(
                    vehicle.title,
                    style = MaterialTheme.typography.bodyMedium.copy(
                        color = DecorativeColor.black,
                        fontSize = 15.sp
                    ),
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    vehicle.subTitle,
                    style = MaterialTheme.typography.bodySmall.copy(color = MaterialTheme.colorScheme.tertiary),
                )
            }
        }
    }
}

@Composable
@Preview
private fun AvailableVehicleItemPreview() {
    MoniePointTheme {
        AvailableVehicleItem(vehicle = Vehicle.AirFreight, true)
    }
}

@Composable
@Preview
private fun AvailableVehicleItemPreview2() {
    MoniePointTheme {
        AvailableVehicleItem(vehicle = Vehicle.CargoFreight, true)
    }
}

@Composable
@Preview
private fun AvailableVehicleItemPreview3() {
    MoniePointTheme {
        AvailableVehicleItem(vehicle = Vehicle.OceanFreight, true)
    }
}