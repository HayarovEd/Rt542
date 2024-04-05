package com.smartloanadvisornad.ui

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.Policy
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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
import com.smartloanadvisornad.domain.model.CbrData
import com.smartloanadvisornad.ui.state.MainEvent
import com.smartloanadvisornad.ui.state.StatusApplication
import com.smartloanadvisornad.ui.theme.background
import com.smartloanadvisornad.ui.theme.black
import com.smartloanadvisornad.ui.theme.grey
import com.smartloanadvisornad.ui.theme.greyText
import com.smartloanadvisornad.ui.theme.red
import com.smartloanadvisornad.ui.theme.textColor
import com.smartloanadvisornad.ui.uikit.RowCurrency

@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    lastApplicationState: StatusApplication,
    cbrData: CbrData?,
    onEvent: (MainEvent) -> Unit,
) {
    BackHandler {
        onEvent(MainEvent.OnChangeStatusApplication(lastApplicationState))
    }
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(color = background)
    ) {
        Image(
            modifier = modifier
                .fillMaxSize(),
            painter = painterResource(id = R.drawable.back_main),
            contentDescription = "",
            contentScale = ContentScale.FillBounds
        )
        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp, vertical = 15.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            IconButton(
                modifier = modifier
                    .clip(shape = CircleShape)
                    .border(width = 1.dp, color = grey, shape = CircleShape),
                onClick = {
                    onEvent(MainEvent.OnChangeStatusApplication(lastApplicationState))
                }) {
                Icon(
                    imageVector = Icons.Filled.ArrowBackIosNew,
                    contentDescription = "",
                    tint = textColor
                )
            }
            Text(
                text = stringResource(R.string.get_loan),
                style = TextStyle(
                    fontSize = 18.sp,
                    fontFamily = FontFamily(Font(R.font.inter)),
                    fontWeight = FontWeight(600),
                    color = black
                )
            )
            IconButton(
                modifier = modifier
                    .clip(shape = CircleShape)
                    .border(width = 1.dp, color = grey, shape = CircleShape),
                onClick = {
                    onEvent(MainEvent.OnChangeStatusApplication(StatusApplication.TermState(
                        onClick = {
                            onEvent(
                                MainEvent.OnChangeStatusApplication(
                                    StatusApplication.Main
                                )
                            )
                        }
                    )))
                }) {
                Icon(
                    imageVector = Icons.Filled.Policy,
                    contentDescription = "",
                    tint = textColor
                )
            }
        }
        Column(
            modifier = modifier
                .align(alignment = Alignment.BottomCenter)
                .padding(start = 24.dp, end = 24.dp, bottom = 31.dp)
        ) {
            if (cbrData != null) {
                Text(
                    text = stringResource(R.string.currency_course),
                    style = TextStyle(
                        fontSize = 18.sp,
                        fontFamily = FontFamily(Font(R.font.inter)),
                        fontWeight = FontWeight(600),
                        color = black
                    )
                )
                Spacer(modifier = modifier.height(15.dp))
                Row (
                    modifier = modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.End
                ) {
                    Text(
                        text = cbrData.previousDate,
                        style = TextStyle(
                            fontSize = 13.sp,
                            fontFamily = FontFamily(Font(R.font.inter)),
                            fontWeight = FontWeight(500),
                            color = greyText
                        )
                    )
                    Spacer(modifier = modifier.width(93.dp))
                    Text(
                        text = cbrData.date,
                        style = TextStyle(
                            fontSize = 13.sp,
                            fontFamily = FontFamily(Font(R.font.inter)),
                            fontWeight = FontWeight(500),
                            color = greyText
                        )
                    )
                }
                Spacer(modifier = modifier.height(5.dp))
                RowCurrency(
                    icon = "\$",
                    name = cbrData.currency.uSD.charCode,
                    prevValue = cbrData.currency.uSD.previous,
                    diffValue = cbrData.currency.uSD.changeValue,
                    value = cbrData.currency.uSD.value
                )
                Spacer(modifier = modifier.height(2.dp))
                RowCurrency(
                    icon = "€",
                    name = cbrData.currency.eUR.charCode,
                    prevValue = cbrData.currency.eUR.previous,
                    diffValue = cbrData.currency.eUR.changeValue,
                    value = cbrData.currency.eUR.value
                )
                Spacer(modifier = modifier.height(2.dp))
                RowCurrency(
                    icon = "¥",
                    name = cbrData.currency.cNY.charCode,
                    prevValue = cbrData.currency.cNY.previous,
                    diffValue = cbrData.currency.cNY.changeValue,
                    value = cbrData.currency.cNY.value
                )
                Spacer(modifier = modifier.height(15.dp))
            }
            Button(
                modifier = modifier
                    .fillMaxWidth(),
                shape = RoundedCornerShape(5.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = textColor
                ),
                contentPadding = PaddingValues(vertical = 12.dp),
                onClick = {
                    onEvent(MainEvent.OnChangeStatusApplication(StatusApplication.AmountState))
                }) {
                Text(
                    text = stringResource(R.string.get_loan),
                    style = TextStyle(
                        fontSize = 14.sp,
                        fontFamily = FontFamily(Font(R.font.inter)),
                        fontWeight = FontWeight(600),
                        color = background
                    )
                )
            }
        }
    }
}