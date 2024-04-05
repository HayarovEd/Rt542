package com.smartloanadvisornad.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import com.smartloanadvisornad.ui.state.MainEvent
import com.smartloanadvisornad.ui.state.MainViewModel
import com.smartloanadvisornad.ui.state.StatusApplication

@Composable
fun BaseScene(
    viewModel: MainViewModel
) {
    val state = viewModel.state.collectAsState()
    val event = viewModel::onEvent

    when (val result = state.value.statusApplication) {
        StatusApplication.AmountState -> {
            AmountScreen(
                sharedAmount = state.value.sharedAmount,
                onEvent = event)
        }

        StatusApplication.EmailState -> {
            EmailScreen(
                sharedEmail = state.value.sharedEmail,
                onEvent = event
            )
        }
        StatusApplication.Main -> {
            MainScreen(
                lastApplicationState = state.value.lastState,
                cbrData = state.value.cbrData,
                onEvent = event
            )
        }
        StatusApplication.NameState -> {
            NameScreen(
                sharedName = state.value.sharedName,
                onEvent = event)
        }
        StatusApplication.PeriodState -> {
            PeriodScreen(
                sharedPeriod = state.value.sharedPeriod,
                onEvent = event)
        }

        StatusApplication.PhoneState -> {
            PhoneScreen(
                sharedPhone = state.value.sharedPhone,
                onEvent = event
            )
        }
        StatusApplication.QuickCash -> {
            GetQuickCashScreen2 (
                onEvent = event
            )
        }
        StatusApplication.Reconnect -> {
            NoInternetScreen(
                onClick = {
                    event(MainEvent.Reconnect)
                }
            )
        }
        StatusApplication.ReconnectFirstLoad -> {
            NoInternetScreen(
                onClick = {
                    event(MainEvent.ReconnectFirstLoad)
                }
            )
        }
        StatusApplication.Success -> {
            LoansScreen(
                loans = state.value.dbData?.loans?: emptyList(),
                onEvent = event)
        }
        is StatusApplication.TermState -> {
            TermsScreen(
                terms = state.value.dbData?.appConfig?.privacyPolicyHtml ?: "",
                onClick = result.onClick
            )
        }
        StatusApplication.Transparent -> {
            GetQuickCashScreen3 (
                onEvent = event
            )
        }

        StatusApplication.WaitingState -> {
            WaitingScreen(
                onEvent = event
            )
        }

        is StatusApplication.Web -> {
            WebViewScreen(
                url = result.url,
                offerName = result.offerName,
                onEvent = event
            )
        }
        StatusApplication.Welcome -> {
            GetQuickCashScreen (
                onEvent = event
            )
        }
    }
}