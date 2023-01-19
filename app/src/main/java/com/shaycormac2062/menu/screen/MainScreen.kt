package com.shaycormac2062.menu.screen

import androidx.compose.animation.*
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.shaycormac2062.menu.R

@OptIn(
    ExperimentalFoundationApi::class,
    ExperimentalAnimationApi::class,
    ExperimentalMaterialApi::class
)
@Composable
@Preview(showBackground = true)
fun MainScreen() {
    Scaffold(
        bottomBar = {
            Column(
                horizontalAlignment = Alignment.End,
                verticalArrangement = Arrangement.Bottom
            ) {
                var expanded by remember { mutableStateOf(false) }
                val buttons by remember {
                    mutableStateOf(
                        arrayListOf(
                            R.drawable.settings,
                            R.drawable.home,
                            R.drawable.search,
                            R.drawable.time,
                            R.drawable.mosaic,
                        )
                    )
                }
                AnimatedVisibility(
                    visible = expanded,
                    enter = slideInVertically(
                        initialOffsetY = { -40 }
                    ) + expandVertically(
                        expandFrom = Alignment.Bottom
                    ) + fadeIn(initialAlpha = 0f),
                    exit = slideOutVertically()
                            + shrinkVertically()
                            + fadeOut()
                ) {
                    LazyColumn(
                        modifier = Modifier.padding(
                            end = 24.dp,
                            bottom = 12.dp
                        )
                    ) {
                        items(
                            items = buttons,
                            key = { it }
                        ) {
                            val dismissState = rememberDismissState()
                            if (
                                dismissState.isDismissed(DismissDirection.EndToStart) ||
                                dismissState.isDismissed(DismissDirection.StartToEnd)
                            ) {
                                buttons.remove(it)
                            }
                            SwipeToDismiss(
                                state = dismissState,
                                directions = setOf(
                                    DismissDirection.EndToStart,
                                    DismissDirection.StartToEnd
                                ),
                                dismissThresholds = { direction ->
                                    FractionalThreshold(if (direction == DismissDirection.EndToStart) 0.1f else 0.05f)
                                },
                                background = {},
                                dismissContent = {
                                    MenuElement(
                                        vector = ImageVector.vectorResource(id = it),
                                        modifier = Modifier.animateItemPlacement()
                                    )
                                }
                            )
                        }
                    }
                }
                BottomBar {
                    expanded = !expanded
                }
            }
        },
        content = {
            Background()
        }
    )
}
