package com.pavan.androidhardwaretask.presentation.ui.widget

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import coil.compose.AsyncImage

@Composable
fun BottomNavigation(action: (BottomNavItem) -> Unit) {

    val items = listOf(
        BottomNavItem.APPS, BottomNavItem.MENU
    )

    NavigationBar {
        items.forEach { item ->
            AddItem(
                screen = item,
                action
            )
        }
    }
}

@Composable
fun RowScope.AddItem(
    screen: BottomNavItem, action: (BottomNavItem) -> Unit
) {
    NavigationBarItem(
        // Text that shows bellow the icon
        label = {
            Text(text = screen.title)
        },

        // The icon resource
        icon = { Icon(imageVector = screen.icon, contentDescription = "") },
        // Display if the icon it is select or not
        selected = true,

        // Always show the label bellow the icon or not
        alwaysShowLabel = true,

        // Click listener for the icon
        onClick = { action(screen) },

        // Control all the colors of the icon
        colors = NavigationBarItemDefaults.colors()
    )
}

sealed class BottomNavItem(
    var title: String,
    var icon: ImageVector
) {
    data object APPS :
        BottomNavItem(
            "Apps",
            Icons.Default.Home
        )

    data object MENU :
        BottomNavItem(
            "Menu",
            Icons.Default.Menu
        )

}