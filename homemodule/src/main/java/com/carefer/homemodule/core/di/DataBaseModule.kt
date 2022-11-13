package com.carefer.homemodule.core.di

import android.content.Context
import com.carefer.basemodule.data.local.MatchesDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import androidx.room.Room

@Module
@InstallIn(SingletonComponent::class)
object DataBaseModule {

    @Singleton
    @Provides
    fun provideDatabaseBuilder(
        @ApplicationContext app: Context
    ) = Room.databaseBuilder(
        app,
        MatchesDatabase::class.java,
        "matches.db"
    ).build()

    @Singleton
    @Provides
    fun bindMatchesDao(db: MatchesDatabase) = db.matchesDao()
}