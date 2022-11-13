package com.carefer.basemodule.data.models.fixturemodels

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.carefer.basemodule.data.local.converter.MatchesConverter
import com.google.gson.annotations.SerializedName

@Entity(tableName = "matches")
@TypeConverters(MatchesConverter::class)
data class MatchesItem(

    @PrimaryKey
    val id: Int? = null,

    @field:SerializedName("score")
    val score: Score? = Score(),

    @field:SerializedName("awayTeam")
    val awayteam: Team = Team(),

    @field:SerializedName("homeTeam")
    val homeTeam: Team = Team(),

    @field:SerializedName("utcDate")
    val date: String? = "",

    @field:SerializedName("status")
    val status: String? = "",

    var isFavourite: Boolean = false
)