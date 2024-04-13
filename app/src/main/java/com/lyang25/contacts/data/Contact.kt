package com.lyang25.contacts.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID

@Entity(tableName = "contact_table")
data class Contact(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "contact_id")
    var id: Long = 0L,

    @ColumnInfo(name = "uuid")
    var uuid: UUID = UUID(0L, 0L),

    @ColumnInfo(name = "first_name")
    var fname: String = "",

    @ColumnInfo(name = "last_name")
    var lname: String = "",

    @ColumnInfo(name = "nickname")
    var nname: String = "",

    @ColumnInfo(name = "email")
    var email: String = "",

    @ColumnInfo(name = "pronouns")
    var pronouns: String = "",
)