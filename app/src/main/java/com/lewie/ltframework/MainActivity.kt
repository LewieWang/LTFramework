package com.lewie.ltframework

import android.os.Bundle
import android.view.View
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.whenStarted
import com.lewie.base.model.DataX
import com.lewie.ltframework.ui.theme.LTFrameworkTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {

    val mainVm = MainVM()
    var content = "Android"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LTFrameworkTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    Greeting(content)
                }
            }
        }

        lifecycleScope.launch {
            lifecycle.whenStarted {
                mainVm.stateFlow.collect {
                      if(!it.datas.isNullOrEmpty()){

                      }


                }
            }

        }
        mainVm.funFlowRequest()

    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Composable
fun messageList(messages: List<DataX>) {// 值可以是 List或者Array两种方式
    LazyColumn {
        itemsIndexed(items = messages){ index, item -> //遍历内容和索引
            Text(text = "$index${item.desc}")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    LTFrameworkTheme {
        Greeting("Android")
    }
}