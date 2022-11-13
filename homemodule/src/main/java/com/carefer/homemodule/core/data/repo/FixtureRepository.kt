package com.carefer.homemodule.core.data.repo

import com.carefer.basemodule.data.models.fixturemodels.FixtureResponse
import com.carefer.basemodule.data.models.fixturemodels.MatchesItem

interface FixtureRepository {
    suspend fun getMatches(): FixtureResponse
    suspend fun getFavouriteMatchesFromDB(): List<MatchesItem>
    suspend fun addFavouriteMatchToDataBase(matchItem : MatchesItem)
    suspend fun removeMatchFromDataBase( matchesItem: MatchesItem)

}