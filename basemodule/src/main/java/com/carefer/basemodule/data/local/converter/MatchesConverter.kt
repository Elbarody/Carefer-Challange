package com.carefer.basemodule.data.local.converter

import androidx.room.TypeConverter
import com.carefer.basemodule.data.models.fixturemodels.Score
import com.carefer.basemodule.data.models.fixturemodels.Team
import com.google.gson.Gson

class MatchesConverter {

    @TypeConverter
    fun stringToScore(string: String): Score {
        return Gson().fromJson<Score>(string, Score::class.java)
    }

    @TypeConverter
    fun scoreToString(score: Score): String {
        return Gson().toJson(score)
    }

    @TypeConverter
    fun stringToTeam(string: String): Team {
        return Gson().fromJson<Team>(string, Team::class.java)
    }

    @TypeConverter
    fun teamToString(team: Team): String {
        return Gson().toJson(team)
    }
}