package com.carefer.homemodule

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.carefer.basemodule.data.models.fixturemodels.*
import com.carefer.basemodule.data.remote.coroutines.CoroutinesProviderImpl
import com.carefer.basemodule.data.remote.coroutines.dispatchers.TestDispatcher
import com.carefer.homemodule.core.data.localdata.LocalDataSource
import com.carefer.homemodule.core.data.remote.FixtureRemoteDataSource
import com.carefer.homemodule.core.data.repo.FixtureRepositoryImpl
import com.carefer.homemodule.core.domain.matchesData.FixtureUseCaseImpl
import com.nhaarman.mockitokotlin2.verifyNoMoreInteractions
import com.carefer.homemodule.platform.view.fixtureFragment.FixtureViewModel
import com.github.testcoroutinesrule.TestCoroutineRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations

class MatchesWholeCycleUnitTesting {
    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    lateinit var dispatcher: TestDispatcher

    lateinit var viewModel: FixtureViewModel
    lateinit var coroutine: CoroutinesProviderImpl
    lateinit var fixtureUseCase: FixtureUseCaseImpl
    lateinit var fixtureRepository: FixtureRepositoryImpl

    @Mock
    lateinit var localDataSource: LocalDataSource

    @Mock
    lateinit var fixtureRemoteDataSource: FixtureRemoteDataSource

    @Mock
    lateinit var fixtureResponseSuccess : Observer<FixtureResponse>

    @Mock
    lateinit var favMatchesFromDBSuccess : Observer<List<MatchesItem>>

    @Mock
    lateinit var addFavToDBSuccess : Observer<Boolean>

    @Mock
    lateinit var removeFavFromDBSuccess : Observer<Boolean>

    @Mock
    lateinit var loadingObserver: Observer<Boolean>

    @Mock
    lateinit var errorObserver: Observer<Any>

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        fixtureRepository =
            FixtureRepositoryImpl(fixtureRemoteDataSource, localDataSource)
        dispatcher = TestDispatcher()
        coroutine = CoroutinesProviderImpl(dispatcher)
        fixtureUseCase = FixtureUseCaseImpl(fixtureRepository, coroutine)
        viewModel = FixtureViewModel(fixtureUseCase)
        viewModel.fixtureResponse.observeForever(fixtureResponseSuccess)
        viewModel.favMatchesFromDB.observeForever(favMatchesFromDBSuccess)
        viewModel.addFavToDB.observeForever(addFavToDBSuccess)
        viewModel.removeFavFromDB.observeForever(removeFavFromDBSuccess)
        viewModel.loading.observeForever(loadingObserver)
        viewModel.error.observeForever(errorObserver)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun test_get_all_matches_success() = testCoroutineRule.runBlockingTest {
        val matchItem = MatchesItem(id= 1,score =  Score(FullTime(awayTeam = 1, homeTeam = 5)),
            homeTeam =Team(name = "text", id = 15), awayteam = Team(name = "text", id = 10),
            date = "10/11", status = "finished", isFavourite = false)
        val response  = FixtureResponse(listOf(matchItem))

        `when`(localDataSource.getFavouriteMatchesFromDB()).thenReturn(listOf(matchItem))
        `when`(
            fixtureRemoteDataSource.getMatches()
        ).thenReturn(
            response
        )

        viewModel.getMatches()



        verify(loadingObserver).onChanged(true)
        verify(fixtureResponseSuccess).onChanged(response)
        verify(loadingObserver).onChanged(false)
        verifyNoMoreInteractions(fixtureResponseSuccess)
        verifyNoMoreInteractions(loadingObserver)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun test_get_all_matches_Failure() = testCoroutineRule.runBlockingTest {
        val exception = RuntimeException("Failed")
        val matchItem = MatchesItem()
        val favMatches  = listOf(matchItem)

        `when`(localDataSource.getFavouriteMatchesFromDB()).thenReturn(favMatches)
        `when`(
            fixtureRemoteDataSource.getMatches()
        ).thenThrow(exception)

        viewModel.getMatches()

        verify(loadingObserver).onChanged(true)
        verify(errorObserver).onChanged(any(java.lang.RuntimeException::class.java))
        verify(loadingObserver).onChanged(false)
        verifyNoMoreInteractions(errorObserver)
        verifyNoMoreInteractions(loadingObserver)
    }

    //Order details
    @Test
    @ExperimentalCoroutinesApi
    fun test_get_fav_matches_success() = testCoroutineRule.runBlockingTest {
        val matchItem = MatchesItem(1, Score(FullTime(1,5)), Team("text",15),Team("text",10),"10/11","finished",false)
        val favMatches  = listOf(matchItem)

        `when`(localDataSource.getFavouriteMatchesFromDB())
        .thenReturn(
            favMatches
        )

        viewModel.getFavMatches()


        verify(loadingObserver).onChanged(true)
        verify(favMatchesFromDBSuccess).onChanged(favMatches)
        verify(loadingObserver).onChanged(false)
        verifyNoMoreInteractions(favMatchesFromDBSuccess)
        verifyNoMoreInteractions(loadingObserver)
    }

    @Test
    @ExperimentalCoroutinesApi
    fun test_get_order_details_Failure() = testCoroutineRule.runBlockingTest {
        val exception = RuntimeException("Failed")
        val matchItem = MatchesItem()

        `when`(localDataSource.getFavouriteMatchesFromDB()).thenReturn(listOf(matchItem))
        .thenThrow(exception)

        viewModel.getFavMatches()

        verify(loadingObserver).onChanged(true)
        verify(errorObserver).onChanged(any(java.lang.RuntimeException::class.java))
        verify(loadingObserver).onChanged(false)
        verifyNoMoreInteractions(errorObserver)
        verifyNoMoreInteractions(loadingObserver)
    }

}