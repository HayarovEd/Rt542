package com.smartloanadvisornad.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.smartloanadvisornad.R
import com.smartloanadvisornad.ui.theme.background
import com.smartloanadvisornad.ui.theme.black
import com.smartloanadvisornad.ui.theme.greyText
import com.smartloanadvisornad.ui.theme.textColor

@Composable
fun NoInternetScreen(
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(color = background)
            .padding(24.dp)
    ) {
        Column(
            modifier = modifier.align(alignment = Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                modifier = modifier.size(130.dp),
                painter = painterResource(
                    id = R.drawable.not_connect
                ),
                contentDescription = ""
            )
            Spacer(modifier = modifier.height(18.dp))
            Text(
                text = stringResource(id = R.string.not_connect),
                fontSize = 18.sp,
                fontWeight = FontWeight(600),
                color = black,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = modifier.height(3.dp))
            Text(
                text = stringResource(id = R.string.try_internet),
                fontSize = 14.sp,
                fontWeight = FontWeight(400),
                color = greyText,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = modifier.height(25.dp))
            Button(
                modifier = modifier.fillMaxWidth(),
                shape = RoundedCornerShape(15.dp),
                contentPadding = PaddingValues(
                    vertical = 12.dp
                ),
                colors = ButtonDefaults.buttonColors(
                    containerColor = textColor,
                    contentColor = background,
                ),
                onClick = onClick
            ) {
                Text(
                    text = stringResource(id = R.string.reconnect),
                    style = TextStyle(
                        fontSize = 14.sp,
                        fontWeight = FontWeight(500),
                    )
                )
            }
        }
    }
}
