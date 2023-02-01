package com.lewie.ltframework

import android.os.Bundle
import com.lewie.base.common.BaseVBActivity
import com.lewie.ltframework.databinding.ActivityVideoMainBinding

class VideoMainActivity : BaseVBActivity<ActivityVideoMainBinding>() {

    override fun genericViewBinding(): ActivityVideoMainBinding =
        ActivityVideoMainBinding.inflate(layoutInflater)

    override fun onViewBound(savedInstanceState: Bundle?) {

    }

}