package com.moniepoint.core.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.HorizontalDivider
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
import com.moniepoint.core.designsystem.component.MoniePointClickableIconText
import com.moniepoint.core.designsystem.icon.MoniePointIcons
import com.moniepoint.core.designsystem.theme.DecorativeColor
import com.moniepoint.core.designsystem.theme.MoniePointTheme

@Composable
fun TrackingCard() {
    Card {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier
                    .padding(horizontal = 16.dp, vertical = 8.dp)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Text(
                        "Shipment Number",
                        style = MaterialTheme.typography.bodyMedium.copy(color = MaterialTheme.colorScheme.tertiary),
                        lineHeight = 16.sp
                    )
                    Text(
                        "NEJ2008399925144111",
                        style = MaterialTheme.typography.titleMedium.copy(color = DecorativeColor.black)
                    )
                }
                Icon(
                    painter = painterResource(MoniePointIcons.Forklift),
                    null,
                    modifier = Modifier.size(40.dp),
                    tint = Color.Unspecified
                )
            }
            HorizontalDivider(
                thickness = 0.5.dp,
                color = MaterialTheme.colorScheme.tertiaryContainer,
            )
            Row(
                modifier = Modifier
                    .padding(horizontal = 16.dp, vertical = 16.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column(
                    verticalArrangement = Arrangement.spacedBy(32.dp),
                    horizontalAlignment = Alignment.Start
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Icon(
                            painter = painterResource(MoniePointIcons.Sender),
                            contentDescription = null,
                            modifier = Modifier
                                .size(36.dp)
                                .background(color = DecorativeColor.orange01, shape = CircleShape)
                                .padding(8.dp),
                            tint = Color.Unspecified
                        )
                        Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                            Text(
                                "Sender",
                                style = MaterialTheme.typography.bodySmall.copy(
                                    color = MaterialTheme.colorScheme.tertiary
                                ),
                            )
                            Text(
                                "Atlanta, 5243",
                                style = MaterialTheme.typography.bodyMedium.copy(
                                    color = DecorativeColor.black
                                ),
                            )
                        }
                    }
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Icon(
                            painter = painterResource(MoniePointIcons.Receiver),
                            contentDescription = null,
                            modifier = Modifier
                                .size(36.dp)
                                .background(color = DecorativeColor.green01, shape = CircleShape)
                                .padding(8.dp),
                            tint = Color.Unspecified
                        )
                        Column {
                            Text(
                                "Receiver",
                                style = MaterialTheme.typography.bodySmall.copy(
                                    color = MaterialTheme.colorScheme.tertiary
                                ),
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                "Atlanta, 5243",
                                style = MaterialTheme.typography.bodyMedium.copy(
                                    color = DecorativeColor.black
                                ),
                            )
                        }
                    }
                }
                Column(
                    verticalArrangement = Arrangement.spacedBy(32.dp),
                    horizontalAlignment = Alignment.Start
                ) {
                    Column(
                        verticalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        Text(
                            "Time",
                            style = MaterialTheme.typography.bodySmall.copy(
                                color = MaterialTheme.colorScheme.tertiary
                            ),
                        )
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Icon(
                                MoniePointIcons.Circle,
                                contentDescription = null,
                                tint = DecorativeColor.green02,
                                modifier = Modifier.size(8.dp)
                            )
                            Text(
                                "2 day - 3 days",
                                style = MaterialTheme.typography.bodyMedium.copy(
                                    color = DecorativeColor.black
                                ),
                            )
                        }
                    }
                    Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                        Text(
                            "Status",
                            style = MaterialTheme.typography.bodySmall.copy(
                                color = MaterialTheme.colorScheme.tertiary
                            ),
                        )
                        Text(
                            "Waiting to collect",
                            style = MaterialTheme.typography.bodyMedium.copy(
                                color = DecorativeColor.black
                            ),
                        )
                    }
                }
            }
            HorizontalDivider(
                thickness = 0.5.dp,
                color = MaterialTheme.colorScheme.tertiaryContainer,
            )
            MoniePointClickableIconText(
                modifier = Modifier.padding(4.dp),
                onClick = {

                },
                text = buildAnnotatedString {
                    withStyle(
                        MaterialTheme.typography.bodyMedium.copy(
                            color = DecorativeColor.orange02
                        ).toSpanStyle()
                    ) {
                        append("Add Stop")
                    }
                },
                leadingIcon = {
                    Icon(
                        MoniePointIcons.Plus,
                        contentDescription = null,
                        tint = DecorativeColor.orange02
                    )
                },
            )
        }
    }
}

@Composable
@Preview
private fun TrackingCardPreview() {
    MoniePointTheme {
        TrackingCard()
    }
}