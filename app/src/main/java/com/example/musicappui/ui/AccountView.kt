package com.example.musicappui.ui

import android.content.res.Resources
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.ModalDrawer
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.musicappui.R

@Composable
fun AccountView() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row {
                Icon(
                    imageVector = Icons.Default.AccountCircle, contentDescription = "Account",
                    modifier = Modifier.padding(end = 8.dp)
                )

                Column {
                    Text("SS")
                    Text("SS@gmail.com")
                }
            }

            IconButton(onClick = {}) {
                Icon(painterResource(R.drawable.outline_keyboard_arrow_right_24), contentDescription = null)
            }
        }

        Row(modifier = Modifier.padding(top =  16.dp) ) {
            Icon(
                painter = painterResource(R.drawable.my_music),
                contentDescription = "My Music",
                modifier = Modifier.padding(end =  8.dp)
            )
            Text("My Music")
        }

        Divider()
    }
}