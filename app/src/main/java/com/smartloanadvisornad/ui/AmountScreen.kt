package com.smartloanadvisornad.ui

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.BorderStroke
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
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
import com.smartloanadvisornad.ui.theme.grey
import com.smartloanadvisornad.ui.theme.greyText
import com.smartloanadvisornad.ui.theme.textColor
import com.smartloanadvisornad.ui.uikit.CustomButton
import com.smartloanadvisornad.ui.uikit.TopRow

@Composable
fun AmountScreen(
    modifier: Modifier = Modifier,
    sharedAmount: Int,
    onEvent: (MainEvent) -> Unit,
) {
    BackHandler {
        onEvent(MainEvent.OnChangeStatusApplication(StatusApplication.Main))
    }
    val amount = remember { mutableIntStateOf(sharedAmount) }
    Scaffold(
        modifier = modifier.fillMaxSize(),
        containerColor = background,
        topBar = {
            TopRow(
                onClickBack = {
                    onEvent(MainEvent.OnChangeStatusApplication(StatusApplication.Main))
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
                enabled = amount.intValue!=0,
                contentPadding = PaddingValues(vertical = 12.dp),
                onClick = {
                    onEvent(MainEvent.OnSetAmount(amount.intValue))
                    onEvent(MainEvent.OnChangeStatusApplication(StatusApplication.PeriodState))
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
                text = stringResource(R.string.amount_of_loan),
                style = TextStyle(
                    fontSize = 24.sp,
                    fontFamily = FontFamily(Font(R.font.inter)),
                    fontWeight = FontWeight(600),
                    color = black
                )
            )
            Spacer(modifier = modifier.height(30.dp))
            Card(
                modifier = modifier
                    .fillMaxWidth(),
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color.Transparent
                ),
                border = BorderStroke(width = 1.dp, color = grey)
            ) {
                Text(
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    text = amount.intValue.toString(),
                    style = TextStyle(
                        fontSize = 16.sp,
                        fontFamily = FontFamily(Font(R.font.inter)),
                        fontWeight = FontWeight(600),
                        color = black
                    )
                )
            }
            Spacer(modifier = modifier.height(6.dp))
            Text(
                text = stringResource(R.string.limits),
                style = TextStyle(
                    fontSize = 12.sp,
                    fontFamily = FontFamily(Font(R.font.inter)),
                    fontWeight = FontWeight(300),
                    color = textColor
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
                    colorBack = if (amount.intValue ==3000) textColor else greyText,
                    colorText = if (amount.intValue ==3000) background else black,
                    title = stringResource(R.string._3000),
                    onClick = {
                        amount.intValue = 3000
                    }
                )
                Spacer(modifier = modifier.width(15.dp))
                CustomButton(
                    modifier = modifier.weight(1f),
                    colorBack = if (amount.intValue ==5000) textColor else greyText,
                    colorText = if (amount.intValue ==5000) background else black,
                    title = stringResource(R.string._5000),
                    onClick = {
                        amount.intValue = 5000
                    }
                )
                Spacer(modifier = modifier.width(15.dp))
                CustomButton(
                    modifier = modifier.weight(1f),
                    colorBack = if (amount.intValue ==7000) textColor else greyText,
                    colorText = if (amount.intValue ==7000) background else black,
                    title = stringResource(R.string._7000),
                    onClick = {
                        amount.intValue = 7000
                    }
                )
                Spacer(modifier = modifier.width(15.dp))
                CustomButton(
                    modifier = modifier.weight(1f),
                    colorBack = if (amount.intValue ==10000) textColor else greyText,
                    colorText = if (amount.intValue ==10000) background else black,
                    title = stringResource(R.string._10_000),
                    onClick = {
                        amount.intValue = 10000
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
                    colorBack = if (amount.intValue ==15000) textColor else greyText,
                    colorText = if (amount.intValue ==15000) background else black,
                    title = stringResource(R.string._15_000),
                    onClick = {
                        amount.intValue = 15000
                    }
                )
                Spacer(modifier = modifier.width(15.dp))
                CustomButton(
                    modifier = modifier.weight(1f),
                    colorBack = if (amount.intValue ==20000) textColor else greyText,
                    colorText = if (amount.intValue ==20000) background else black,
                    title = stringResource(R.string._20_000),
                    onClick = {
                        amount.intValue = 20000
                    }
                )
                Spacer(modifier = modifier.width(15.dp))
                CustomButton(
                    modifier = modifier.weight(1f),
                    colorBack = if (amount.intValue ==30000) textColor else greyText,
                    colorText = if (amount.intValue ==30000) background else black,
                    title = stringResource(R.string._30_000),
                    onClick = {
                        amount.intValue = 30000
                    }
                )
                Spacer(modifier = modifier.width(15.dp))
                CustomButton(
                    modifier = modifier.weight(1f),
                    colorBack = if (amount.intValue == 50000) textColor else greyText,
                    colorText = if (amount.intValue == 50000) background else black,
                    title = stringResource(R.string._50_000),
                    onClick = {
                        amount.intValue = 50000
                    }
                )
            }
        }
    }
}