package com.carefer.homemodule.core.data.remote

import com.carefer.basemodule.data.models.fixturemodels.FixtureResponse
import retrofit2.http.GET


interface FixtureApiInterface {

    @GET("competitions/2021/matches")
    suspend fun getMatches() : FixtureResponse
}