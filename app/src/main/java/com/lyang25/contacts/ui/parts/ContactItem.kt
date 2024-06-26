package com.lyang25.contacts.ui.parts

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lyang25.contacts.R
import com.lyang25.contacts.data.Contact
import com.lyang25.contacts.ui.theme.ContactsTheme
import java.util.UUID

@Composable
fun ContactItem(
    contact: Contact,
    onClick: (Contact) -> Unit = {},
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .clickable {
                onClick(contact)
            },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier.weight(1f)
        ) {
            Image(
                painter = painterResource(id = R.drawable.default_pfp),
                contentDescription = null
            )
        }

        Column(
            modifier = Modifier.weight(5f),
        ) {
            Text(
                text = "${
                    if (contact.lname == "") {
                        "N/A"
                    } else contact.lname
                }, ${
                    if (contact.fname == "") {
                        "N/A"
                    } else contact.fname
                } (${
                    if (contact.nname == "") {
                        "N/A"
                    } else contact.nname
                })",
                textAlign = TextAlign.Right,
                fontSize = 21.sp,
                modifier = Modifier.fillMaxWidth()
            )

            Text(
                text = contact.pronouns,
                textAlign = TextAlign.Right,
                color = Color.DarkGray,
                fontSize = 19.sp,
                fontStyle = FontStyle.Italic,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@Preview
@Composable
fun ContactItemPreview() {
    ContactsTheme {
        ContactItem(
            contact = Contact(0, UUID(0L, 0L),
                "first", "last", "nick",
                pronouns = "she/her/hers")
        )
    }
}