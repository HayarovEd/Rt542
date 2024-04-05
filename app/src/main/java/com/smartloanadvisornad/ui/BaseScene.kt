package com.smartloanadvisornad.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import com.smartloanadvisornad.ui.state.MainViewModel
import com.smartloanadvisornad.ui.state.StatusApplication

@Composable
fun BaseScene(
    viewModel: MainViewModel
) {
    val state = viewModel.state.collectAsState()
    val event = viewModel::onEvent

    when (val result = state.value.statusApplication) {
        StatusApplication.AddressState -> TODO()
        StatusApplication.AmountState -> TODO()
        StatusApplication.EmailState -> TODO()
        StatusApplication.Main -> {
            MainScreen(
                lastApplicationState = state.value.lastState,
                cbrData = state.value.cbrData,
                onEvent = event
            )
        }
        StatusApplication.NameState -> TODO()
        StatusApplication.PeriodState -> TODO()
        StatusApplication.PhoneState -> TODO()
        StatusApplication.QuickCash -> {
            GetQuickCashScreen2 (
                onEvent = event
            )
        }
        StatusApplication.Reconnect -> TODO()
        StatusApplication.ReconnectFirstLoad -> TODO()
        StatusApplication.Success -> TODO()
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
        StatusApplication.WaitingState -> TODO()
        is StatusApplication.Web -> TODO()
        StatusApplication.Welcome -> {
            GetQuickCashScreen (
                onEvent = event
            )
        }
    }
}