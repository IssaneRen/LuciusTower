package com.lucius.luciustower.settings

import android.view.LayoutInflater
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.intsig.commonbase.baseclass.BaseLuciusFragment
import com.lucius.luciustower.databinding.FragmentSettingsBinding
import com.lucius.luciustower.homepage.MainActivity
import com.lucius.luciustower.homepage.MainModel
import com.lucius.luciustower.homepage.MainViewModel

/**
 * @author Lucius Ren
 * @since 2022/1/29 16:55
 *
 * 首页Tab4 设置页面
 */
class SettingsFragment : BaseLuciusFragment() {
    companion object {
        private const val TAG = "SettingsFragment"

        @JvmStatic
        fun newInstance() = SettingsFragment()
    }

    /**
     * 和activity公用viewModel
     */
    private val mActivityViewModel by lazy {
        ViewModelProvider(activity as MainActivity)[MainViewModel::class.java]
    }

    private lateinit var mViewBinding: FragmentSettingsBinding

    override fun initData() {
    }

    override fun inflateFragmentView(inflater: LayoutInflater): View {
        mViewBinding = FragmentSettingsBinding.inflate(inflater)
        return mViewBinding.root
    }

    override fun initView(view: View) {
    }

    override fun onFragmentVisible() {
        // 更新首页标题
        mActivityViewModel.liveDataForToolbarTitle.postValue("设置")
        // 更新右上角按钮icon
        mActivityViewModel.liveDataForToolbarIconRight1.postValue(
            MainModel.MenuIconModel(0, View.GONE, null)
        )
    }
}