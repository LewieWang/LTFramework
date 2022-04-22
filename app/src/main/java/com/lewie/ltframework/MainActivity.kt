package com.lewie.ltframework

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.lifecycle.whenStarted
import com.lewie.base.model.Data
import com.lewie.base.model.DataX
import com.lewie.base.repository.ListRepository
import com.lewie.ltframework.ui.theme.LTFrameworkTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LTFrameworkTheme {
                Surface(modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background) {
                    val mViewModel : MainVM by viewModels()
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
    LazyColumn {
        itemsIndexed(items = messages) { index, item -> //遍历内容和索引
            Text(text = "$index${item.desc}")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    LTFrameworkTheme {

    }
}