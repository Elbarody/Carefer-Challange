package com.carefer.homemodule.core.di

import com.carefer.homemodule.core.domain.matchesData.FixtureUseCase
import com.carefer.homemodule.core.domain.matchesData.FixtureUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped


@Module
@InstallIn(ViewModelComponent::class)
object UseCaseModule {

    @Provides
    @ViewModelScoped
    fun bindFixtureTransactionsUseCase(fixtureTransactionsUseCase: FixtureUseCaseImpl): FixtureUseCase =
        fixtureTransactionsUseCase

}