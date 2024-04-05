package com.smartloanadvisornad.ui.state


sealed class MainEvent {
    data object ReconnectFirstLoad: MainEvent()
    class SendUserData(val statusApplication: StatusApplication): MainEvent()
    class OnSetName(val name:String): MainEvent()
    class OnSetEmail(val email:String): MainEvent()
    class OnSetPhone(val phone:String): MainEvent()
    class OnSetAmount(val amount:Int): MainEvent()
    class OnSetPeriod(val period:Int): MainEvent()
    data object SetFirstLoad: MainEvent()
    data object SaveLocalData: MainEvent()
    data object Reconnect : MainEvent()
    class OnChangeStatusApplication(val statusApplication: StatusApplication): MainEvent()
    class UpdateLastStatusApplication(val statusApplication: StatusApplication): MainEvent()
    class OnGoToWeb(
        val urlOffer: String,
        val nameOffer: String
        ): MainEvent()
}
