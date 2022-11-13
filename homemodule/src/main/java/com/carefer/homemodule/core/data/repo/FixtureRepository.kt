package com.carefer.homemodule.core.data.repo

import com.carefer.basemodule.data.models.fixturemodels.FixtureResponse
import com.carefer.basemodule.data.models.fixturemodels.MatchesItem
import retrofit2.Response

interface FixtureRepository {
    suspend fun getMatches(): Response<FixtureResponse>
    suspend fun getFavouriteMatchesFromDB(): List<MatchesItem>
    suspend fun addFavouriteMatchToDataBase(matchItem : MatchesItem)
    suspend fun removeMatchFromDataBase( matchesItem: MatchesItem)

}