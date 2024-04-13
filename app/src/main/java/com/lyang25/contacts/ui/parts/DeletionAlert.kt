package com.lyang25.contacts.ui.parts

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lyang25.contacts.R
import com.lyang25.contacts.data.Contact
import com.lyang25.contacts.ui.theme.ContactsTheme


@Composable
fun DeletionAlert(
    contact: Contact,
    onCancel: () -> Unit,
    onDelete: () -> Unit,
) {
    
    AlertDialog(

        title = { Text(stringResource(id = R.string.confirmdelete)) },
        text = {
            Text(
                "${contact.lname}, ${contact.fname}",
                modifier = Modifier.padding(horizontal = 8.dp),
                fontWeight = FontWeight.Medium,
                fontSize = 21.sp,
            )
        },

        onDismissRequest = { },
        dismissButton = {
            OutlinedButton(onClick = onCancel) {
                Text(stringResource(id = R.string.cancel))
            }
        },
        confirmButton = {
            Button(onClick = onDelete) {
                Text(stringResource(R.string.delete))
            }
        },
    )

}

@Preview
@Composable
fun DeletionAlertPreview() {
    ContactsTheme {
        DeletionAlert(
            contact = Contact(fname = "first", lname = "last", nname = "nick"),
            onCancel = {},
            onDelete = {},
        )
    }
}