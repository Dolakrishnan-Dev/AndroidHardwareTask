package com.pavan.androidhardwaretask

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.pavan.androidhardwaretask.domain.data.AppListData
import com.pavan.androidhardwaretask.domain.data.AppUIData
import com.pavan.androidhardwaretask.domain.others.AppName
import com.pavan.androidhardwaretask.presentation.ui.screen.MainScreen
import com.pavan.androidhardwaretask.presentation.ui.widget.BottomNavItem
import com.pavan.androidhardwaretask.presentation.ui.widget.BottomNavigation
import com.pavan.androidhardwaretask.ui.theme.AndroidHardwareTaskTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AndroidHardwareTaskTheme {
                var screen by remember {
                    mutableStateOf<BottomNavItem>(BottomNavItem.APPS)
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
                            InstalledAppsList(padding)
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

    @Composable
    fun InstalledAppsList(padding: PaddingValues) {
        val appsList = mutableListOf<AppUIData>()
        val packageManager = LocalContext.current.packageManager
        val packageMetaData = packageManager.getInstalledApplications(0)
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
            appsList.add(appUIData)
        }
        val suggestedApps = appsList.filter { it.isSocialContent }
        val otherApps = appsList.filter { it.isSocialContent.not() }
        MainScreen(
            appListData = AppListData(suggestedApps = suggestedApps, others = otherApps),
            padding
        )
    }

}


@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    AndroidHardwareTaskTheme {
        Greeting("Android")
    }
}