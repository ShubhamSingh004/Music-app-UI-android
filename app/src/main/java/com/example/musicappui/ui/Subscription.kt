package com.example.musicappui.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.Divider
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.musicappui.R

@Composable
fun Subscription() {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Text(
            "Manage Subscription",
            modifier = Modifier
                .padding(bottom = 8.dp)
                .fillMaxWidth(),
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )


        Card(
            modifier = Modifier.fillMaxWidth(),
            elevation = CardDefaults.cardElevation(3.dp),
            colors = CardDefaults.cardColors(Color.White)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 19.dp, bottom = 19.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column(modifier = Modifier.padding(start = 5.dp)) {
                    Text("Musical")
                    Text("Free Tier")
                }

                Row(
                    modifier = Modifier
                        .wrapContentSize()
                        .padding(start = 5.dp, end = 5.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        "See All Plans",
                        color = _root_ide_package_.androidx.compose.ui.graphics.Color.Magenta
                    )

                    IconButton(
                        modifier = Modifier.wrapContentSize(), onClick = {

                        }) {
                        Icon(
                            painter = painterResource(R.drawable.ic_arrow_right_purple),
                            contentDescription = null
                        )
                    }

                }

            }

            Divider(thickness = 1.dp, modifier = Modifier.padding(horizontal = 10.dp))

            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)) {
                Icon(
                    imageVector = Icons.Default.AccountBox,
                    contentDescription = "Get a plan"
                )
                Text("Get a plan")
            }
        }


    }
}


@Preview(showBackground = true)
@Composable
fun SubsPrev() {
    Subscription()
}