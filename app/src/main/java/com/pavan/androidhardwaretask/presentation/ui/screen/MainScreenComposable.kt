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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pavan.androidhardwaretask.domain.data.AppListData
import com.pavan.androidhardwaretask.domain.data.AppUIData
import com.pavan.androidhardwaretask.presentation.ui.widget.AppListWidget
import kotlin.random.Random

@Composable
fun MainScreen(appListData: AppListData, padding: PaddingValues) {

    var localAppListData by remember {
        mutableStateOf(appListData)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
            .padding(padding)
    ) {

        var showBottomSheet by remember {
            mutableStateOf(false)
        }

        AppListWidget(localAppListData) {
            localAppListData = it
        }

        Row(horizontalArrangement = Arrangement.End, modifier = Modifier.fillMaxWidth().padding(10.dp)) {
            FloatingActionButton(onClick = { showBottomSheet = showBottomSheet.not() }) {
                Icon(Icons.Default.Add, contentDescription = "Add Icon")
            }
        }

        if (showBottomSheet) {
            BottomSheetScreenComposable(
                localAppListData,
                onCheckboxChanged = {localAppListData = it},
                action = {
                    showBottomSheet = showBottomSheet.not()
                    val tempData = localAppListData.copy(isChanged = Random.nextInt())

                    localAppListData = tempData
                })
        }


    }

}


@Preview(showBackground = true)
@Composable
fun PreviewMainScreen() {

    val appListData = AppListData(
        listOf(
            AppUIData("Instagram", false, null, null, "Instagram"),
            AppUIData("FaceBook", false, null, null, "FaceBook"),
        ), listOf(
            AppUIData("WhatsApp", false, null, null, "WhatsApp")
        ),0
    )

    MainScreen(appListData = appListData, padding = PaddingValues(7.dp))
}