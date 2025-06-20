package com.moniepoint.core.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.moniepoint.core.designsystem.component.MoniePointIconText
import com.moniepoint.core.designsystem.icon.MoniePointIcons
import com.moniepoint.core.designsystem.theme.DecorativeColor
import com.moniepoint.core.designsystem.theme.MoniePointTheme
import com.moniepoint.core.model.entity.ShipmentEntity
import com.moniepoint.core.model.enums.ShipmentStatus
import com.moniepoint.core.ui.util.formatAmount
import kotlinx.datetime.toKotlinLocalDateTime

@Composable
fun ShipmentItem(
    shipment: ShipmentEntity,
) {
    Card {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Column(
                modifier = Modifier.weight(1f)
            ) {
                val color = when (shipment.status) {
                    ShipmentStatus.Completed -> DecorativeColor.green02
                    ShipmentStatus.InProgress -> DecorativeColor.green02
                    ShipmentStatus.PendingOrder -> DecorativeColor.orange02
                    ShipmentStatus.Loading -> DecorativeColor.blue02
                    ShipmentStatus.Cancelled -> DecorativeColor.red02
                }
                MoniePointIconText(
                    text = buildAnnotatedString {
                        withStyle(
                            MaterialTheme.typography.labelMedium.copy(color = color)
                                .toSpanStyle()
                        ) {
                            append(shipment.status.label)
                        }
                    },
                    leadingIcon = {
                        Icon(
                            MoniePointIcons.InProgress,
                            contentDescription = null,
                            tint = color,
                            modifier = Modifier.size(16.dp)
                        )
                    },
                    backgroundShape = CircleShape,
                    backgroundColor = MaterialTheme.colorScheme.primaryContainer,
                    contentPaddingValues = PaddingValues(vertical = 2.dp, horizontal = 6.dp)
                )
                Spacer(Modifier.height(8.dp))
                Text(
                    "Arriving today!",
                    style = MaterialTheme.typography.titleMedium.copy(color = DecorativeColor.black),
                )
                Spacer(Modifier.height(8.dp))
                Text(
                    "Your delivery, ${shipment.shipmentId} from Atlanta, is arriving today!",
                    style = MaterialTheme.typography.bodyMedium.copy(color = MaterialTheme.colorScheme.tertiary),
                    lineHeight = 16.sp
                )
                Spacer(Modifier.height(16.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text(
                        "${formatAmount(shipment.fee)} USD",
                        style = MaterialTheme.typography.labelLarge.copy(color = MaterialTheme.colorScheme.primary)
                    )
                    Icon(
                        MoniePointIcons.Circle,
                        contentDescription = null,
                        modifier = Modifier.size(6.dp),
                        tint = MaterialTheme.colorScheme.tertiaryContainer
                    )
                    Text(
                        "Sep 20, 2023",
                        style = MaterialTheme.typography.labelMedium.copy(color = MaterialTheme.colorScheme.tertiary)
                    )
                }
            }
            Icon(
                painter = painterResource(MoniePointIcons.Package),
                null,
                modifier = Modifier.size(52.dp),
                tint = Color.Unspecified
            )
        }
    }
}

@Composable
@Preview
private fun ShipmentItemPreview() {
    MoniePointTheme {
        ShipmentItem(
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