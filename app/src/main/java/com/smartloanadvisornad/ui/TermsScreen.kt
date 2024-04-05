package com.smartloanadvisornad.ui

import android.widget.TextView
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
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
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.text.HtmlCompat
import com.smartloanadvisornad.R
import com.smartloanadvisornad.ui.theme.background
import com.smartloanadvisornad.ui.theme.black
import com.smartloanadvisornad.ui.theme.grey
import com.smartloanadvisornad.ui.theme.textColor

@Composable
fun TermsScreen(
    modifier: Modifier = Modifier,
    terms: String,
    onClick: () -> Unit
) {
    BackHandler {
        onClick()
    }
    Scaffold(
       topBar = {
           Row(
               modifier = modifier
                   .fillMaxWidth()
                   .background(color = background)
                   .padding(horizontal = 24.dp, vertical = 15.dp),
               verticalAlignment = Alignment.CenterVertically
           ) {
               IconButton(
                   modifier = modifier
                       .clip(shape = CircleShape)
                       .border(width = 1.dp, color = grey, shape = CircleShape),
                   onClick = onClick) {
                   Icon(
                       imageVector = Icons.Filled.ArrowBackIosNew,
                       contentDescription = "",
                       tint = textColor
                   )
               }
               Spacer(modifier = modifier.width(5.dp))
               Text(
                   text = stringResource(R.string.policy),
                   style = TextStyle(
                       fontSize = 18.sp,
                       fontFamily = FontFamily(Font(R.font.inter)),
                       fontWeight = FontWeight(600),
                       color = black
                   )
               )
           }
       }
    ) {
        Column(
            modifier = modifier
                .padding(it)
                .fillMaxSize()
                .background(color = background)
                .padding(horizontal = 24.dp)
                .verticalScroll(rememberScrollState()),
        ) {
            AndroidView(
                modifier = modifier
                    .fillMaxWidth(),
                factory = { context -> TextView(context) },
                update = {content->
                    content.text = HtmlCompat.fromHtml(
                        terms,
                        HtmlCompat.FROM_HTML_MODE_COMPACT
                    )
                }
            )
        }
    }
}