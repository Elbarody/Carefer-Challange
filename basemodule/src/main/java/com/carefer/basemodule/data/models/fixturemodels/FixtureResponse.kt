package com.carefer.basemodule.data.models.fixturemodels

import com.google.gson.annotations.SerializedName

data class FixtureResponse(
	@field:SerializedName("matches") var matches: List<MatchesItem> = ArrayList()
)