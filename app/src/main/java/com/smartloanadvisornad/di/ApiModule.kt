package com.smartloanadvisornad.di

import com.smartloanadvisornad.data.remote.ApiAnalytic
import com.smartloanadvisornad.data.remote.ApiCbr
import com.smartloanadvisornad.data.remote.ApiServer
import com.smartloanadvisornad.data.remote.ApiUser
import com.smartloanadvisornad.domain.utils.BASE_CBR_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import com.smartloanadvisornad.domain.utils.URL_ANALYTICS
import com.smartloanadvisornad.domain.utils.URL_POST_SERVER
import com.smartloanadvisornad.domain.utils.URL_SERVER
import javax.inject.Singleton
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {
    @Provides
    @Singleton
    fun provideApiAnalytic(): ApiAnalytic {
        return Retrofit.Builder()
            .baseUrl(URL_ANALYTICS)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiAnalytic::class.java)
    }

    @Provides
    @Singleton
    fun provideApiServer(): ApiServer {
        return Retrofit.Builder()
            .baseUrl(URL_SERVER)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiServer::class.java)
    }

    @Provides
    @Singleton
    fun provideApiUser(): ApiUser {
        return Retrofit.Builder()
            .baseUrl(URL_POST_SERVER)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiUser::class.java)
    }

    @Provides
    @Singleton
    fun provideApiCbr(): ApiCbr {
        return Retrofit.Builder()
            .baseUrl(BASE_CBR_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiCbr::class.java)
    }
}