package com.carefer.homemodule.core.data.remote


import com.carefer.basemodule.data.models.fixturemodels.FixtureResponse
import retrofit2.Response

interface FixtureRemoteDataSource {

    suspend fun getMatches(): Response<FixtureResponse>
}