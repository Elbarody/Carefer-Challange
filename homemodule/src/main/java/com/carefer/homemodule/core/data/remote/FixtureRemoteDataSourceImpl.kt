package com.carefer.homemodule.core.data.remote


import com.carefer.basemodule.data.models.fixturemodels.FixtureResponse
import javax.inject.Inject

class FixtureRemoteDataSourceImpl
@Inject constructor(private val fixtureApiInterface: FixtureApiInterface) :
    FixtureRemoteDataSource {

    override suspend fun getMatches(): FixtureResponse {
        return fixtureApiInterface.getMatches()
    }
}