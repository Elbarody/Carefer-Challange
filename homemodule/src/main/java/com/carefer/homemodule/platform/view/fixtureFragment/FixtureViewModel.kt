package com.carefer.homemodule.platform.view.fixtureFragment

import com.carefer.basemodule.data.models.fixturemodels.FixtureResponse
import com.carefer.basemodule.data.models.fixturemodels.MatchesItem
import com.carefer.basemodule.data.remote.coroutines.Failure
import com.carefer.basemodule.data.remote.coroutines.Success
import com.carefer.basemodule.utility.SingleLiveEvent
import com.carefer.basemodule.view.BaseViewModel
import com.carefer.homemodule.core.domain.matchesData.FixtureUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class FixtureViewModel @Inject constructor(private val fixtureUseCase: FixtureUseCase)
    : BaseViewModel() {

    var fixtureResponse =
        SingleLiveEvent<FixtureResponse>()

    var favMatchesFromDB =
        SingleLiveEvent<List<MatchesItem>>()

    val addFavToDB =
        SingleLiveEvent<Boolean>()

    val removeFavFromDB =
        SingleLiveEvent<Boolean>()

    fun getMatches() {
        loading.postValue(true)
        fixtureUseCase.getMatches {
            when (it) {
                is Success<*> -> {
                    loading.postValue(false)
                    val response = it.result as FixtureResponse
                    fixtureResponse.setValue(response)
                }
                is Failure<*> -> {
                    loading.postValue(false)
                }
            }
        }
    }

    fun getFavMatches() {
        loading.postValue(true)
        fixtureUseCase.getFavouriteMatchesFromDB {
            when (it) {
                is Success<*> -> {
                    loading.postValue(false)
                    val matches = it.result as List<MatchesItem>
                    favMatchesFromDB.setValue(matches)
                }
                is Failure<*> -> {
                    loading.postValue(false)
                }
            }
        }
    }

    fun addFavMatches(matchesItem : MatchesItem) {
        fixtureUseCase.addFavMatches(matchesItem) {
            when (it) {
                is Success<*> -> {
                    addFavToDB.setValue(true)
                }
                is Failure<*> -> {
                }
            }
        }
    }

    fun removeFavMatches(matchesItem : MatchesItem) {
        fixtureUseCase.removeMatchFromDataBase(matchesItem) {
            when (it) {
                is Success<*> -> {
                    removeFavFromDB.setValue(true)
                }
                is Failure<*> -> {
                }
            }
        }
    }
}