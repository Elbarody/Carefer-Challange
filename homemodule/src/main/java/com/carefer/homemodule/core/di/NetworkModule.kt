package com.carefer.homemodule.core.di

import com.carefer.basemodule.data.helpers.network.RetrofitFactory
import com.carefer.homemodule.core.data.remote.FixtureApiInterface
import com.carefer.homemodule.core.data.remote.FixtureRemoteDataSource
import com.carefer.homemodule.core.data.remote.FixtureRemoteDataSourceImpl

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    fun provideFixtureApi(
        remoteDataSource: RetrofitFactory,
    ): FixtureApiInterface {
        return remoteDataSource.getService()
    }

    @Provides
    fun bindFixtureRemoteDataSource(fixtureRemoteDataSource: FixtureRemoteDataSourceImpl): FixtureRemoteDataSource =
        fixtureRemoteDataSource



}