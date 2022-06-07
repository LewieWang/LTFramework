package com.lewie.ltframework.compontent

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.rememberPagerState
import com.lewie.base.model.BannerData
import com.lewie.ltframework.R

@OptIn(ExperimentalPagerApi::class)
@Composable
fun HomeBanner(list : List<BannerData>){
    Box(
        Modifier
            .height(180.dp)
            .fillMaxWidth()
            .padding(0.dp)) {
        val pagerState = rememberPagerState()

        // Display 10 items
        HorizontalPager(
            count = list.size,
            state = pagerState,
            // Add 32.dp horizontal padding to 'center' the pages
            contentPadding = PaddingValues(0.dp),
            modifier = Modifier
                .fillMaxWidth(),
        ) { page ->
            PagerItem(
                page = page,
                data = list[page],
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth()
//                    .aspectRatio(1f)
            )
        }

        HorizontalPagerIndicator(
            pagerState = pagerState,
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp),
            activeColor = Color.White
        )

//        ActionsRow(
//            pagerState = pagerState,
//            modifier = Modifier.align(Alignment.CenterHorizontally)
//        )
    }
}


@Composable
internal fun PagerItem(
    page: Int,
    data: BannerData,
    modifier: Modifier = Modifier,
) {
    Box(modifier) {
        // Our page content, displaying a random image
        AsyncImage(
            model = data.imagePath,
            contentDescription = data.desc,
            modifier = Modifier.matchParentSize()
        )

        // Displays the page index
        Text(
            text = data.desc,
            color = Color.White,
            modifier = Modifier
                .align(Alignment.BottomStart)
//                .padding(16.dp)
                .background(colorResource(id = R.color.black_transfer))
//                .sizeIn(minWidth = 40.dp, minHeight = 40.dp)
                .padding(8.dp)
                .wrapContentSize(Alignment.Center)
                .fillMaxWidth()

        )
    }
}