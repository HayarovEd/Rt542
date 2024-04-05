package com.smartloanadvisornad.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.smartloanadvisornad.R
import com.smartloanadvisornad.domain.model.Loan
import com.smartloanadvisornad.ui.state.MainEvent
import com.smartloanadvisornad.ui.theme.background
import com.smartloanadvisornad.ui.theme.black
import com.smartloanadvisornad.ui.theme.textColor

@Composable
fun LoansScreen(
    modifier: Modifier = Modifier,
    loans: List<Loan>,
    onEvent: (MainEvent) -> Unit,
    ) {
    Scaffold {innerPaddings->
        LazyColumn (
            modifier = modifier
                .padding(innerPaddings)
                .fillMaxSize()
                .background(color = background)
                .padding(25.dp),
            verticalArrangement = Arrangement.spacedBy(15.dp)
        ) {
            items(loans) {loan->
                Card (
                    modifier = modifier
                        .fillMaxWidth(),
                    shape = RoundedCornerShape(14.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = background
                    ),
                    elevation = CardDefaults.cardElevation(
                        defaultElevation = 10.dp
                    )
                ) {
                    Row (
                        modifier = modifier
                            .fillMaxWidth()
                            .padding(24.dp),
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        AsyncImage(
                            modifier = modifier.width(45.dp),
                            model = loan.image, 
                            contentDescription = "",
                            contentScale = ContentScale.FillWidth)
                        Spacer(modifier = modifier.width(15.dp))
                        Text(
                            text = loan.name,
                            style = TextStyle(
                                fontSize = 18.sp,
                                fontFamily = FontFamily(Font(R.font.inter)),
                                fontWeight = FontWeight(500),
                                color = black
                            )
                        )
                        Spacer(modifier = modifier.weight(1f))
                        IconButton(
                            modifier = modifier,
                            onClick = {
                                onEvent(MainEvent.OnGoToWeb(
                                    urlOffer = loan.url,
                                    nameOffer = loan.name
                                ))
                            }
                        ) {
                            Icon(
                                imageVector = ImageVector.vectorResource(id = R.drawable.next),
                                contentDescription = "",
                                tint = textColor
                            )
                        }
                    }
                }
            }
        }
    }
}