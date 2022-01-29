package com.lucius.luciustower.guide

import android.view.LayoutInflater
import android.view.View
import com.intsig.commonbase.baseclass.BaseLuciusFragment
import com.lucius.luciustower.databinding.FragmentGuideHomeBinding

/**
 * @author Lucius Ren
 * @since 2022/1/27 15:17
 * 
 * guide页面的外层页 主要是用于首页切换tab的第二个页面
 */
class GuideHomeFragment: BaseLuciusFragment() {
    companion object {
        private const val TAG = "GuideHomeFragment"
    }

    private lateinit var mBinding: FragmentGuideHomeBinding

    override fun initData() {
    }

    override fun inflateFragmentView(inflater: LayoutInflater): View {
        mBinding = FragmentGuideHomeBinding.inflate(inflater)
        return mBinding.root
    }

    override fun initView(view: View) {
        mBinding
    }
}
