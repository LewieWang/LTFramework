package com.lewie.ltframework

import android.content.Intent
import android.os.Bundle
import com.lewie.base.common.BaseVBActivity
import com.lewie.ltframework.databinding.ActivityHomeBinding
import com.lewie.video.VideoMainActivity

class HomeActivity : BaseVBActivity<ActivityHomeBinding>() {

    override fun genericViewBinding(): ActivityHomeBinding =
        ActivityHomeBinding.inflate(layoutInflater)

    override fun onViewBound(savedInstanceState: Bundle?) {
        binding.button.setOnClickListener {
            startActivity(Intent(this,VideoMainActivity::class.java))
        }
    }

}