package com.shaycormac2062.menu.screen

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.shaycormac2062.menu.R
import com.shaycormac2062.menu.ui.theme.Teal400

@Composable
fun Background() {
    Image(
        painter = painterResource(id = R.drawable.background),
        contentDescription = null,
        modifier = Modifier.fillMaxSize(),
        contentScale = ContentScale.Crop
    )
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun BottomBar(
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Teal400),
        horizontalArrangement = Arrangement.End,
        verticalAlignment = Alignment.Bottom
    ) {
        AppButton(
            modifier = Modifier
                .width(90.dp)
                .height(40.dp)
                .background(Teal400),
            text = stringResource(R.string.menu)
        ) {
            onClick.invoke()
        }
    }
}

@ExperimentalAnimationApi
@Composable
fun AppButton(
    modifier: Modifier? = Modifier,
    text: String = "",
    enabled: Boolean = true,
    onClick: () -> Unit
) {
    Button(
        contentPadding = PaddingValues(),
        onClick = {
            onClick.invoke()
        },
        enabled = enabled,
        modifier = Modifier
            .clip(RoundedCornerShape(18.dp))
            .background(Teal400)
            .padding(end = 15.dp)
    ) {
        Box(
            modifier = modifier ?: Modifier,
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = text,
                color = Color.White
            )
        }
    }
}

@Composable
fun MenuElement(
    vector: ImageVector,
    modifier: Modifier = Modifier
) {
    IconButton(
        onClick = { },
        modifier = modifier
            .clip(CircleShape)
            .size(56.dp)
            .background(Teal400),
    ) {
        Icon(
            vector,
            contentDescription = null,
            tint = Color.White
        )
    }
}
