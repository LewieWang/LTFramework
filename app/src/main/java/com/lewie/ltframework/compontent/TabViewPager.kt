package com.lewie.ltframework.compontent

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.google.accompanist.pager.*
import com.lewie.base.model.Data
import com.lewie.ltframework.MainVM
import com.lewie.ltframework.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch



@OptIn(ExperimentalPagerApi::class)
@SuppressLint("UnrememberedMutableState")
@Composable
fun TabViewPager(mViewModel: MainVM) {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        val pages by mutableStateOf(
            listOf("首页", "广场", "教程", "问答", "体系", "项目", "公众号")
        )
        val pagerState = rememberPagerState(initialPage = 0)//初始化页面，0就表示第一个页面
        ScrollableTabRow(
            selectedTabIndex = pagerState.currentPage,
            edgePadding = 0.dp, //消除左右间距
            // 使用提供的 pagerTabIndicatorOffset 修饰符自定义指示器
            indicator = { tabPositions ->
                TabRowDefaults.Indicator(
                    Modifier
                        .pagerTabIndicatorOffset(pagerState, tabPositions)
                        .width(8.dp),
                )
            },
            backgroundColor = colorResource(id = R.color.white),
            contentColor = colorResource(id = R.color.black)
        ) {
            pages.forEachIndexed { index, title ->
                Tab(
                    text = { Text(title) },
                    selected = pagerState.currentPage == index,//是否选中
                    onClick = {
                        CoroutineScope(Dispatchers.Main).launch {
                            pagerState.scrollToPage(index)
                        }
                    },
                    modifier = Modifier.alpha(0.9f),//透明度
                    enabled = true,//是否启用
                    selectedContentColor = colorResource(id = R.color.black),//选中的颜色
                    unselectedContentColor = colorResource(id = R.color.gray),//未选中的颜色
                )
            }
        }

        HorizontalPager(
            count = pages.size,
            state = pagerState,//用于控制或观察viewpage状态的状态对象。
            modifier = Modifier.padding(top = 4.dp),
            itemSpacing = 2.dp
        ) { page ->
            when (page) {
                0 -> mViewModel.result.collectAsState(initial = Data()).value?.datas?.let {
                    MessageList(it)
                }
                1 -> mViewModel.result.collectAsState(initial = Data()).value?.datas?.let {
                    MessageList(it)
                }
                2 -> mViewModel.result.collectAsState(initial = Data()).value?.datas?.let {
                    MessageList(it)
                }
                3 -> mViewModel.result.collectAsState(initial = Data()).value?.datas?.let {
                    MessageList(it)
                }
                4 -> mViewModel.result.collectAsState(initial = Data()).value?.datas?.let {
                    MessageList(it)
                }
                else -> {
                    Column(modifier = Modifier.fillMaxSize()) {
                        Text(
                            text = "Page: $page",
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                }
            }
        }
    }
}


