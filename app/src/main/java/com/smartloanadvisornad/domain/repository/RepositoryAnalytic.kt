package loans.online.loan.app.domain.repository

import com.smartloanadvisornad.data.remote.dto.Sub1
import com.smartloanadvisornad.data.remote.dto.Sub2
import com.smartloanadvisornad.data.remote.dto.Sub3
import com.smartloanadvisornad.data.remote.dto.Sub5
import com.smartloanadvisornad.domain.utils.Resource


interface RepositoryAnalytic {
    suspend fun getSub1 (
        applicationToken: String,
        userId: String,
        myTrackerId: String,
        appMetricaId: String,
        appsflyer: String,
        firebaseToken: String
    ): Resource<Sub1>

    suspend fun getSub2 (
        applicationToken: String,
        userId: String,
        appsflyer: String,
        myTracker: String,
    ): Resource<Sub2>

    suspend fun getSub3 (
        applicationToken: String,
        userId: String,
        myTrackerId: String,
        appMetricaId: String,
        appsflyer: String,
        firebaseToken: String,
    ): Resource<Sub3>

    suspend fun getSub5 (
        applicationToken: String,
        userId: String,
        gaid:String
    ): Resource<Sub5>
}