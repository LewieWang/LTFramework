package com.lewie.video

import android.os.Bundle
import android.widget.Toast
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import com.lewie.base.common.BaseVBActivity
import com.lewie.base.common.createVbStableAdapter
import com.lewie.base.common.vb.VbStableAdapter
import com.lewie.video.databinding.ActivityVideoMainBinding


class VideoMainActivity : BaseVBActivity<ActivityVideoMainBinding>() {

    private lateinit var mAdapter: VbStableAdapter
    private val data = mutableListOf<VideoItemCell>()
    override fun genericViewBinding(): ActivityVideoMainBinding =
        ActivityVideoMainBinding.inflate(layoutInflater)

    override fun onViewBound(savedInstanceState: Bundle?) {

        mAdapter = createVbStableAdapter {

        }
        //切换动画
        val compositePageTransformer = CompositePageTransformer()
        compositePageTransformer.addTransformer(ScaleInTransformer())
        compositePageTransformer.addTransformer(MarginPageTransformer(40))


        binding.viewPager.apply {
                adapter = mAdapter
                registerOnPageChangeCallback(pageChangeCallback)
//                setPageTransformer(compositePageTransformer)
            }

        for (i in 0..10){
            data.add(VideoItemCell())
        }
        mAdapter.submitList(data)
    }

    private val pageChangeCallback = object : ViewPager2.OnPageChangeCallback(){
        override fun onPageSelected(position: Int) {
            super.onPageSelected(position)
            Toast.makeText(this@VideoMainActivity, "ccm== $position", Toast.LENGTH_SHORT).show()
        }
    }
}