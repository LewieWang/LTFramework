package com.lewie.ltframework.compontent

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.layoutId
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet


import coil.compose.AsyncImage
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState

import kotlinx.coroutines.launch
import java.util.*

/**
 * @param data 数据来源
 * @param onImagePath 设置图片的url
 * @param pagerModifier HorizontalPager的Modifier
 * @param ratio 图片宽高压缩比
 * @param contentScale 图片裁剪方式
 * @param isShowPagerIndicator 是否显示指示器
 * @param pagerIndicatorModifier 指示器Row的整个样式
 * @param activeColor 选中的指示器样式
 * @param inactiveColor 未选中的指示器样式
 * @param isLoopBanner 是否自动播放轮播图
 * @param loopDelay 任务执行前的延迟（毫秒）
 * @param loopPeriod 连续任务执行之间的时间（毫秒）。
 * @param horizontalArrangement 指示器Row中文本和指示器的排版样式
 * @param desc 文本内容
 * @param onBannerItemClick Banner的item点击事件
 */
@OptIn(ExperimentalPagerApi::class)
@Composable
fun <T> Banner(
    data: List<T>,
    onImagePath: (Int) -> String,
    pagerModifier: Modifier = Modifier,
    ratio: Float = 7 / 3f,
    contentScale: ContentScale = ContentScale.Crop,
    isShowPagerIndicator: Boolean = true,
    pagerIndicatorModifier: Modifier = Modifier,
    activeColor: Color = Color.White,
    inactiveColor: Color = Color(0xFF999999),
    isLoopBanner: Boolean = true,
    loopDelay: Long = 3000,
    loopPeriod: Long = 3000,
    horizontalArrangement: Arrangement.Horizontal = Arrangement.SpaceBetween,
    desc: @Composable ((Int) -> Unit)? = null,
    onBannerItemClick: ((Int) -> Unit)? = null,
) {
    val virtualCount = Int.MAX_VALUE

    val actualCount = data.size
    //初始图片下标
    val initialIndex = virtualCount / 2
    val pageState = rememberPagerState(initialPage = initialIndex)
    if (isLoopBanner) {
        val coroutineScope = rememberCoroutineScope()
        DisposableEffect(Unit) {
            val timer = Timer()
            timer.schedule(object : TimerTask() {
                override fun run() {
                    coroutineScope.launch {
                        pageState.animateScrollToPage(pageState.currentPage + 1)
                    }
                }

            }, loopDelay, loopPeriod)
            onDispose {
                timer.cancel()
            }
        }
    }
    val constraintSet = ConstraintSet {
        val image = createRefFor("image")
        val content = createRefFor("content")
        constrain(image) {
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        }
        constrain(content) {
            bottom.linkTo(image.bottom)
            start.linkTo(image.start)
        }
    }
    HorizontalPager(count = virtualCount,
        state = pageState,
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .then(pagerModifier)) { index ->
        val actualIndex = (index - initialIndex).floorMod(actualCount)
        ConstraintLayout(constraintSet = constraintSet) {
            AsyncImage(
                model = onImagePath(actualIndex),
                contentDescription = null,
                modifier = Modifier
                    .layoutId("image")
                    .aspectRatio(ratio)
                    .clickable {
                        onBannerItemClick?.invoke(actualIndex)
                    },
                contentScale = contentScale,
            )
            if (isShowPagerIndicator) {
                Row(Modifier
                    .layoutId("content")
                    .fillMaxWidth()
                    .then(pagerIndicatorModifier),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = horizontalArrangement
                ) {
                    desc?.invoke(actualIndex)
                    HorizontalPagerIndicator(
                        pagerState = pageState,
                        activeColor = activeColor,
                        inactiveColor = inactiveColor,
                        count = actualCount
                    )
                }

            }
        }
    }
}


fun Int.floorMod(other: Int): Int = when (other) {
    0 -> this
    else -> this - floorDiv(other = other) * other
}