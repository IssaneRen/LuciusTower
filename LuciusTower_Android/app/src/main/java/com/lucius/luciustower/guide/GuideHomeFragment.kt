package com.lucius.luciustower.guide

import android.view.LayoutInflater
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.blankj.utilcode.util.ToastUtils
import com.intsig.commonbase.baseclass.BaseLuciusFragment
import com.lucius.luciustower.R
import com.lucius.luciustower.databinding.FragmentGuideHomeBinding
import com.lucius.luciustower.homepage.MainActivity
import com.lucius.luciustower.homepage.MainModel
import com.lucius.luciustower.homepage.MainViewModel

/**
 * @author Lucius Ren
 * @since 2022/1/27 15:17
 * 
 * guide页面的外层页 主要是用于首页切换tab的第二个页面
 */
class GuideHomeFragment: BaseLuciusFragment() {
    companion object {
        private const val TAG = "GuideHomeFragment"

        @JvmStatic
        fun newInstance() = GuideHomeFragment().apply {
//                arguments = Bundle().apply {
//                    putString(ARG_PARAM1, param1)
//                    putString(ARG_PARAM2, param2)
//                }
        }
    }

    private lateinit var mBinding: FragmentGuideHomeBinding

    /**
     * 和activity公用viewModel
     */
    private val mActivityViewModel by lazy {
        ViewModelProvider(activity as MainActivity)[MainViewModel::class.java]
    }

    override fun initData() {
    }

    override fun inflateFragmentView(inflater: LayoutInflater): View {
        mBinding = FragmentGuideHomeBinding.inflate(inflater)
        return mBinding.root
    }

    override fun initView(view: View) {
    }

    override fun onFragmentVisible() {
        // 更新首页标题
        mActivityViewModel.liveDataForToolbarTitle.postValue("引导")
        // 更新右上角按钮icon
        mActivityViewModel.liveDataForToolbarIconRight1.postValue(
            MainModel.MenuIconModel(R.drawable.ic_lucius_ui_menu, View.VISIBLE) {
                ToastUtils.showShort("引导页菜单显示！")
            })
    }
}
