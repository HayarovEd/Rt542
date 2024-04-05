package com.smartloanadvisornad.domain.repository

import com.smartloanadvisornad.domain.model.BaseData
import com.smartloanadvisornad.domain.model.CbrData
import com.smartloanadvisornad.domain.utils.Resource


interface RepositoryServer {
    suspend fun getDataDb() : Resource<BaseData>
    suspend fun sendUserData(fullName: String, phone: String, email: String)
    suspend fun getCurrency() : Resource<CbrData>
}