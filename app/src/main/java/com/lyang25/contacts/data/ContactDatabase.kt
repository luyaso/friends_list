package com.lyang25.contacts.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

@Database(entities = [Contact::class], version = 2, exportSchema = true)
abstract class ContactDatabase : RoomDatabase() {

    abstract fun contactDao(): ContactDao

    companion object {
        const val NAME = "contact_database"
    }
}

val migration_1_2 = object : Migration(1, 2) {
    override fun migrate(db: SupportSQLiteDatabase) {
        val defaultStr: String = ""
        db.execSQL("ALTER TABLE contact_table ADD COLUMN email TEXT NOT NULL DEFAULT '$defaultStr'")
        db.execSQL("ALTER TABLE contact_table ADD COLUMN pronouns TEXT NOT NULL DEFAULT '$defaultStr'")
    }
}