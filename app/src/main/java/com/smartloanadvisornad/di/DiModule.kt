package com.smartloanadvisornad.di

import com.smartloanadvisornad.data.repository.RepositoryAnalyticImpl
import com.smartloanadvisornad.data.repository.RepositoryServerImpl
import com.smartloanadvisornad.data.repository.ServiceImpl
import com.smartloanadvisornad.data.repository.SharedKeeperImpl
import loans.online.loan.app.domain.repository.RepositoryAnalytic
import com.smartloanadvisornad.domain.repository.RepositoryServer
import com.smartloanadvisornad.domain.repository.Service
import com.smartloanadvisornad.domain.repository.SharedKeeper
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
abstract class DiModule {

    @Binds
    @Singleton
    abstract fun bindService(service: ServiceImpl): Service

    @Binds
    @Singleton
    abstract fun bindKeeper(sharedKeeper: SharedKeeperImpl): SharedKeeper

    @Binds
    @Singleton
    abstract fun bindRepositoryAnalytic(repository: RepositoryAnalyticImpl): RepositoryAnalytic

    @Binds
    @Singleton
    abstract fun bindRepositoryServer(repository: RepositoryServerImpl): RepositoryServer

}