package com.lewie.ltframework

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.lewie.base.model.Data
import com.lewie.base.model.DataX
import com.lewie.ltframework.ui.theme.LTFrameworkTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LTFrameworkTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val mViewModel: MainVM by viewModels()
                    InitData(mViewModel)
                }
            }
        }

    }
}


@Composable
fun InitData(mainVm: MainVM) {
    mainVm.result.collectAsState(initial = Data()).value?.datas?.let {
        MessageList(it)
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
                    text = "$index. ${data.title}",
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

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    LTFrameworkTheme {

    }
}