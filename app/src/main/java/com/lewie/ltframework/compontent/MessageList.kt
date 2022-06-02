package com.lewie.ltframework.compontent

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.lewie.base.common.PageConstant.WEB_VIEW_PAGE
import com.lewie.base.model.DataX
import com.lewie.base.util.encode
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

@SuppressLint("StaticFieldLeak")
lateinit var mNavController: NavHostController


@Composable
fun MessageList(messages: List<DataX>,navController: NavHostController) {// 值可以是 List或者Array两种方式
    mNavController =  navController

    LazyColumn(
        contentPadding = PaddingValues(10.dp, 10.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp),
        state = rememberLazyListState(),
    ) {

        itemsIndexed(items = messages) { index, item -> //遍历内容和索引
            MessageListItem(item, index)
//            Spacer(Modifier.size(10.dp))
        }
    }
}

@Composable
fun MessageListItem(data: DataX, index: Int) {// 值可以是 List或者Array两种方式
    Card(elevation = 4.dp, modifier = Modifier.clickable {

        mNavController.navigate("${WEB_VIEW_PAGE}/${data.title}/${data.link.encode()}")
    }) {
        Row(verticalAlignment = Alignment.Top, modifier = Modifier.padding(5.dp),) {
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

