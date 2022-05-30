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
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.lewie.base.common.PageConstant
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
                            val mViewModel: MainVM by viewModels()
                            HomePage(navController,mViewModel)
                        }
                        composable("friendslist") { }
                        /*...*/
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

