package com.smartloanadvisornad.ui

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.smartloanadvisornad.R
import com.smartloanadvisornad.domain.utils.DEMO_EMAIL
import com.smartloanadvisornad.domain.utils.isValidEmail
import com.smartloanadvisornad.ui.state.MainEvent
import com.smartloanadvisornad.ui.state.StatusApplication
import com.smartloanadvisornad.ui.theme.background
import com.smartloanadvisornad.ui.theme.black
import com.smartloanadvisornad.ui.theme.green
import com.smartloanadvisornad.ui.theme.grey
import com.smartloanadvisornad.ui.theme.greyText
import com.smartloanadvisornad.ui.theme.textColor
import com.smartloanadvisornad.ui.uikit.TopRow

@Composable
fun PhoneScreen(
    modifier: Modifier = Modifier,
    sharedPhone: String,
    sharedEmail: String,
    onEvent: (MainEvent) -> Unit,
) {
    BackHandler {
        onEvent(MainEvent.OnChangeStatusApplication(StatusApplication.EmailState))
    }
    val phone = remember { mutableStateOf(sharedPhone) }
    Scaffold(
        modifier = modifier.fillMaxSize(),
        containerColor = background,
        topBar = {
            TopRow(
                onClickBack = {
                    onEvent(MainEvent.OnChangeStatusApplication(StatusApplication.EmailState))
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
                enabled = phone.value.isNotBlank(),
                contentPadding = PaddingValues(vertical = 12.dp),
                onClick = {
                    onEvent(MainEvent.OnSetPhone(phone.value))
                    onEvent(MainEvent.SetFirstLoad)
                    onEvent(MainEvent.SaveLocalData)
                    onEvent(MainEvent.SendUserData(StatusApplication.PhoneState))
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
                text = stringResource(R.string.enter_phone),
                style = TextStyle(
                    fontSize = 24.sp,
                    fontFamily = FontFamily(Font(R.font.inter)),
                    fontWeight = FontWeight(600),
                    color = black
                )
            )
            Spacer(modifier = modifier.height(30.dp))
            Text(
                text = stringResource(R.string.your_phone),
                style = TextStyle(
                    fontSize = 14.sp,
                    fontFamily = FontFamily(Font(R.font.inter)),
                    fontWeight = FontWeight(600),
                    color = black
                )
            )
            Spacer(modifier = modifier.height(8.dp))
            OutlinedTextField(
                modifier = modifier.fillMaxWidth(),
                value = phone.value,
                onValueChange = {
                    phone.value = it
                },
                shape = RoundedCornerShape(5.dp),
                textStyle = TextStyle(
                    fontSize = 16.sp,
                    fontFamily = FontFamily(Font(R.font.inter)),
                    fontWeight = FontWeight(600),
                    color = black
                ),
                placeholder = {
                    Text(
                        text = stringResource(R.string.Enter_your_phone),
                        style = TextStyle(
                            fontSize = 16.sp,
                            fontFamily = FontFamily(Font(R.font.inter)),
                            fontWeight = FontWeight(400),
                            color = greyText
                        )
                    )
                },
                colors = OutlinedTextFieldDefaults.colors(
                    focusedContainerColor = background,
                    unfocusedBorderColor = grey,
                    focusedBorderColor = green,
                    cursorColor = black
                ),
                maxLines = 1,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Phone
                ),
            )
        }
    }
}