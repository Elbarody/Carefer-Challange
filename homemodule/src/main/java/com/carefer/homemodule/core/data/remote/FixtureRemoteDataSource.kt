package com.carefer.homemodule.core.data.remote


import com.carefer.basemodule.data.models.fixturemodels.FixtureResponse

interface FixtureRemoteDataSource {

    suspend fun getMatches(): FixtureResponse
}