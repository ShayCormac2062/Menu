package com.shaycormac2062.menu.screen

import androidx.compose.animation.*
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.shaycormac2062.menu.R

@OptIn(
    ExperimentalFoundationApi::class,
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
                            buttons.size
                        ) { index ->
                            val dismissState = rememberDismissState()
                            if (
                                dismissState.isDismissed(DismissDirection.EndToStart) ||
                                dismissState.isDismissed(DismissDirection.StartToEnd)
                            ) {
                                if (index < buttons.size) buttons.removeAt(index)
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
                                    if (index < buttons.size) {
                                        MenuElement(
                                            vector = ImageVector.vectorResource(id = buttons[index]),
                                            modifier = Modifier.animateItemPlacement()
                                        )
                                    }
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
