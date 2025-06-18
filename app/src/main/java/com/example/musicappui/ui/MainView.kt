package com.example.musicappui.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.musicappui.MainViewModel
import com.example.musicappui.Screen
import com.example.musicappui.screensInBottom
import com.example.musicappui.screensInDrawer
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainView(paddingValues: PaddingValues) {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()


    val viewModel: MainViewModel = viewModel()
    val currentScreen = remember {
        viewModel.currentScreen.value
    }


    // Allow us to find which screen we are
    val controller: NavHostController = rememberNavController()
    val navBackStackEntry by controller.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route


    val title = remember {
        mutableStateOf(currentScreen.title)
    }

    val dialogOpen = remember {
        mutableStateOf(false)
    }

    val bottomBar: @Composable () -> Unit = {
        if (currentScreen is Screen.DrawerScreen || currentScreen == Screen.BottomScreen.Home) {
            BottomAppBar(
                Modifier.wrapContentSize(),
                containerColor = MaterialTheme.colorScheme.primary
            ) {
                screensInBottom.forEach { item ->
                    val isSelected = currentRoute == item.bRoute
                    val iconColor = if (isSelected) Color.Red else Color.White
                    val labelColor = if (isSelected) Color.Red else Color.White
                    BottomNavigationItem(
                        selected = isSelected,
                        onClick = {
                            viewModel.setCurrentScreen(item)
                            controller.navigate(item.bRoute)
                            title.value = item.bTitle
                        },
                        icon = {
                            Icon(
                                contentDescription = item.bTitle,
                                painter = painterResource(item.icon),
                                tint = iconColor
                            )
                        },
                        label = { Text(item.bTitle, color = labelColor) },
                        selectedContentColor = Color.Red,
                        unselectedContentColor = Color.Black
                    )
                }
            }
        }
    }

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet(
                drawerContainerColor = Color.White
            ) {
                LazyColumn {
                    items(screensInDrawer) { item ->
                        DrawerItem(selected = currentRoute == item.dRoute, item = item) {
                            scope.launch {
                                drawerState.close()
                            }
                            if (item.dRoute == "add_account") {
                                dialogOpen.value = true
                            } else {
                                controller.navigate(item.dRoute)
                                title.value = item.dTitle
                            }

                        }
                    }
                }
            }
        }
    ) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text(title.value, color = Color.White) },
                    navigationIcon = {
                        IconButton(onClick = {
                            scope.launch {
                                drawerState.open()
                            }
                        }) {
                            Icon(
                                imageVector = Icons.Default.AccountCircle,
                                contentDescription = title.value,
                                tint = Color.White
                            )
                        }
                    },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = MaterialTheme.colorScheme.primary
                    )
                )
            },
            bottomBar = bottomBar

        ) { innerPadding ->
            Navigation(
                navController = controller, viewModel = viewModel,
                paddingValues = innerPadding
            )

            AccountDialog(dialogOpen = dialogOpen)
        }
    }
}


@Composable
fun DrawerItem(
    selected: Boolean,
    item: Screen.DrawerScreen,
    onDrawerItemClicked: () -> Unit
) {
    val background = if (selected) Color.LightGray else Color.White

    Row(
        Modifier
            .fillMaxSize()
            .padding(horizontal = 8.dp, vertical = 16.dp)
            .background(background)
            .clickable {
                onDrawerItemClicked()
            },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(id = item.icon),
            contentDescription = item.dTitle,
            Modifier.padding(end = 8.dp, top = 4.dp)
        )
        Text(
            text = item.dTitle,
            style = MaterialTheme.typography.bodyMedium
        )
    }
}


@Composable
fun Navigation(
    navController: NavHostController,
    viewModel: MainViewModel,
    paddingValues: PaddingValues
) {
    NavHost(
        navController = navController,
        startDestination = Screen.DrawerScreen.Account.route,
        modifier = Modifier.padding(paddingValues)
    ) {

        composable(Screen.BottomScreen.Home.bRoute) {
            HomeView()
        }

        composable(Screen.BottomScreen.Browse.bRoute) {
            // TODO
        }

        composable(Screen.BottomScreen.Library.bRoute) {
            // TODO
        }


        composable(Screen.DrawerScreen.Account.route) {
            AccountView()

        }

        composable(Screen.DrawerScreen.Subscription.route) {
            Subscription()
        }
    }
}