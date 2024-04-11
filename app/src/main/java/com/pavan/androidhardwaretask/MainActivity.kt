package com.pavan.androidhardwaretask

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pavan.androidhardwaretask.domain.data.AppListData
import com.pavan.androidhardwaretask.domain.data.AppUIData
import com.pavan.androidhardwaretask.domain.others.AppName
import com.pavan.androidhardwaretask.presentation.ui.screen.MainScreen
import com.pavan.androidhardwaretask.presentation.ui.widget.BottomNavItem
import com.pavan.androidhardwaretask.presentation.ui.widget.BottomNavigation
import com.pavan.androidhardwaretask.ui.theme.AndroidHardwareTaskTheme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AndroidHardwareTaskTheme {
                var screen by remember {
                    mutableStateOf<BottomNavItem>(BottomNavItem.APPS)
                }
                val appListData =  remember { mutableStateOf(AppListData()) }
                var isLoading by remember { mutableStateOf(true) }
                val packageManager = LocalContext.current.packageManager

                LaunchedEffect(key1 = Unit) {
                    // Perform data fetching in a background thread
                    withContext(Dispatchers.IO) {
                        val packageMetaData = packageManager.getInstalledApplications(0)
                        val appUIList = mutableListOf<AppUIData>()
                        packageMetaData.forEach { appInfo ->
                            val appName = packageManager.getApplicationLabel(appInfo).toString()
                            val content = listOf(
                                AppName.FACEBOOK,
                                AppName.INSTAGRAM,
                                AppName.WHATSAPP,
                                AppName.CHROME,
                                AppName.YOUTUBE
                            )
                            val isSocialContent = content.any { it.title == appName }

                            val appUIData = AppUIData(
                                name = appName,
                                isChecked = false,
                                icon = appInfo.icon,
                                logo = appInfo.loadIcon(packageManager),
                                packageName = appInfo.packageName,
                                isSocialContent = isSocialContent
                            )
                            appUIList.add(appUIData)
                        }
                        val suggestedApps = appUIList.filter { it.isSocialContent }
                        val otherApps = appUIList.filter { it.isSocialContent.not() }

                        withContext(Dispatchers.Main){
                            appListData.value = AppListData(suggestedApps = suggestedApps.toMutableList(), others = otherApps.toMutableList())
                            isLoading = false
                            Log.d("AppUIData",appListData.value.suggestedApps.toString())
                        }
                    }
                }
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Scaffold(bottomBar = {
                        BottomNavigation {
                            screen = it
                        }
                    }) { padding ->
                        if (screen == BottomNavItem.APPS) {
                            if (isLoading.not()) {
                                MainScreen(
                                    appListData = appListData, // Use appListData.value to access the current value
                                    padding = padding
                                ) {
                                    appListData.value = it
                                }
                            } else {
                                LoadScreen()
                            }
                        } else {
                            Column(
                                Modifier.fillMaxSize(),
                                verticalArrangement = Arrangement.Center,
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Text(text = "Menu Selected", fontSize = 20.sp, color = Color.Black)
                            }
                        }
                    }
                }
            }
        }
    }

}


@Composable
fun LoadScreen() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CircularProgressIndicator()
        Spacer(modifier = Modifier.height(16.dp))
        Text("Loading data...")
    }
}