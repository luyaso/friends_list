package com.lyang25.contacts.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.lyang25.contacts.R
import com.lyang25.contacts.data.Contact
import com.lyang25.contacts.ui.theme.ContactsTheme
import java.util.UUID

@Composable
fun AddContactDialog(
    onAddContact: (Contact) -> Unit,
    onCancel: () -> Unit,
) {
    var fnameString by remember { mutableStateOf("") }
    var lnameString by remember { mutableStateOf("") }
    var nnameString by remember { mutableStateOf("") }
    var emailAddy by remember { mutableStateOf("") }

    val pronounList = stringArrayResource(id = R.array.pronouns)
    var pronounIndex by remember { mutableIntStateOf(-1) }
    var pronounPickerExpanded by remember { mutableStateOf(false) }
    
    Dialog(onDismissRequest = { }) {
        Surface(
            shape = RoundedCornerShape(12.dp),
        ) {
            Column(
                Modifier.background(Color.White),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(modifier = Modifier.padding(8.dp),
                    text = stringResource(R.string.new_contact),
                    fontSize = 20.sp,
                )

                OutlinedTextField(
                    modifier = Modifier.padding(8.dp),
                    value = fnameString,
                    onValueChange = {fnameString = it},
                    label = {
                        Text(stringResource(id = R.string.fname))
                    }
                )

                OutlinedTextField(
                    modifier = Modifier.padding(8.dp),
                    value = lnameString,
                    onValueChange = {lnameString = it},
                    label = {
                        Text(stringResource(id = R.string.lname))
                    }
                )

                OutlinedTextField(
                    modifier = Modifier.padding(8.dp),
                    value = nnameString,
                    onValueChange = {nnameString = it},
                    label = {
                        Text(stringResource(id = R.string.nname))
                    }
                )

                OutlinedTextField(
                    modifier = Modifier.padding(8.dp),
                    value = emailAddy,
                    onValueChange = {emailAddy = it},
                    label = {
                        Text(stringResource(id = R.string.email))
                    }
                )

                OutlinedButton(onClick = { pronounPickerExpanded = true }) {
                    Text(
                        if (pronounIndex >= 0)
                            pronounList[pronounIndex]
                        else stringResource(R.string.select_pronoun)
                    )
                    DropdownMenu(
                        expanded = pronounPickerExpanded,
                        onDismissRequest = { pronounPickerExpanded = false }
                    ) {
                        pronounList.forEachIndexed { index, s ->
                            DropdownMenuItem(text = {
                                Text(s)
                            }, onClick = {
                                pronounIndex = index
                                pronounPickerExpanded = false
                            })
                        }
                    }
                }

                Row() {
                    OutlinedButton(
                        onClick = onCancel,
                        Modifier
                            .fillMaxWidth()
                            .padding(8.dp)
                            .weight(1f)
                    ) {
                        Text(text = stringResource(R.string.cancel))
                    }

                    Button(
                        onClick = {
                            onAddContact(
                                Contact(
                                    uuid = UUID.randomUUID(),
                                    fname = fnameString,
                                    lname = lnameString,
                                    nname = nnameString,
                                    email = emailAddy,
                                    pronouns = pronounList[pronounIndex]
                                )
                            )
                        },
                        Modifier
                            .fillMaxWidth()
                            .padding(8.dp)
                            .weight(1f)
                    ) {
                        Text(text = stringResource(R.string.add))
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun AddContactDialogPreview() {
    ContactsTheme {
        AddContactDialog(
            onCancel = {},
            onAddContact = {
                Contact(lname = "last", fname = "first", nname = "nick")
            })
    }
}