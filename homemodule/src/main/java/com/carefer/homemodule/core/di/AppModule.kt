package com.carefer.homemodule.core.di

import com.carefer.basemodule.data.remote.coroutines.CoroutinesProvider
import com.carefer.basemodule.data.remote.coroutines.CoroutinesProviderImpl
import com.carefer.basemodule.data.remote.coroutines.dispatchers.BaseCoroutineDispatcher
import com.carefer.basemodule.data.remote.coroutines.dispatchers.RuntimeDispatcher
import com.carefer.homemodule.core.data.remote.FixtureRemoteDataSource
import com.carefer.homemodule.core.data.remote.FixtureRemoteDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideIoDispatcher() = Dispatchers.IO

    @Singleton
    @Provides
    fun bindRunTimeDispatcher(coroutinesRuntimeDispatcher: RuntimeDispatcher): BaseCoroutineDispatcher =
        coroutinesRuntimeDispatcher

    @Singleton
    @Provides
    fun bindCoroutines(coroutinesProvider: CoroutinesProviderImpl): CoroutinesProvider =
        coroutinesProvider
}