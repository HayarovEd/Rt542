package com.smartloanadvisornad.ui

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
import com.smartloanadvisornad.ui.theme.black

@Composable
fun WaitingScreen(
    modifier: Modifier = Modifier,
    onEvent: (MainEvent) -> Unit,
) {
    BackHandler {
        onEvent(MainEvent.OnChangeStatusApplication(StatusApplication.Main))
    }
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(color = background)
            .padding(24.dp)
    ) {
        Text(
            modifier = modifier
                .fillMaxWidth()
                .align(alignment = Alignment.Center),
            text = stringResource(R.string.appointment),
            style = TextStyle(
                fontSize = 24.sp,
                fontFamily = FontFamily(Font(R.font.inter)),
                fontWeight = FontWeight(600),
                color = black
            )
        )
    }
}