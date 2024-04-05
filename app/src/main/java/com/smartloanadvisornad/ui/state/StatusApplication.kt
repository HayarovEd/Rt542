package com.smartloanadvisornad.ui.state

sealed class StatusApplication {
    //data object Splash: StatusApplication()
    data object Welcome: StatusApplication()
    data object QuickCash: StatusApplication()
    data object Transparent: StatusApplication()
    data object Main: StatusApplication()
    data object NameState: StatusApplication()
    data object EmailState: StatusApplication()
    data object PhoneState: StatusApplication()
    data object AmountState: StatusApplication()
    data object PeriodState: StatusApplication()
    data object WaitingState: StatusApplication()
    class TermState(
        val onClick: () -> Unit,
    ): StatusApplication()


    data object Success: StatusApplication()
    class Web (
        val url: String,
        val offerName: String
    ): StatusApplication()

    data object ReconnectFirstLoad : StatusApplication()
    data object Reconnect : StatusApplication()
}
