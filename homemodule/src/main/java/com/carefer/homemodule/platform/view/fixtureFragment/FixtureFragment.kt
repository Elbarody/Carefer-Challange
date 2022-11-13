package com.carefer.homemodule.platform.view.fixtureFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CompoundButton
import androidx.fragment.app.viewModels
import com.carefer.basemodule.data.models.fixturemodels.MatchesItem
import com.carefer.basemodule.view.BaseFragment
import com.carefer.homemodule.databinding.FragmentFixtureBinding
import dagger.hilt.android.AndroidEntryPoint
import java.util.*


@AndroidEntryPoint
class FixtureFragment : BaseFragment<FragmentFixtureBinding>(){

    private lateinit var matchesAdapter: FixtureAdapter
    var matchesList = ArrayList<MatchesItem>()

    override val mViewModel: FixtureViewModel by viewModels()

    override fun initBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        attachToParent: Boolean
    ): FragmentFixtureBinding =
        FragmentFixtureBinding.inflate(inflater, container, attachToParent)


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerViews()
        setupObservers()
        mViewModel.getMatches()
        mViewDataBinding.swIsFavourites.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                mViewModel.getFavMatches()
            } else {
                mViewModel.getMatches()
            }
        }

    }

    private fun setupRecyclerViews() {
        matchesAdapter =
            FixtureAdapter {
                val matchesItem = it
                if(it.isFavourite) {
                    mViewModel.addFavMatches(matchesItem)
                } else {
                    mViewModel.removeFavMatches(matchesItem)
                    if (mViewDataBinding.swIsFavourites.isChecked)
                        mViewModel.getFavMatches()
                }


            }
        mViewDataBinding.rvMatches.adapter = matchesAdapter


    }


    private fun setupObservers() {
        mViewModel.fixtureResponse.observe(viewLifecycleOwner) {
            it?.let {
                it.body()?.matches?.let { matches ->
                    matchesList.clear()
                    matchesList.addAll(matches)
                    matchesAdapter.addMatches(matchesList)
                }
            }
        }

        mViewModel.favMatchesFromDB.observe(viewLifecycleOwner) {
            it?.let {
                it.let { matches ->
                    matchesList.clear()
                    matchesList.addAll(matches)
                    matchesAdapter.addMatches(matchesList)
                }
            }
        }

        mViewModel.addFavToDB.observe(viewLifecycleOwner){matchWillUpdate ->
        }

        mViewModel.removeFavFromDB.observe(viewLifecycleOwner){matchWillUpdate ->
        }

        mViewModel.loading.observe(viewLifecycleOwner){
            if (it){
                mViewDataBinding.pbLoading.visibility = View.VISIBLE
            }else
                mViewDataBinding.pbLoading.visibility = View.GONE


        }

    }

}