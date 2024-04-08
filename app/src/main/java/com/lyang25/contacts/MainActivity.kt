package com.lyang25.contacts

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.ui.Modifier
import com.lyang25.contacts.data.Contact
import com.lyang25.contacts.ui.screens.MainScreen
import com.lyang25.contacts.ui.theme.ContactsTheme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : ComponentActivity() {

    private val dao = ContactsApp.database.contactDao()
    private var contactList = mutableStateListOf<Contact>()
    private var scope = MainScope()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ContactsTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainScreen(
                        contactList = contactList,
                        addContact = { addContact(it) },

                    ) {
                        deleteContact(it)
                    }
                    loadContacts()
                }
            }
        }
    }

    private fun loadContacts() {
        scope.launch {
            withContext(Dispatchers.Default) {
                dao.getAll().forEach {contact ->
                    contactList.add(contact)
                }
            }
        }
    }

    private fun addContact(temp: String) {
        scope.launch {
            withContext(Dispatchers.Default) {
                dao.insertContact(Contact(fname = temp))

                contactList.clear()
                loadContacts()
            }
        }
    }

    private fun deleteContact(contact: Contact) {
        scope.launch {
            withContext(Dispatchers.Default) {
                dao.deleteContact(contact)

                contactList.clear()
                loadContacts()
            }
        }
    }

}