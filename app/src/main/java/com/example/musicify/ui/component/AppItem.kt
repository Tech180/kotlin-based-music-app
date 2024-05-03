// AppItem.kt
package com.example.musicify.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import kotlinx.coroutines.CoroutineScope

/*
@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun AppItem(
    app: SuperUserViewModel.AppInfo,
    onClickListener: () -> Unit,
) {
    ListItem(
        modifier = Modifier.clickable(onClick = onClickListener),
        headlineContent = { Text(app.label) },
        supportingContent = {
            Column {
                Text(app.packageName)
                FlowRow {
                    if (app.allowSu) {
                        LabelText(label = "ROOT")
                    } else {
                        if (Natives.uidShouldUmount(app.uid)) {
                            LabelText(label = "UMOUNT")
                        }
                    }
                    if (app.hasCustomProfile) {
                        LabelText(label = "CUSTOM")
                    }
                }
            }
        },
        leadingContent = {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(app.packageInfo)
                    .crossfade(true)
                    .build(),
                contentDescription = app.label,
                modifier = Modifier
                    .padding(4.dp)
                    .width(48.dp)
                    .height(48.dp)
            )
        },
    )
}

@Composable
fun LabelText(label: String) {
    Box(
        modifier = Modifier
            .padding(top = 4.dp, end = 4.dp)
            .background(
                Color.Black,
                shape = RoundedCornerShape(4.dp)
            )
    ) {
        Text(
            text = label,
            modifier = Modifier.padding(vertical = 2.dp, horizontal = 5.dp),
            style = TextStyle (
                fontSize = 8.sp,
                color = Color.White,
            )
        )
    }
}

@Composable
fun ScaffoldContent (
    viewModel: SuperUserViewModel,
    scope: CoroutineScope,
    navigator: AppNavigator,
) {
    Scaffold(
        topBar = {
            CustomSearchBar(
                title = { Text(stringResource(R.string.app_name)) },
                searchText = viewModel.search,
                onSearchTextChange = { viewModel.search = it },
                onClearClick = { viewModel.search = "" },
                dropdownContent = {
                    var showDropdown by remember { mutableStateOf(false) }

                    IconButton(
                        onClick = { showDropdown = true },
                    ) {
                        Icon(
                            imageVector = Icons.Filled.MoreVert,
                            contentDescription = stringResource(id = 0)
                        )

                        DropdownMenu(expanded = showDropdown, onDismissRequest = {
                            showDropdown = false
                        }) {
                            DropdownMenuItem(text = {
                                Text(stringResource(R.string.app_name))
                            }, onClick = {
                                scope.launch {
                                    viewModel.fetchAppList()
                                }
                                showDropdown = false
                            })
                            DropdownMenuItem(text = {
                                Text(
                                    if (viewModel.showSystemApps) {
                                        stringResource(R.string.app_name)
                                    } else {
                                        stringResource(R.string.app_name)
                                    }
                                )
                            }, onClick = {
                                viewModel.showSystemApps = !viewModel.showSystemApps
                                showDropdown = false
                            })
                        }
                    }
                },
            )
        }
    ) { innerPadding ->
        val refreshState = rememberPullRefreshState(
            refreshing = viewModel.isRefreshing,
            onRefresh = { scope.launch { viewModel.fetchAppList() } },
        )
        Box(
            modifier = Modifier
                .padding(innerPadding)
                .pullRefresh(refreshState)
        ) {
            LazyColumn(Modifier.fillMaxSize()) {
                items(viewModel.appList, key = { it.packageName + it.uid }) { app ->
                    AppItem(app) {
                        navigator.navigate(AppProfileScreenDestination(app))
                    }

                }
            }

            PullRefreshIndicator(
                refreshing = viewModel.isRefreshing,
                state = refreshState,
                modifier = Modifier.align(Alignment.TopCenter)
            )
        }
    }
}

 */

