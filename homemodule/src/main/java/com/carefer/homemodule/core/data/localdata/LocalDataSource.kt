package com.carefer.homemodule.core.data.localdata

import com.carefer.basemodule.data.local.room.doa.MatchesDoa
import com.carefer.basemodule.data.models.fixturemodels.MatchesItem
import javax.inject.Inject

class LocalDataSource@Inject constructor(
    private val dao: MatchesDoa
){

    suspend fun getFavouriteMatchesFromDB() = dao.getFavouriteMatches()
    suspend fun addFavouriteMatchToDataBase(matchItem : MatchesItem) = dao.insert(matchItem)
    suspend fun removeMatchFromDataBase( matchesItem: MatchesItem) = dao.removeMatchFromDataBase(matchesItem)
}