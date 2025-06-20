package com.moniepoint.core.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.moniepoint.core.designsystem.icon.MoniePointIcons
import com.moniepoint.core.designsystem.theme.DecorativeColor
import com.moniepoint.core.designsystem.theme.MoniePointTheme
import com.moniepoint.core.model.entity.ShipmentEntity
import com.moniepoint.core.model.enums.ShipmentStatus
import kotlinx.datetime.toKotlinLocalDateTime

@Composable
fun SearchItem(
    shipment: ShipmentEntity
) {
    Row(
        modifier = Modifier.padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Icon(
            painter = painterResource(MoniePointIcons.Box),
            contentDescription = null,
            modifier = Modifier
                .size(36.dp)
                .background(color = MaterialTheme.colorScheme.primary, shape = CircleShape)
                .padding(8.dp),
            tint = Color.White
        )
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                shipment.title,
                style = MaterialTheme.typography.labelLarge.copy(color = DecorativeColor.black),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(Modifier.height(8.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(
                    shipment.shipmentId,
                    style = MaterialTheme.typography.bodyMedium.copy(color = MaterialTheme.colorScheme.tertiary),
                    lineHeight = 16.sp,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Icon(
                    MoniePointIcons.Circle,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.tertiary,
                    modifier = Modifier.size(8.dp)
                )
                Text(
                    shipment.origin,
                    style = MaterialTheme.typography.bodyMedium.copy(color = MaterialTheme.colorScheme.tertiary),
                    lineHeight = 16.sp,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Icon(
                    MoniePointIcons.ForwardArrow,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.tertiary,
                    modifier = Modifier.size(16.dp)
                )
                Text(
                    shipment.destination,
                    style = MaterialTheme.typography.bodyMedium.copy(color = MaterialTheme.colorScheme.tertiary),
                    lineHeight = 16.sp,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }

        }
    }
}

@Composable
@Preview(showBackground = true)
private fun SearchItemPreview() {
    MoniePointTheme {
        SearchItem(
            shipment = ShipmentEntity(
                "Summer linen jacket",
                "#NEJ39392903992",
                ShipmentStatus.InProgress,
                deliveryDate = java.time.LocalDateTime.now().toKotlinLocalDateTime(),
                createdDate = java.time.LocalDateTime.now().toKotlinLocalDateTime(),
                origin = "Barcelona",
                destination = "Morroco",
                fee = 2901.00
            )
        )
    }
}