package com.lucius.luciustower.explore

import android.view.LayoutInflater
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.blankj.utilcode.util.ToastUtils
import com.intsig.commonbase.baseclass.BaseLuciusFragment
import com.lucius.luciustower.R
import com.lucius.luciustower.databinding.FragmentExploreBinding
import com.lucius.luciustower.homepage.MainActivity
import com.lucius.luciustower.homepage.MainModel
import com.lucius.luciustower.homepage.MainViewModel

/**
 * @author Lucius Ren
 * @since 2022/1/29 17:32
 *
 * exploretab 首页的第三个tab 探索功能相关，是自由功能
 */
class ExploreFragment: BaseLuciusFragment() {
    companion object {
        private const val TAG = "ExploreFragment"

        fun newInstance() = ExploreFragment()
    }

    /**
     * 和activity公用viewModel
     */
    private val mActivityViewModel by lazy {
        ViewModelProvider(activity as MainActivity)[MainViewModel::class.java]
    }

    private lateinit var mViewBinding: FragmentExploreBinding

    override fun initData() {
    }

    override fun inflateFragmentView(inflater: LayoutInflater): View {
        mViewBinding = FragmentExploreBinding.inflate(inflater)
        return mViewBinding.root
    }

    override fun initView(view: View) {
    }

    override fun onFragmentVisible() {
        // 更新首页标题
        mActivityViewModel.liveDataForToolbarTitle.postValue("发现")
        // 更新右上角按钮icon
        mActivityViewModel.liveDataForToolbarIconRight1.postValue(
            MainModel.MenuIconModel(R.drawable.ic_lucius_ui_menu, View.VISIBLE) {
                ToastUtils.showShort("发现页菜单！")
            }
        )
    }
}