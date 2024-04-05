package com.smartloanadvisornad.ui.uikit

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedIconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.smartloanadvisornad.R
import com.smartloanadvisornad.ui.state.MainEvent
import com.smartloanadvisornad.ui.theme.black
import com.smartloanadvisornad.ui.theme.grey
import com.smartloanadvisornad.ui.theme.textColor

@Composable
fun TopRow(
    modifier: Modifier = Modifier,
    onClickBack: () -> Unit,
) {
    Box(modifier = modifier
        .fillMaxWidth()
        .padding(horizontal = 24.dp, vertical = 15.dp)) {
        IconButton(
            modifier = modifier
                .align(alignment = Alignment.CenterStart)
                .clip(shape = CircleShape)
                .border(width = 1.dp, color = grey, shape = CircleShape),
            onClick = onClickBack) {
            Icon(
                imageVector = Icons.Filled.ArrowBackIosNew,
                contentDescription = "",
                tint = textColor
            )
        }
        Text(
            modifier = modifier
                .align(alignment = Alignment.Center),
            text = stringResource(R.string.get_loan),
            style = TextStyle(
                fontSize = 18.sp,
                fontFamily = FontFamily(Font(R.font.inter)),
                fontWeight = FontWeight(600),
                color = black
            )
        )
    }
}