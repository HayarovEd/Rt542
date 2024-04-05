package com.smartloanadvisornad.ui.uikit

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDownward
import androidx.compose.material.icons.filled.ArrowUpward
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.smartloanadvisornad.R
import com.smartloanadvisornad.ui.theme.black
import com.smartloanadvisornad.ui.theme.green
import com.smartloanadvisornad.ui.theme.red

@Composable
fun RowCurrency(
    modifier: Modifier = Modifier,
    icon: String,
    name: String,
    prevValue: Double,
    diffValue: Double,
    value:Double
) {
    val iconChange = if (diffValue>=0) Icons.Filled.ArrowUpward else Icons.Filled.ArrowDownward
    val colorChange =  if (diffValue>=0) green else red
    Row (
        modifier = modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            text = icon,
            style = TextStyle(
                fontSize = 16.sp,
                fontFamily = FontFamily(Font(R.font.inter)),
                fontWeight = FontWeight(500),
                color = red
            )
        )
        Text(
            text = name,
            style = TextStyle(
                fontSize = 13.sp,
                fontFamily = FontFamily(Font(R.font.inter)),
                fontWeight = FontWeight(500),
                color = black
            )
        )
        Spacer(modifier = modifier.weight(1f))
        Text(
            text = String.format("%.2f", prevValue),
            style = TextStyle(
                fontSize = 13.sp,
                fontFamily = FontFamily(Font(R.font.inter)),
                fontWeight = FontWeight(500),
                color = black
            )
        )
        Spacer(modifier = modifier.width(34.dp))
        Icon(
            imageVector = iconChange,
            contentDescription = "",
            tint = colorChange)
        Text(
            text = String.format("%.2f", diffValue),
            style = TextStyle(
                fontSize = 13.sp,
                fontFamily = FontFamily(Font(R.font.inter)),
                fontWeight = FontWeight(500),
                color = colorChange
            )
        )
        Spacer(modifier = modifier.width(34.dp))
        Text(
            text = String.format("%.2f", value),
            style = TextStyle(
                fontSize = 13.sp,
                fontFamily = FontFamily(Font(R.font.inter)),
                fontWeight = FontWeight(500),
                color = black
            )
        )
    }
}