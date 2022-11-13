package com.carefer.homemodule.core.domain.matchesData


import com.carefer.basemodule.data.models.fixturemodels.FixtureResponse
import com.carefer.basemodule.data.models.fixturemodels.MatchesItem
import com.carefer.basemodule.data.remote.coroutines.CoroutinesProviderImpl
import com.carefer.basemodule.data.remote.coroutines.Failure
import com.carefer.homemodule.core.data.repo.FixtureRepository
import com.carefer.basemodule.data.remote.coroutines.Result
import com.carefer.basemodule.data.remote.coroutines.Success
import retrofit2.Response
import javax.inject.Inject

class FixtureUseCaseImpl @Inject constructor(
    private val fixtureRepository: FixtureRepository,
    private val coroutines: CoroutinesProviderImpl
) : FixtureUseCase {

    override fun getMatches(callback: ((Result) -> Unit)) {
        coroutines.request({ fixtureRepository.getMatches() }, {
            when(it){
                is Success<*> -> {
                    val response = it.result as Response<FixtureResponse>
                    val matchesList = response.body()?.matches

                    coroutines.request({fixtureRepository.getFavouriteMatchesFromDB()},{fav ->
                        when(fav){
                            is Success<*> -> {
                                val favList = fav.result as  List<MatchesItem>
                                favList.forEach {matchItemInFav ->
                                    matchesList?.find { match -> match.id ==  matchItemInFav.id}?.isFavourite = true
                                }
                                if (matchesList != null) {
                                    response.body()?.matches = matchesList
                                }
                                callback(Success(response))
                            }
                            is Failure<*> ->{
                                callback(it)
                            }
                        }
                    })
                }
                is Failure<*> -> {
                    callback(it)
                }

            }
            callback(it)
        })
    }

    override fun getFavouriteMatchesFromDB(callback: (Result) -> Unit) {
        coroutines.request({fixtureRepository.getFavouriteMatchesFromDB()},{
            callback(it)
        })
    }

    override fun addFavMatches(matchesItem: MatchesItem, callback: (Result) -> Unit) {
        coroutines.request({fixtureRepository.addFavouriteMatchToDataBase(matchesItem)},{
            callback(it)
        })
    }

    override fun removeMatchFromDataBase(matchesItem: MatchesItem, callback: (Result) -> Unit) {
        coroutines.request({fixtureRepository.removeMatchFromDataBase(matchesItem)},{
            callback(it)
        })
    }


}