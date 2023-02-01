package com.lewie.base.common

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding

abstract class BaseVBActivity<VB : ViewBinding> : AppCompatActivity() {

    protected lateinit var binding: VB

    abstract fun genericViewBinding(): VB

    abstract fun onViewBound(savedInstanceState: Bundle?)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = genericViewBinding()
        setContentView(binding.root)
        onViewBound(savedInstanceState)
    }

    override fun onResume() {
        super.onResume()
    }
}