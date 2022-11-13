package com.carefer.basemodule.data.models.fixturemodels

import com.google.gson.annotations.SerializedName

data class Team(

	@field:SerializedName("name")
	val name: String? = "",

	@field:SerializedName("id")
	val id: Int? = 0
)