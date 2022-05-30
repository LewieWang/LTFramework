package com.lewie.ltframework

import android.annotation.SuppressLint
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.lewie.base.common.PageConstant.HOME_ITEM
import com.lewie.base.model.Data
import com.lewie.base.model.DataX
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@OptIn(ExperimentalAnimationApi::class)
@Composable
fun HomePage(mNavController: NavHostController, mainVm: MainVM) {
    val navController = rememberAnimatedNavController()
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()
    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            //顶部应用栏
            val drawerState = scaffoldState.drawerState
            TopAppBar(
                backgroundColor = Color.White,
                title = {
                    Text(
                        text = stringResource(id = R.string.app_name),
                        modifier = Modifier.fillMaxWidth(),
                        color = Color.Black,
                        overflow = TextOverflow.Ellipsis, //超出省略
                        maxLines = 1, //单行显示
                        textAlign = TextAlign.Center
                    )
                },
                navigationIcon = {
                    IconButton(onClick = {
                        scope.launch {
                            if (drawerState.isClosed) drawerState.open() else drawerState.close()
                        }
                    }) {
                        Icon(Icons.Default.Menu, contentDescription = "Menu")
                    }
                },
                actions = {
                    IconButton(onClick = {

                    }) {
                        Icon(Icons.Default.Home, contentDescription = "疫情")
                    }
                }
            )
        },
        modifier = Modifier.fillMaxSize(),
        drawerContent = {  },
        bottomBar = {

        }
    ) {
        AnimatedNavHost(
            navController = navController,
            startDestination = HOME_ITEM,
            enterTransition = {
                slideInHorizontally(
                    initialOffsetX = { fullWidth -> fullWidth },
                    animationSpec = tween(500)
                )
            },
            exitTransition = {
                slideOutHorizontally(
                    targetOffsetX = { fullWidth -> -fullWidth },
                    animationSpec = tween(500)
                )
            },
            popEnterTransition = {
                slideInHorizontally(
                    initialOffsetX = { fullWidth -> -fullWidth },
                    animationSpec = tween(500)
                )
            },
            popExitTransition = {
                slideOutHorizontally(
                    targetOffsetX = { fullWidth -> fullWidth },
                    animationSpec = tween(500)
                )
            }
        ) {
            composable(HOME_ITEM) {
                mainVm.result.collectAsState(initial = Data()).value?.datas?.let {
                    MessageList(it)
                }
            }
//            composable(COLLECTION_ITEM) {
//                CollectionItem(mNavController, homeViewModel)
//            }
        }
    }

}

@Composable
fun MessageList(messages: List<DataX>) {// 值可以是 List或者Array两种方式
    LazyColumn(
        contentPadding = PaddingValues(10.dp, 10.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {

        itemsIndexed(items = messages) { index, item -> //遍历内容和索引
            MessageListItem(item, index)
//            Spacer(Modifier.size(10.dp))
        }
    }
}

@Composable
fun MessageListItem(data: DataX, index: Int) {// 值可以是 List或者Array两种方式
    Card(elevation = 4.dp) {
        Row(verticalAlignment = Alignment.Top, modifier = Modifier.padding(5.dp)) {
            AsyncImage(
                model = "https://pic.centanet.com/shenzhen/postimage/xinfang/20200806/b1bbe21e0a3fcfc8071654dcf3ec419d.jpg",
                contentDescription = null,
                modifier = Modifier.size(130.dp, 80.dp)
            )
            Column(modifier = Modifier.fillMaxSize()) {
                Text(
                    text = "${index + 1}.${data.title}",
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "作者：${data.author}",
                    maxLines = 1,
                    fontSize = 12.sp
                )
            }

        }
    }

}

