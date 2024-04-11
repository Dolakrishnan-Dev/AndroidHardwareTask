package com.pavan.androidhardwaretask.presentation.ui.widget

import android.util.Log
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pavan.androidhardwaretask.domain.data.AppListData
import com.pavan.androidhardwaretask.domain.data.AppUIData

@Composable
fun ColumnScope.AppListWidget(appListData: MutableState<AppListData>, onCheckboxChanged: (AppListData) -> Unit) {

    LazyColumn(modifier = Modifier
        .weight(1f)
        .padding(5.dp)) {

        item {

            Text(text = "Suggested Apps",Modifier.fillMaxWidth().padding(horizontal = 6.dp), fontSize = 20.sp)

        }
        items(
            appListData.value.suggestedApps,
            key = { item: AppUIData -> item.packageName.orEmpty() }) { localAppUIData ->
            AppUIWidget(appUIData = localAppUIData, appListData.value) { onCheckboxChanged(it) }
        }

        item {

            Text(text = "Other Apps",Modifier.fillMaxWidth().padding(horizontal = 6.dp), fontSize = 20.sp)

        }

        items(
            appListData.value.others,
            key = { item: AppUIData -> item.packageName.orEmpty() }) { localAppUIData ->
            AppUIWidget(appUIData = localAppUIData, appListData.value, onCheckboxChanged)
        }
    }
}