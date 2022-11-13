package com.carefer.homemodule.core.domain.matchesData

import com.carefer.basemodule.data.models.fixturemodels.MatchesItem
import com.carefer.basemodule.data.remote.coroutines.Result

interface FixtureUseCase {
    fun getMatches(callback: (Result) -> Unit)
    fun getFavouriteMatchesFromDB(callback: (Result) -> Unit)
    fun addFavMatches(matchesItem: MatchesItem,callback: (Result) -> Unit)
    fun removeMatchFromDataBase( matchesItem: MatchesItem,callback: (Result) -> Unit)

}