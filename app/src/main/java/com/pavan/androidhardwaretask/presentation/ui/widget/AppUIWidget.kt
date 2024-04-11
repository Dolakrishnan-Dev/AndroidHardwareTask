package com.pavan.androidhardwaretask.presentation.ui.widget

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.pavan.androidhardwaretask.domain.data.AppListData
import com.pavan.androidhardwaretask.domain.data.AppUIData

@Composable
fun AppUIWidget(appUIData: AppUIData,appListData: AppListData, onCheckboxChanged: (AppListData) -> Unit,) {

    var isChecked by remember {
        mutableStateOf(appUIData.isChecked)
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
    ) {

        appUIData.logo?.let {

            AsyncImage(model = it, contentDescription = "AppIcin",Modifier.size(36.dp).padding(6.dp))

        }

        Text(
            text = appUIData.name,
            fontSize = 20.sp,
            color = Color.Black,
            modifier = Modifier.weight(1f)
        )

        Checkbox(checked = isChecked, onCheckedChange = {
            appUIData.isChecked = appUIData.isChecked.not()
            isChecked = it
            val suggestedApps = appListData.suggestedApps.map { item ->
                if (item.packageName ==  appUIData.name) {
                    item.copy(isChecked = it)
                } else {
                    item
                }}

            val otherApps = appListData.others.map { item ->
                if (item.packageName ==  appUIData.name) {
                    item.copy(isChecked = it)
                } else {
                    item
                }}
            onCheckboxChanged(AppListData(suggestedApps,otherApps))

        })




    }

}