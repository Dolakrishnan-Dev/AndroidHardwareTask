package com.pavan.androidhardwaretask.presentation.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.pavan.androidhardwaretask.domain.data.AppListData
import com.pavan.androidhardwaretask.presentation.ui.widget.AppListWidget

@Composable
fun MainScreen(appListData: MutableState<AppListData>, padding: PaddingValues, onCheckboxChanged: (AppListData) -> Unit) {

    Column(
        modifier = Modifier
                .fillMaxSize()
                .background(color = Color.White)
                .padding(padding)
    ) {

        var showBottomSheet by remember {
            mutableStateOf(false)
        }

        Column(Modifier.weight(0.9f)) {
            AppListWidget(appListData) {
                onCheckboxChanged(it)
            }
        }

        Column(Modifier.weight(0.1f)) {
            Row(horizontalArrangement = Arrangement.End, modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)) {
                FloatingActionButton(onClick = { showBottomSheet = showBottomSheet.not() }) {
                    Icon(Icons.Default.Add, contentDescription = "Add Icon")
                }
            }
        }

        if (showBottomSheet) {
            BottomSheetScreenComposable(
                appListData,
                onCheckboxChanged = { onCheckboxChanged(it)},
                action = {
                    showBottomSheet = showBottomSheet.not()
                })
        }


    }

}
