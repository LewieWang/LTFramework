package com.lewie.base.util

import android.content.res.Resources
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.GradientDrawable
import android.util.Log
import android.util.TypedValue
import android.view.View
import android.widget.Toast
import com.lewie.base.common.ext.no
import com.lewie.base.common.ext.otherwise

fun log(msg: String) {
    Log.e("log", msg);
}


fun toast(string: String) {
//    Toast.makeText(BaseApp.myApp, string, Toast.LENGTH_SHORT).show()
}

/**
 * 一般结合takeIf使用
 * @param content String?
 * @param notShow Boolean
 * @return Boolean
 */
fun takeToast(content: String?, notShow: Boolean): Boolean {
    notShow.no {
        toast(content ?: "null")
    }

    return notShow
}

val Float.dp
    get() = TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP,
        this,
        Resources.getSystem().displayMetrics
    )
val Float.intDp
    get() = TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP,
        this,
        Resources.getSystem().displayMetrics
    ).toInt()
val Int.dp
    get() = TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP,
        this.toFloat(),
        Resources.getSystem().displayMetrics
    ).toInt()
