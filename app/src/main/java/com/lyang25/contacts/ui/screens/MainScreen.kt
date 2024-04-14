package com.lyang25.contacts.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.lyang25.contacts.R
import com.lyang25.contacts.data.Contact
import com.lyang25.contacts.ui.parts.ContactItem
import com.lyang25.contacts.ui.parts.DeletionAlert
import com.lyang25.contacts.ui.theme.ContactsTheme
import java.util.UUID

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    contactList: List<Contact>,
    addContact: (Contact) -> Unit,
    deleteContact: (Contact) -> Unit,
) {

    var showAddContactDialog by remember { mutableStateOf(false) }
    var showDeletionAlert by remember { mutableStateOf(false) }
    var selection by remember { mutableStateOf<Contact?>(null) }

    if (showAddContactDialog) {

        AddContactDialog(
            onCancel = { showAddContactDialog = false },
            onAddContact = {
                addContact(it)
                showAddContactDialog = false
            }
        )

    } else if (showDeletionAlert) {
        DeletionAlert(
            contact = selection?: Contact(),
            onCancel = {
                showDeletionAlert = false
            },
            onDelete = {
                val deleteItem: Contact? = selection
                if (deleteItem != null) {
                    deleteContact(deleteItem)
                    selection = null
                }
                showDeletionAlert = false
            }
        )
    }

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
//                    actions = {
//                        IconButton(onClick = {
//
//                        }) {
//                            Icon(
//                                Icons.Default.Search,
//                                contentDescription = null
//                            )
//                        }
//                    }
            )
        },

        bottomBar = {
            BottomAppBar(
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                contentColor = MaterialTheme.colorScheme.primary
            ) {
//                    Row {
//                        IconButton(onClick = {
//
//                        }) {
//                            Icon(
//                                Icons.Default.Settings,
//                                contentDescription = null
//                            )
//                        }
//                    }
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
                    Row(
                        modifier = Modifier
                            .padding(4.dp)
                            .background(color = Color.LightGray)
                    ) {

                        Box(
                            modifier = Modifier.weight(5f)
                        ) {
                            key(contact.id) {
                                ContactItem(
                                    contact = contact,
                                    onClick = {
                                        // todo:
                                    })
                            }
                        }

                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .padding(vertical = 18.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            IconButton(onClick = {
//                                    deleteContact(contact)
                                selection = contact
                                showDeletionAlert = true
                            }) {
                                Icon(
                                    Icons.Default.Delete,
                                    contentDescription = null
                                )
                            }
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
            contactList = listOf(Contact(0L, UUID(0L,0L),
                "first", "last", "nick",
                pronouns = "he/him/his")),
            addContact = {},
            deleteContact = {})
    }
}