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
        StatusApplication.Main -> TODO()
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
        is StatusApplication.TermState -> TODO()
        StatusApplication.Transparent -> {
            TODO()
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