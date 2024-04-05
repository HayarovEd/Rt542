package com.smartloanadvisornad.data.repository

import android.util.Log
import com.smartloanadvisornad.data.mapper.mapToBaseData
import com.smartloanadvisornad.data.mapper.mapToCberData
import com.smartloanadvisornad.data.remote.ApiCbr
import com.smartloanadvisornad.data.remote.ApiServer
import com.smartloanadvisornad.data.remote.ApiUser
import com.smartloanadvisornad.data.remote.dto.UserBody
import com.smartloanadvisornad.domain.model.BaseData
import com.smartloanadvisornad.domain.model.CbrData
import com.smartloanadvisornad.domain.repository.RepositoryServer
import com.smartloanadvisornad.domain.utils.Resource
import com.smartloanadvisornad.domain.utils.Resource.Error
import com.smartloanadvisornad.domain.utils.Resource.Success
import com.smartloanadvisornad.domain.utils.USER_ID
import javax.inject.Inject

class RepositoryServerImpl @Inject constructor(
    private val apiServer: ApiServer,
    private val apiUser: ApiUser,
    private val apiCbr: ApiCbr,
) : RepositoryServer {
    override suspend fun getDataDb(): Resource<BaseData> {
        return try {
            val folder = apiServer.getDataDb()
            Log.d("DATADB", "dATA DB:${folder.loanDtos.first().id}")
            Success(
                data = folder.mapToBaseData()
            )
        } catch (e: Exception) {
            e.printStackTrace()
            Error(e.message ?: "An unknown error")
        }
    }

    override suspend fun sendUserData(
        fullName: String,
        phone: String,
        email:String
    ) {
        try {
            val parts = fullName.split(" ")
            val name = parts[0]
            val surname = if (parts.size==1 ) parts[0] else parts[1]
            val result = apiUser.postUser(
                UserBody(
                    id = USER_ID,
                    name = name,
                    surName = surname,
                    phone = phone,
                    email = email
                )
            )
            Log.d("rtest send", "result $result")
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override suspend fun getCurrency(): Resource<CbrData> {
        return try {
            val cbrResult = apiCbr.getCbrData()
            Success(
                data = cbrResult.mapToCberData()
            )
        } catch (e: Exception) {
            e.printStackTrace()
            Error(e.message ?: "An unknown error")
        }
    }
}