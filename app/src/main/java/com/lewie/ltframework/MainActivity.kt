package com.lewie.ltframework

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.lewie.base.common.PageConstant
import com.lewie.ltframework.page.HomePage
import com.lewie.ltframework.page.WebViewPage
import com.lewie.ltframework.ui.theme.LTFrameworkTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @ExperimentalAnimationApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            // Remember a SystemUiController
            val systemUiController = rememberSystemUiController()
            val useDarkIcons = MaterialTheme.colors.isLight
            val navController = rememberNavController()
            val mViewModel: MainVM by viewModels()

            SideEffect {
                // Update all of the system bar colors to be transparent, and use
                // dark icons if we're in light theme
                systemUiController.setSystemBarsColor(
                    color = Color.Transparent,
                    darkIcons = useDarkIcons

                )
                // setStatusBarsColor() and setNavigationBarColor() also exist
            }
            LTFrameworkTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    NavHost(
                        navController = navController,
                        startDestination = PageConstant.HOME_PAGE,
                       ) {
                        composable(PageConstant.HOME_PAGE) {
                            HomePage(navController,mViewModel)
                        }
                        composable(
                            "${PageConstant.WEB_VIEW_PAGE}/{title}/{url}",
                            arguments = listOf(
                                navArgument("title") {
                                    type = NavType.StringType
                                },
                                navArgument("url") {
                                    type = NavType.StringType
                                },
                            )
                        ) {
                            val title = it.arguments?.getString("title") ?: "WebView页面"
                            val url = it.arguments?.getString("url") ?: "WebViewUrl"
                            WebViewPage(navController, title, url)
                        }

                    }

                }
            }
        }

    }
}

//
//@Composable
//fun FriendsList(navController: NavController) {
//    /*...*/
//    Button(onClick = { navController.navigate("mainFragment") }) {
//        Text(text = "friendslist")
//    }
//    /*...*/
//}

