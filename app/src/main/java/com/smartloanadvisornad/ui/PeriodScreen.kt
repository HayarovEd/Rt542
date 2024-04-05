package com.smartloanadvisornad.ui

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
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
import com.smartloanadvisornad.ui.theme.greyText
import com.smartloanadvisornad.ui.theme.textColor
import com.smartloanadvisornad.ui.uikit.CustomButton
import com.smartloanadvisornad.ui.uikit.TopRow

@Composable
fun PeriodScreen(
    modifier: Modifier = Modifier,
    sharedPeriod: Int,
    onEvent: (MainEvent) -> Unit,
) {
    BackHandler {
        onEvent(MainEvent.OnChangeStatusApplication(StatusApplication.AmountState))
    }
    val period = remember { mutableIntStateOf(sharedPeriod) }
    Scaffold(
        modifier = modifier.fillMaxSize(),
        containerColor = background,
        topBar = {
            TopRow(
                onClickBack = {
                    onEvent(MainEvent.OnChangeStatusApplication(StatusApplication.AmountState))
                },
            )
        },
        bottomBar = {
            Button(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(24.dp),
                shape = RoundedCornerShape(5.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = textColor,
                    disabledContainerColor = textColor
                ),
                enabled = period.intValue!=0,
                contentPadding = PaddingValues(vertical = 12.dp),
                onClick = {
                    onEvent(MainEvent.OnSetPeriod(period.intValue))
                    onEvent(MainEvent.OnChangeStatusApplication(StatusApplication.NameState))
                }) {
                Text(
                    text = stringResource(R.string.next),
                    style = TextStyle(
                        fontSize = 14.sp,
                        fontFamily = FontFamily(Font(R.font.inter)),
                        fontWeight = FontWeight(600),
                        color = background
                    )
                )
            }
        }
    ) { paddings ->
        Column(
            modifier = modifier
                .padding(paddings)
                .fillMaxSize()
                .background(color = background)
                .padding(horizontal = 24.dp),
        ) {
            Text(
                text = stringResource(R.string.period),
                style = TextStyle(
                    fontSize = 24.sp,
                    fontFamily = FontFamily(Font(R.font.inter)),
                    fontWeight = FontWeight(600),
                    color = black
                )
            )
            Spacer(modifier = modifier.height(35.dp))
            Row(
                modifier = modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                CustomButton(
                    modifier = modifier.weight(1f),
                    colorBack = if (period.intValue == 2) textColor else greyText,
                    colorText = if (period.intValue == 2) background else black,
                    title = stringResource(R.string._2_month),
                    onClick = {
                        period.intValue = 2
                    }
                )
                Spacer(modifier = modifier.width(15.dp))
                CustomButton(
                    modifier = modifier.weight(1f),
                    colorBack = if (period.intValue == 3) textColor else greyText,
                    colorText = if (period.intValue == 3) background else black,
                    title = stringResource(R.string._month),
                    onClick = {
                        period.intValue = 3
                    }
                )
                Spacer(modifier = modifier.width(15.dp))
                CustomButton(
                    modifier = modifier.weight(1f),
                    colorBack = if (period.intValue == 4) textColor else greyText,
                    colorText = if (period.intValue == 4) background else black,
                    title = stringResource(R.string._4_month),
                    onClick = {
                        period.intValue = 4
                    }
                )
                Spacer(modifier = modifier.width(15.dp))
                CustomButton(
                    modifier = modifier.weight(1f),
                    colorBack = if (period.intValue == 5) textColor else greyText,
                    colorText = if (period.intValue == 5) background else black,
                    title = stringResource(R.string._5_month),
                    onClick = {
                        period.intValue = 5
                    }
                )
            }
            Spacer(modifier = modifier.height(15.dp))
            Row(
                modifier = modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                CustomButton(
                    modifier = modifier.weight(1f),
                    colorBack = if (period.intValue == 6) textColor else greyText,
                    colorText = if (period.intValue == 6) background else black,
                    title = stringResource(R.string._6_month),
                    onClick = {
                        period.intValue = 6
                    }
                )
                Spacer(modifier = modifier.width(15.dp))
                CustomButton(
                    modifier = modifier.weight(1f),
                    colorBack = if (period.intValue == 7) textColor else greyText,
                    colorText = if (period.intValue == 7) background else black,
                    title = stringResource(R.string._7_month),
                    onClick = {
                        period.intValue = 7
                    }
                )
                Spacer(modifier = modifier.width(15.dp))
                CustomButton(
                    modifier = modifier.weight(1f),
                    colorBack = if (period.intValue == 8) textColor else greyText,
                    colorText = if (period.intValue == 8) background else black,
                    title = stringResource(R.string._8_month),
                    onClick = {
                        period.intValue = 8
                    }
                )
                Spacer(modifier = modifier.width(15.dp))
                CustomButton(
                    modifier = modifier.weight(1f),
                    colorBack = if (period.intValue == 9) textColor else greyText,
                    colorText = if (period.intValue == 9) background else black,
                    title = stringResource(R.string._9_month),
                    onClick = {
                        period.intValue = 9
                    }
                )
            }
            Spacer(modifier = modifier.height(15.dp))
            Row(
                modifier = modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                CustomButton(
                    modifier = modifier.weight(1f),
                    colorBack = if (period.intValue == 10) textColor else greyText,
                    colorText = if (period.intValue == 10) background else black,
                    title = stringResource(R.string._10_month),
                    onClick = {
                        period.intValue = 10
                    }
                )
                Spacer(modifier = modifier.width(15.dp))
                CustomButton(
                    modifier = modifier.weight(1f),
                    colorBack = if (period.intValue == 11) textColor else greyText,
                    colorText = if (period.intValue == 11) background else black,
                    title = stringResource(R.string._11_month),
                    onClick = {
                        period.intValue = 11
                    }
                )
                Spacer(modifier = modifier.width(15.dp))
                CustomButton(
                    modifier = modifier.weight(1f),
                    colorBack = if (period.intValue == 12) textColor else greyText,
                    colorText = if (period.intValue == 12) background else black,
                    title = stringResource(R.string._1_year),
                    onClick = {
                        period.intValue = 12
                    }
                )
                Spacer(modifier = modifier.width(15.dp))
                CustomButton(
                    modifier = modifier.weight(1f),
                    colorBack = if (period.intValue == 13) textColor else greyText,
                    colorText = if (period.intValue == 13) background else black,
                    title = stringResource(R.string._2_years),
                    onClick = {
                        period.intValue = 13
                    }
                )
            }
        }
    }
}