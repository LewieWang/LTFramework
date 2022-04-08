package com.lewie.base

import androidx.lifecycle.*
import com.lewie.base.util.log

open class LifeViewModel : ViewModel(), LifecycleEventObserver {

    override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {
        when (event) {
            Lifecycle.Event.ON_CREATE -> log("===OnLifecycleEvent==ON_CREATE==>")
            Lifecycle.Event.ON_START -> log("===OnLifecycleEvent==ON_START==>")
            Lifecycle.Event.ON_RESUME -> log("===OnLifecycleEvent==ON_RESUME==>")
            Lifecycle.Event.ON_PAUSE -> log("===OnLifecycleEvent==ON_PAUSE==>")
            Lifecycle.Event.ON_STOP -> log("===OnLifecycleEvent==ON_STOP==>")
            Lifecycle.Event.ON_DESTROY -> log("===OnLifecycleEvent==ON_DESTROY==>")
            else -> {
                log("===OnLifecycleEvent==ON_ANY==>")
            }
        }
    }
}