package com.moniepoint.core.ui.util

import java.text.DecimalFormat
import java.text.DecimalFormatSymbols

fun formatAmount(value: Number): String {
    val df = DecimalFormat("##,###,##0")
    return df.format(value)
}

