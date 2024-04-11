package com.pavan.androidhardwaretask.presentation.ui.widget

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.pavan.androidhardwaretask.domain.data.AppListData
import com.pavan.androidhardwaretask.domain.data.AppUIData

@Composable
fun AppUIWidget(
    appUIData: AppUIData,
    appListData: AppListData,
    onCheckboxChanged: (AppListData) -> Unit,
) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        appUIData.logo?.let {

            AsyncImage(
                model = it,
                contentDescription = "AppIcin",
                Modifier
                    .size(40.dp)
                    .padding(6.dp)
            )

        }

        Text(
            text = appUIData.name,
            fontSize = 20.sp,
            color = Color.Black,
            modifier = Modifier.weight(1f)
        )

        Checkbox(checked = appUIData.isChecked, onCheckedChange = {
            val suggestedApps = appListData.suggestedApps.map { item ->
                if (item.name == appUIData.name) {
                    item.copy(isChecked = item.isChecked.not())
                } else {
                    item
                }
            }.toMutableList()

            val otherApps = appListData.others.map { item ->
                if (item.name == appUIData.name) {
                    item.copy(isChecked = it)
                } else {
                    item
                }
            }.toMutableList()
            val updatedListData = AppListData(suggestedApps, otherApps)
            onCheckboxChanged(updatedListData)
        })


    }

}