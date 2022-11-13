package com.carefer.homemodule.core.di

import com.carefer.homemodule.core.data.repo.FixtureRepository
import com.carefer.homemodule.core.data.repo.FixtureRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped


@Module
@InstallIn(ViewModelComponent::class)
object RepositoryModule {

    @Provides
    @ViewModelScoped
    fun bindFixtureRepository(fixtureRepository: FixtureRepositoryImpl): FixtureRepository =
        fixtureRepository

}