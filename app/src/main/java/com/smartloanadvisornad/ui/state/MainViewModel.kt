package com.smartloanadvisornad.ui.state


import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import loans.online.loan.app.domain.repository.RepositoryAnalytic
import com.smartloanadvisornad.ui.state.MainEvent.OnAddressName
import com.smartloanadvisornad.ui.state.MainEvent.OnChangeStatusApplication
import com.smartloanadvisornad.ui.state.MainEvent.OnGoToWeb
import com.smartloanadvisornad.ui.state.MainEvent.OnSetAmount
import com.smartloanadvisornad.ui.state.MainEvent.OnSetEmail
import com.smartloanadvisornad.ui.state.MainEvent.OnSetName
import com.smartloanadvisornad.ui.state.MainEvent.OnSetPhone
import com.smartloanadvisornad.ui.state.MainEvent.Reconnect
import com.smartloanadvisornad.ui.state.MainEvent.ReconnectFirstLoad
import com.smartloanadvisornad.ui.state.MainEvent.SaveLocalData
import com.smartloanadvisornad.ui.state.MainEvent.SendUserData
import com.smartloanadvisornad.ui.state.MainEvent.SetFirstLoad
import com.smartloanadvisornad.ui.state.MainEvent.UpdateLastStatusApplication
import com.smartloanadvisornad.ui.state.StatusApplication.AmountState
import com.smartloanadvisornad.ui.state.StatusApplication.Success
import com.smartloanadvisornad.ui.state.StatusApplication.Web
import com.my.tracker.MyTracker
import com.smartloanadvisornad.domain.repository.RepositoryServer
import com.smartloanadvisornad.domain.repository.Service
import com.smartloanadvisornad.domain.repository.SharedKeeper
import com.smartloanadvisornad.domain.utils.APP_METRICA
import com.smartloanadvisornad.domain.utils.APY_KEY
import com.smartloanadvisornad.domain.utils.BACKEND_UNAVAILABLE
import com.smartloanadvisornad.domain.utils.DEMO_EMAIL
import com.smartloanadvisornad.domain.utils.EXTERNAL_LINK
import com.smartloanadvisornad.domain.utils.ITEM_ID
import com.smartloanadvisornad.domain.utils.LOANS
import com.smartloanadvisornad.domain.utils.OFFER_WALL
import com.smartloanadvisornad.domain.utils.REQUEST_DB
import com.smartloanadvisornad.domain.utils.Resource
import com.smartloanadvisornad.domain.utils.URL
import com.yandex.metrica.YandexMetrica
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel
class MainViewModel @Inject constructor(
    private val service: Service,
    private val sharedKeeper: SharedKeeper,
    private val repositoryAnalytic: RepositoryAnalytic,
    private val repositoryServer: RepositoryServer,
) : ViewModel() {
    private var _state = MutableStateFlow(MainState())
    val state = _state.asStateFlow()
    private val _myTracker = MutableStateFlow("")
    private val _appsFlayer = MutableStateFlow("")
    private val _link = MutableStateFlow("")
    private val _yandexMetrikaDeviceId = MutableStateFlow("")
    private val _appsFlayerInstanceId = MutableStateFlow("")

    init {
       viewModelScope.launch {
           loadData()
           when (val cbrData = repositoryServer.getCurrency()) {
               is Resource.Error -> {

               }
               is Resource.Success -> {
                   _state.value.copy(
                      cbrData = cbrData.data
                   )
                       .updateStateUI()
               }
           }
       }
    }

    fun loadAFDeeplink(deeplink: String) {
        Log.d("ASDFGH", "appsFlayer deeplink -  $deeplink")
        _appsFlayer.value = deeplink
        Log.d("ASDFGH", "appsFlayer start -  ${_appsFlayer.value}")

    }

    fun loadMTDeeplink(deeplink: String) {
        _myTracker.value = deeplink
    }

    fun loadLink(link: String) {
        _link.value = link
    }

    private suspend fun loadData() {
        if (checkInternet()) {
            Log.d("test view vodel", "first ${sharedKeeper.getFirstRun()}")
            if (sharedKeeper.getFirstRun()) {
                _state.value.copy(
                    statusApplication = StatusApplication.Welcome
                )
                    .updateStateUI()
            } else {
                viewModelScope.launch(Dispatchers.Main) {
                    _state.value.copy(
                        statusApplication = Success,
                        sharedPhone = sharedKeeper.getPhone() ?: "",
                        sharedAddress = sharedKeeper.getAddress() ?: "",
                        sharedEmail = sharedKeeper.getEmail() ?: "",
                        sharedAmount = sharedKeeper.getAmount() ?: ""
                    )
                        .updateStateUI()
                }
            }
            val sharedYandexMetrica = sharedKeeper.getYandexMetricaDeviceId()
            if (sharedYandexMetrica.isNullOrBlank()) {
                service.getYandexMetricaDeviceId {
                    _yandexMetrikaDeviceId.value = it ?: ""
                    viewModelScope.launch {
                        sharedKeeper.setYandexMetricaDeviceId(it ?: "")
                    }
                }
            } else {
                _yandexMetrikaDeviceId.value = sharedYandexMetrica
            }
            val instanceIdMyTracker =
                if (sharedKeeper.getMyTrackerInstanceId().isNullOrBlank()) {
                    val instance = service.instanceIdMyTracker
                    sharedKeeper.setMyTrackerInstanceId(instance)
                    instance
                } else {
                    sharedKeeper.getMyTrackerInstanceId()
                }
            val sharedFireBaseToken = sharedKeeper.getFireBaseToken()
            Log.d("GHJIOP", "result fb token $sharedFireBaseToken")
            if (sharedFireBaseToken.isNullOrBlank()) {
                viewModelScope.launch(Dispatchers.IO)
                {
                    service.getHmsToken().let { token ->
                        Log.d("GHJIOP", "result fb token? $token")
                        _state.value.copy(
                            fireBaseToken = token
                        )
                            .updateStateUI()
                        sharedKeeper.setFireBaseToken(token ?: "")
                    }
                    getSub3()
                }
            } else {
                _state.value.copy(
                    fireBaseToken = sharedFireBaseToken
                )
                    .updateStateUI()
                getSub3()
            }

            _appsFlayerInstanceId.value = sharedKeeper.getAppsFlyerInstanceId() ?: ""
            _state.value.copy(
                instanceIdMyTracker = instanceIdMyTracker
            )
                .updateStateUI()
                viewModelScope.launch(Dispatchers.IO) {
                    val gaid = service.getOAID()
                    _state.value.copy(
                        gaid = gaid,
                    )
                        .updateStateUI()
                    delay(2000)
                    getSub1()
                }

            if (sharedKeeper.getSub2().isNullOrBlank()) {
                getFirstSub2()
            }

            getSub5()
            loadDbData()
        } else {
            _state.value.copy(
                statusApplication = StatusApplication.ReconnectFirstLoad
            )
                .updateStateUI()
        }
    }

    private fun MainState.updateStateUI() {
        _state.update {
            this
        }
    }

    fun onEvent(mainEvent: MainEvent) {
        when (mainEvent) {
            ReconnectFirstLoad -> {
                if (service.checkedInternetConnection()) {
                    viewModelScope.launch {
                        loadData()
                    }

                } else {
                    _state.value.copy(
                        statusApplication = StatusApplication.ReconnectFirstLoad,
                    )
                        .updateStateUI()
                }
            }
            is OnChangeStatusApplication -> {
                _state.value.copy(
                    statusApplication = mainEvent.statusApplication,
                )
                    .updateStateUI()
            }

            is OnGoToWeb -> {
                /*_state.value.copy(
                    statusApplication = Splash,
                )
                    .updateStateUI()*/
                viewModelScope.launch {
                    delay(2000)
                    val completeUrl =
                        "${mainEvent.urlOffer}&aff_sub1=${_state.value.affsub1Unswer}&aff_sub2=${_state.value.affsub2Unswer}&aff_sub3=${_state.value.affsub3Unswer}&aff_sub5=${_state.value.affsub5Unswer}"
                    Log.d("ASDFGH", "url $completeUrl")
                    if (service.checkedInternetConnection()) {
                        _state.value.copy(
                            statusApplication = Web(

                                offerName = mainEvent.nameOffer,
                                url = completeUrl
                            ),
                        )
                            .updateStateUI()
                        sendGoToOffer(
                            url = completeUrl,
                            parameter = OFFER_WALL
                        )
                        sendFromListOffers(
                            url = completeUrl
                        )
                    } else {
                        _state.value.copy(
                            lastState = Web(
                                offerName = mainEvent.nameOffer,
                                url = completeUrl
                            ),
                            statusApplication = StatusApplication.Reconnect,
                        )
                            .updateStateUI()
                    }
                }
            }

            is Reconnect -> {
                if (service.checkedInternetConnection()) {
                    _state.value.copy(
                        statusApplication = _state.value.lastState,
                    )
                        .updateStateUI()
                }
            }

            is UpdateLastStatusApplication -> {
                _state.value.copy(
                    lastState = mainEvent.statusApplication,
                )
                    .updateStateUI()
            }

            SetFirstLoad -> {
                viewModelScope.launch {
                    sharedKeeper.setFirstRun(false)
                }
            }

            is SendUserData -> {
                if (service.checkedInternetConnection()) {
                    if (_state.value.sharedEmail == DEMO_EMAIL) {
                        _state.value.copy(
                            statusApplication = AmountState,
                        )
                            .updateStateUI()
                    } else {
                        _state.value.copy(
                            statusApplication = Success,
                        )
                            .updateStateUI()
                    }
                    viewModelScope.launch(Dispatchers.IO) {

                        repositoryServer.sendUserData(
                            fullName = _state.value.sharedName,
                            email = _state.value.sharedEmail,
                            phone = _state.value.sharedPhone
                        )
                    }
                } else {
                    _state.value.copy(
                        lastState = mainEvent.statusApplication,
                        statusApplication = StatusApplication.Reconnect,
                    )
                        .updateStateUI()
                }
            }

            is OnAddressName -> {
                _state.value.copy(
                    sharedAddress = mainEvent.address,
                )
                    .updateStateUI()
            }

            is OnSetEmail -> {
                _state.value.copy(
                    sharedEmail = mainEvent.email,
                )
                    .updateStateUI()
            }

            is OnSetName -> {
                _state.value.copy(
                    sharedName = mainEvent.name,
                )
                    .updateStateUI()
            }

            is OnSetPhone -> {
                _state.value.copy(
                    sharedPhone = mainEvent.phone,
                )
                    .updateStateUI()
            }

            is OnSetAmount -> {
                _state.value.copy(
                    sharedAmount = mainEvent.amount,
                )
                    .updateStateUI()
            }

            SaveLocalData -> {
                viewModelScope.launch {
                    sharedKeeper.setAddress(_state.value.sharedAddress)
                    sharedKeeper.setAmount(_state.value.sharedAmount)
                    sharedKeeper.setEmail(_state.value.sharedEmail)
                    sharedKeeper.setPhone(_state.value.sharedPhone)
                }
            }
        }
    }


    private fun getSub1() {
        viewModelScope.launch {
            delay(2000)
            Log.d("ASDFGH", "_yandexMetrikaDeviceId sub1 ${_yandexMetrikaDeviceId.value}")
            Log.d("ASDFGH", "_appsFlayerInstanceId sub1 ${_appsFlayerInstanceId.value}")
            Log.d("ASDFGH", "gaid ${_state.value.gaid}")
            Log.d("ASDFGH", "instanceIdMyTracker ${_state.value.instanceIdMyTracker}")
            when (val result = repositoryAnalytic.getSub1(
                applicationToken = APY_KEY,
                userId = _state.value.gaid ?: "",
                appMetricaId = _yandexMetrikaDeviceId.value,
                appsflyer = _appsFlayerInstanceId.value,
                firebaseToken = "NA",
                myTrackerId = _state.value.instanceIdMyTracker ?: ""
            )) {
                is Resource.Error -> {
                    _state.value.copy(
                        message = result.message ?: "unknown error"
                    )
                        .updateStateUI()
                }

                is Resource.Success -> {
                    Log.d("FGHJJ", "result sub1 ${result.data}")
                    _state.value.copy(
                        affsub1Unswer = result.data?.affsub1 ?: ""
                    )
                        .updateStateUI()
                }
            }
        }
    }

    private fun getSub3() {
        viewModelScope.launch {
            delay(2000)
            when (val result = repositoryAnalytic.getSub3(
                applicationToken = APY_KEY,
                userId = _state.value.gaid ?: "",
                appMetricaId = APP_METRICA,
                appsflyer = _appsFlayerInstanceId.value,
                firebaseToken = _state.value.fireBaseToken ?: "",
                myTrackerId = _state.value.instanceIdMyTracker ?: ""
            )) {
                is Resource.Error -> {
                    _state.value.copy(
                        message = result.message ?: "unknown error"
                    )
                        .updateStateUI()
                }

                is Resource.Success -> {
                    _state.value.copy(
                        affsub3Unswer = result.data?.affsub3 ?: ""
                    )
                        .updateStateUI()
                }
            }
        }
    }

    private fun getSub5() {
        viewModelScope.launch {
            delay(2000)
            when (val result = repositoryAnalytic.getSub5(
                applicationToken = APY_KEY,
                userId = _state.value.gaid ?: "",
                gaid = _state.value.gaid ?: ""
            )) {
                is Resource.Error -> {
                    _state.value.copy(
                        message = result.message ?: "unknown error"
                    )
                        .updateStateUI()
                }

                is Resource.Success -> {
                    _state.value.copy(
                        affsub5Unswer = result.data?.affsub5 ?: ""
                    )
                        .updateStateUI()
                }
            }
        }
    }

    private fun getFirstSub2() {
        viewModelScope.launch {
            delay(1000)
            when (val result = repositoryAnalytic.getSub2(
                applicationToken = APY_KEY,
                userId = _state.value.gaid ?: "",
                appsflyer = "",
                myTracker = ""
            )) {
                is Resource.Error -> {
                    _state.value.copy(
                        message = result.message ?: "unknown error"
                    )
                        .updateStateUI()
                }

                is Resource.Success -> {
                    val sub2 = result.data?.affsub2
                    Log.d("ASDFGH", "affsub2UnswerEmpty $sub2")
                    _state.value.copy(
                        affsub2UnswerEmpty = sub2 ?: ""
                    )
                        .updateStateUI()
                }
            }
        }
    }

    private fun getSub2(currentMyTracker: String, currentAppsFlyer: String) {
        viewModelScope.launch {
            if (currentMyTracker.isNotBlank()) {
                when (val result = repositoryAnalytic.getSub2(
                    applicationToken = APY_KEY,
                    userId = _state.value.gaid ?: "",
                    appsflyer = "",
                    myTracker = currentMyTracker
                )) {
                    is Resource.Error -> {
                        _state.value.copy(
                            message = result.message ?: "unknown error"
                        )
                            .updateStateUI()
                    }

                    is Resource.Success -> {
                        Log.d("ASDFGH", "currentMyTracker $currentMyTracker")
                        val affsub2Unswer = result.data?.affsub2 ?: ""
                        Log.d("ASDFGH", "affsub2UnswerMT $affsub2Unswer")
                        _state.value.copy(
                            affsub2UnswerMT = affsub2Unswer
                        )
                            .updateStateUI()
                    }
                }
            }
            if (currentAppsFlyer.isNotBlank()) {
                when (val result = repositoryAnalytic.getSub2(
                    applicationToken = APY_KEY,
                    userId = _state.value.gaid ?: "",
                    appsflyer = currentAppsFlyer,
                    myTracker = ""
                )) {
                    is Resource.Error -> {
                        _state.value.copy(
                            message = result.message ?: "unknown error"
                        )
                            .updateStateUI()
                    }

                    is Resource.Success -> {
                        val affsub2Unswer = result.data?.affsub2 ?: ""
                        Log.d("ASDFGH", "affsub2UnswerAF $affsub2Unswer")
                        _state.value.copy(
                            affsub2UnswerAF = affsub2Unswer
                        )
                            .updateStateUI()
                    }
                }
            }
        }
    }

    private fun loadDbData() {
        viewModelScope.launch {
            delay(4000)
            val currentGaid = _state.value.gaid ?: ""

            Log.d("SDFGH", "oaid ${_state.value.gaid}")
            Log.d("SDFGH", "instanceMyTracker ${_state.value.instanceIdMyTracker}")
            Log.d("SDFGH", "-------------------------")
            val db = repositoryServer.getDataDb()
            YandexMetrica.reportEvent(REQUEST_DB, currentGaid)
            MyTracker.trackEvent(REQUEST_DB, mapOf(REQUEST_DB to currentGaid))
            service.sendAppsFlyerEvent(
                key = REQUEST_DB,
                content = mapOf(REQUEST_DB to currentGaid)
            )
            when (db) {
                is Resource.Error -> {
                    YandexMetrica.reportEvent(BACKEND_UNAVAILABLE, currentGaid)
                    MyTracker.trackEvent(
                        BACKEND_UNAVAILABLE,
                        mapOf(BACKEND_UNAVAILABLE to currentGaid)
                    )
                    service.sendAppsFlyerEvent(
                        key = BACKEND_UNAVAILABLE,
                        content = mapOf(BACKEND_UNAVAILABLE to currentGaid)
                    )
                }

                is Resource.Success -> {
                    if (_link.value.isBlank() || _link.value == " ") {
                        _state.value.copy(
                            dbData = db.data,
                        )
                            .updateStateUI()
                        val sharedSub2 = sharedKeeper.getSub2()
                        if (!sharedSub2.isNullOrBlank()) {
                            _state.value.copy(
                                affsub2Unswer = sharedSub2
                            )
                                .updateStateUI()
                        } else {
                            delay(2000)
                            getSub2(
                                currentAppsFlyer = _appsFlayer.value,
                                currentMyTracker = _myTracker.value
                            )
                            delay(2000)
                            val tempSub2 = if (_state.value.affsub2UnswerMT.isNotBlank()) {
                                sharedKeeper.setSub2(_state.value.affsub2UnswerMT)
                                _state.value.affsub2UnswerMT
                            } else if (state.value.affsub2UnswerAF.isNotBlank()) {
                                sharedKeeper.setSub2(_state.value.affsub2UnswerAF)
                                _state.value.affsub2UnswerAF
                            } else {
                                sharedKeeper.setSub2(_state.value.affsub2UnswerEmpty)
                                _state.value.affsub2UnswerEmpty
                            }
                            _state.value.copy(
                                affsub2Unswer = tempSub2
                            )
                                .updateStateUI()
                        }
                    } else {
                        delay(1000)
                        val statusApplication = Success
                        _state.value.copy(
                            statusApplication = statusApplication,
                            dbData = db.data,
                        )
                            .updateStateUI()
                        delay(1000)
                    }
                }
            }
        }
    }

    private fun sendGoToOffer(url: String, parameter: String) {
        val sendingData = mapOf(
            ITEM_ID to parameter,
            URL to url
        )
        YandexMetrica.reportEvent(EXTERNAL_LINK, sendingData)
        MyTracker.trackEvent(EXTERNAL_LINK)
        service.sendAppsFlyerEvent(
            key = EXTERNAL_LINK,
            content = sendingData
        )
    }

    private fun sendFromListOffers(url: String) {
        val sendingData = mapOf(
            URL to url
        )
        YandexMetrica.reportEvent(LOANS, sendingData)
        MyTracker.trackEvent(LOANS, sendingData)
        service.sendAppsFlyerEvent(
            key = LOANS,
            content = sendingData
        )
    }

    private fun checkInternet(): Boolean {
        return service.checkedInternetConnection()
    }
}