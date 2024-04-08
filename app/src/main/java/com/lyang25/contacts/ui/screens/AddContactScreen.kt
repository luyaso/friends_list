package com.lyang25.contacts.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lyang25.contacts.R
import com.lyang25.contacts.ui.theme.ContactsTheme

@Composable
fun AddContactScreen(
    onCancel: () -> Unit,
    onAddContact: (String) -> Unit,
) {
    var inputString by remember { mutableStateOf("") }

    Column(
        Modifier.background(Color.White)
    ) {
        Text(modifier = Modifier.padding(8.dp),
            text = stringResource(R.string.new_contact),
            fontSize = 20.sp,
        )

        OutlinedTextField(
            modifier = Modifier.padding(8.dp),
            value = inputString,
            onValueChange = {inputString = it},
            label = {
                Text(stringResource(id = R.string.contact))
            }
        )

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
                onClick = { onAddContact(inputString) },
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

@Preview
@Composable
fun AddContactScreenPreview() {
    ContactsTheme {
        AddContactScreen(onCancel = {}, onAddContact = {})
    }
}