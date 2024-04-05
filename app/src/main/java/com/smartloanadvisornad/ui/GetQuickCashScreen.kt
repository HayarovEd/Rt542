package com.smartloanadvisornad.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.smartloanadvisornad.R
import com.smartloanadvisornad.ui.state.MainEvent
import com.smartloanadvisornad.ui.state.StatusApplication
import com.smartloanadvisornad.ui.theme.background
import com.smartloanadvisornad.ui.theme.textColor

@Composable
fun GetQuickCashScreen(
    modifier: Modifier = Modifier,
    onEvent: (MainEvent) -> Unit,
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(color = background)
    ) {
        Image(
            modifier = modifier
                .fillMaxSize(),
            painter = painterResource(id = R.drawable.back_on_1),
            contentDescription = "",
            contentScale = ContentScale.FillBounds
        )
        Button(
            modifier = modifier
                .align(alignment = Alignment.BottomCenter)
                .padding(start = 24.dp, end = 24.dp, bottom = 31.dp)
                .fillMaxWidth(),
            shape = RoundedCornerShape(5.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = background
            ),
            contentPadding = PaddingValues(vertical = 12.dp),
            onClick = {
                onEvent(MainEvent.OnChangeStatusApplication(StatusApplication.QuickCash))
        }) {
            Text(
                text = stringResource(R.string.next),
                style = TextStyle(
                    fontSize = 14.sp,
                    fontFamily = FontFamily(Font(R.font.inter)),
                    fontWeight = FontWeight(600),
                    color = textColor
                )
            )
        }
    }
}