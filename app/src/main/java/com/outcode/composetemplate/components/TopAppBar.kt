package com.outcode.myapplication.components

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp

/**
 * Created by Akash on 28/08/20
 */

@Composable
fun TopAppBar(@StringRes titleResource: Int, onThemeSwitch: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth().height(48.dp)
            .background(Color.Blue),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically

    ) {
        WidthSpacer(8.dp)
        Text(
            text = stringResource(id = titleResource),
            color = MaterialTheme.colorScheme.onPrimary
        )

        Box() {
            ThemeSwitcher(onThemeSwitch = {
                onThemeSwitch()
            })
        }
    }
}
@Composable
fun TopAppBarWithBack(@StringRes titleResource: Int, onBackPressed: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth().height(48.dp)
            .background(Color.Blue),
        verticalAlignment = Alignment.CenterVertically

    ) {
        WidthSpacer(8.dp)

        Box(modifier = Modifier
            .clickable {
                onBackPressed()
            }) {
           Icon(imageVector =Icons.Filled.ArrowBack , contentDescription ="bacK", tint = MaterialTheme.colorScheme.onPrimary)
        }
        WidthSpacer(20.dp)
        Text(
            text = stringResource(id = titleResource),
            color = MaterialTheme.colorScheme.onPrimary

        )
     
    }
}

@Composable
fun ThemeSwitcher(onThemeSwitch: () -> Unit) {
    val isDark = remember { mutableStateOf(false) }
    @DrawableRes val light = com.outcode.composetemplate.R.drawable.ic_launcher_background
    @DrawableRes val dark = com.outcode.composetemplate.R.drawable.ic_launcher_background
    IconButton(onClick = {
        onThemeSwitch()
        isDark.value = !isDark.value
    }) {
        Icon(
            painter = if (isDark.value) painterResource(light) else painterResource(dark),
            contentDescription = "Theme Switcher",
            tint = Color.White
        )
    }
}
