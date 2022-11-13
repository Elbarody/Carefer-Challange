package com.carefer.basemodule.data.local.room.doa

import androidx.room.*
import com.carefer.basemodule.data.models.fixturemodels.MatchesItem

@Dao
interface MatchesDoa {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(vararg matchesItem: MatchesItem)

    @Query("select * from matches where isFavourite = 1 order by date asc")
    suspend fun getFavouriteMatches(): List<MatchesItem>

    @Delete
    suspend fun removeMatchFromDataBase( matchesItem: MatchesItem)
}