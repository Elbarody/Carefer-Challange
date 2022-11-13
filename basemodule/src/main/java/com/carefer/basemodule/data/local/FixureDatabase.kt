package com.carefer.basemodule.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.carefer.basemodule.data.local.room.doa.MatchesDoa
import com.carefer.basemodule.data.models.fixturemodels.MatchesItem

@Database(
    entities = [MatchesItem::class],
    version = 1)

abstract class MatchesDatabase : RoomDatabase() {

    abstract fun matchesDao(): MatchesDoa
}
