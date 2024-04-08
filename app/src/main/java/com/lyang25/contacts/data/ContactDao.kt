package com.lyang25.contacts.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ContactDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertContact(contact: Contact)

    @Delete
    fun deleteContact(contact: Contact)

    @Query("SELECT * FROM contact_table ORDER BY contact_id DESC")
    fun getAll(): MutableList<Contact>

    @Query("DELETE FROM contact_table")
    fun deleteAll()

}