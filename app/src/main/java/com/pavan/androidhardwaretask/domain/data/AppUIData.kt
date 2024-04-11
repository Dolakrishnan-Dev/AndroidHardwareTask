package com.pavan.androidhardwaretask.domain.data

import android.graphics.drawable.Drawable

/**
 * [AppUIData]
 *
 * @property name[String] The AppName
 * @property isChecked [Boolean] checkes Whether the App is Selected
 * @property icon [Int] AppIcon
 * */
data class AppUIData(
    val name: String,
    var isChecked: Boolean = false,
    val icon: Int?,
    val logo: Drawable?,
    val packageName: String?,
    val isSocialContent: Boolean = false
)


data class AppListData(
    val suggestedApps: MutableList<AppUIData> = mutableListOf(),
    val others: MutableList<AppUIData> = mutableListOf(),
    val isChanged: Int = 0
)
