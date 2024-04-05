package com.smartloanadvisornad.data.mapper

import com.smartloanadvisornad.data.remote.dto.BaseDto
import com.smartloanadvisornad.data.remote.dto.CbrDto
import com.smartloanadvisornad.domain.model.AppConfig
import com.smartloanadvisornad.domain.model.BaseData
import com.smartloanadvisornad.domain.model.CNY
import com.smartloanadvisornad.domain.model.CbrData
import com.smartloanadvisornad.domain.model.Currency
import com.smartloanadvisornad.domain.model.EUR
import com.smartloanadvisornad.domain.model.Loan
import com.smartloanadvisornad.domain.model.USD

fun BaseDto.mapToBaseData(): BaseData {
    return BaseData(
        appConfig = AppConfig(
            privacyPolicyHtml = this.appConfigDto.privacyPolicyHtml,
            showCalculatorItem = this.appConfigDto.showCalculatorItem,
            showChat = this.appConfigDto.showChat,
            showNewsItem = this.appConfigDto.showNewsItem,
            showPhoneAuthentication = this.appConfigDto.showPhoneAuthentication,
            userTermHtml = this.appConfigDto.userTermHtml
        ),
        loans = this.loanDtos.map {
            Loan(
                image = it.screen,
                name = it.name,
                url = it.order
            )
        }
    )
}

fun CbrDto.mapToCberData(): CbrData {
    return CbrData(
        date = formatCbrDate(this.date),
        previousDate = formatCbrDate(this.previousDate),
        currency = Currency(
            cNY = CNY(
                name = this.currencyDto.cNY.name,
                charCode = this.currencyDto.cNY.charCode,
                nominal = this.currencyDto.cNY.nominal,
                value = this.currencyDto.cNY.value,
                previous = this.currencyDto.cNY.previous
            ),
            eUR = EUR(
                name = this.currencyDto.eUR.name,
                charCode = this.currencyDto.eUR.charCode,
                nominal = this.currencyDto.eUR.nominal,
                value = this.currencyDto.eUR.value,
                previous = this.currencyDto.eUR.previous
            ),
            uSD = USD(
                name = this.currencyDto.uSD.name,
                charCode = this.currencyDto.uSD.charCode,
                nominal = this.currencyDto.uSD.nominal,
                value = this.currencyDto.uSD.value,
                previous = this.currencyDto.uSD.previous
            )
        )
    )
}


private fun formatCbrDate(current: String): String {
    val month = current.substring(5, 7)
    val day = current.substring(8, 10)
    return "$day.$month"
}