package com.carefer.basemodule.data.models.fixturemodels

import com.google.gson.annotations.SerializedName

data class Score(


    @field:SerializedName("fullTime")
    val fullTime: FullTime? = FullTime()
    )