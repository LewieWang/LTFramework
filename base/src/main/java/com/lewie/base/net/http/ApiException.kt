package com.lewie.base.net.http

class  ApiException  (val code:Int?, private val msg:String):Exception(msg) {



}