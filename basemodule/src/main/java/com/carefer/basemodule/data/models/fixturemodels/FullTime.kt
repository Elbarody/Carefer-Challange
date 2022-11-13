package com.carefer.basemodule.data.models.fixturemodels

import com.google.gson.annotations.SerializedName

data class FullTime(

	@field:SerializedName("awayTeam")
	val awayTeam: Int? = 0,

	@field:SerializedName("homeTeam")
	val homeTeam: Int? = 0
)