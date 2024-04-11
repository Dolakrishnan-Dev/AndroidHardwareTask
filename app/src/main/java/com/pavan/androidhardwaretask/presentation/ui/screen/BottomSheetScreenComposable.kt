package com.pavan.androidhardwaretask.presentation.ui.screen

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import com.pavan.androidhardwaretask.common.Action
import com.pavan.androidhardwaretask.domain.data.AppListData
import com.pavan.androidhardwaretask.presentation.ui.widget.AppListWidget

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomSheetScreenComposable(appListData: AppListData, action: Action, onCheckboxChanged: (AppListData) -> Unit) {

    val sheetState = rememberModalBottomSheetState()

    ModalBottomSheet(onDismissRequest = action, sheetState = sheetState) {
        AppListWidget(appListData,onCheckboxChanged)
    }


}