package com.lyang25.contacts

import android.app.Application
import androidx.room.Room
import com.lyang25.contacts.data.ContactDatabase

class ContactsApp : Application() {

    companion object {
        lateinit var database: ContactDatabase
    }

    override fun onCreate() {
        super.onCreate()

        database = Room.databaseBuilder(
            applicationContext,
            ContactDatabase::class.java,
            ContactDatabase.NAME,
        ).build()
    }
}