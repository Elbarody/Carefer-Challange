package com.carefer.homemodule.core.data.repo

import com.carefer.basemodule.data.models.fixturemodels.FixtureResponse
import com.carefer.basemodule.data.models.fixturemodels.MatchesItem
import com.carefer.homemodule.core.data.localdata.LocalDataSource
import com.carefer.homemodule.core.data.remote.FixtureRemoteDataSource
import javax.inject.Inject

class FixtureRepositoryImpl @Inject constructor(
    private val remoteDataSource: FixtureRemoteDataSource,
    private val localDataSource: LocalDataSource
) : FixtureRepository {

    override suspend fun getMatches(): FixtureResponse =
        remoteDataSource.getMatches()

    override suspend fun getFavouriteMatchesFromDB(): List<MatchesItem> =
        localDataSource.getFavouriteMatchesFromDB()


    override suspend fun addFavouriteMatchToDataBase(matchItem: MatchesItem) {
        localDataSource.addFavouriteMatchToDataBase(matchItem)
    }

    override suspend fun removeMatchFromDataBase(matchesItem: MatchesItem) {
        localDataSource.removeMatchFromDataBase(matchesItem)
    }


}
