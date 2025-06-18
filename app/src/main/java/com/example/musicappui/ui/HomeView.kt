package com.example.musicappui.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.musicappui.R
import androidx.compose.ui.graphics.Color


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeView() {
    val categories = listOf("Hits", "Happy", "Workout", "TGIF", "Yoga")
    val grouped = listOf<String>("New Release", "Favorites", "Top Rated").groupBy {
        it[0]
    }
    LazyColumn {
        grouped.forEach {
            stickyHeader {
                Text(it.value[0], modifier = Modifier.padding(16.dp))
                LazyRow {
                    items(categories) { cat ->
                        BrowserItem(cat = cat, drarwable = R.drawable.ic_account)
                    }
                }
            }
        }
    }
}


@Composable
fun BrowserItem(cat: String, drarwable: Int) {
    Card(
        modifier = Modifier
            .padding(16.dp)
            .size(200.dp),
        border = BorderStroke(
            3.dp,
            color = Color.DarkGray
        )
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(cat)
            Image(painter = painterResource(id = drarwable), contentDescription = cat)
        }
    }
}