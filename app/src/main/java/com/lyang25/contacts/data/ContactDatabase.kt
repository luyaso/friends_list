package com.lyang25.contacts.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [Contact::class], version = 1, exportSchema = true)
abstract class ContactDatabase : RoomDatabase() {

    abstract fun contactDao(): ContactDao

    companion object {
        const val NAME = "contact_database"
    }
}