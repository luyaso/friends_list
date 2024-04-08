package com.lyang25.contacts.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.lyang25.contacts.R
import com.lyang25.contacts.data.Contact
import com.lyang25.contacts.ui.parts.ContactItem
import com.lyang25.contacts.ui.theme.ContactsTheme
import java.util.UUID

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    contactList: List<Contact>,
    addContact: (String) -> Unit,
    deleteContact: (Contact) -> Unit,
) {

    var showAddContactDialog by remember { mutableStateOf(false) }

    if (showAddContactDialog) {

        AddContactScreen(
            onCancel = { showAddContactDialog = false },
            onAddContact = {
                addContact(it)
                showAddContactDialog = false
            })

    } else {
        Scaffold(

            topBar = {
                TopAppBar(
                    colors = topAppBarColors(
                        containerColor = MaterialTheme.colorScheme.primaryContainer,
                        titleContentColor = MaterialTheme.colorScheme.primary
                    ),
                    title = {
                        Text(stringResource(id = R.string.app_name))
                    },
                    actions = {
                        IconButton(onClick = {

                        }) {
                            Icon(
                                Icons.Default.Search,
                                contentDescription = null
                            )
                        }
                    }

                )
            },

            bottomBar = {

                BottomAppBar(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    contentColor = MaterialTheme.colorScheme.primary
                ) {
                    Row {
                        IconButton(onClick = {

                        }) {
                            Icon(
                                Icons.Default.Settings,
                                contentDescription = null
                            )
                        }
                    }
                }
            },

            floatingActionButton = {

                FloatingActionButton(onClick = {
                    showAddContactDialog = true
                }) {
                    Icon(
                        Icons.Default.Add,
                        contentDescription = null
                    )
                }
            }

        ) { innerPadding ->
            Column(
                modifier = Modifier.padding(innerPadding),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                ) {
                    items(contactList) { contact ->
                        key(contact.id) {
                            ContactItem(
                                contact = contact,
                                onClick = {})
                        }
                    }
                }
            }

        }

    }
}

@Preview
@Composable
fun MainScreenPreview() {
    ContactsTheme {
        MainScreen(
            contactList = listOf(Contact(0L, UUID(0L,0L), "first", "last", "nick")),
            addContact = {},
            deleteContact = {})
    }
}