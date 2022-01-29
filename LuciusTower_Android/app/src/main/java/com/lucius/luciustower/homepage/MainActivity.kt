package com.lucius.luciustower.homepage

import android.graphics.Color
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.google.android.material.tabs.TabLayoutMediator
import com.intsig.commonbase.baseclass.BaseLuciusActivity
import com.intsig.commonui.util.UiHelper
import com.intsig.commonui.util.statusbar.setStatusBarColor
import com.lucius.luciustower.R
import com.lucius.luciustower.databinding.ActivityMainBinding
import com.lucius.luciustower.databinding.ItemHomePageTabBinding
import com.lucius.luciustower.guide.GuideHomeFragment
import com.lucius.luciustower.person.PersonListFragment

class MainActivity : BaseLuciusActivity() {
    companion object {
        private const val TAG = "MainActivity"

        private val arrayForTabName = arrayOf(
            "主页",
            "通讯录",
            "发现",
            "我",
        )

        private val arrayForIconId = arrayOf(
            R.drawable.ic_female_symbol,
            R.drawable.ic_male_symbol,
            R.drawable.ic_launcher_foreground,
            R.drawable.ic_unknown_filled
        )
    }

    private lateinit var mBinding: ActivityMainBinding

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        if (hasFocus) {
            UiHelper.onFirstActivityWindowFocusChangedTrue(this)
        }
    }

    override fun initData() {

    }

    override fun initView() {
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
        val adapter = MainPageAdapter(this)
        mBinding.vpMainPage.adapter = adapter
        TabLayoutMediator(mBinding.tabBottom, mBinding.vpMainPage, true, false) { tab, position->
            val binding = ItemHomePageTabBinding.inflate(layoutInflater)
            binding.mainBottomTabText.text = arrayForTabName[position]
            binding.aivIcon.setImageResource(arrayForIconId[position])
            tab.customView = binding.root
        }.attach()

        setStatusBarColor(this,
            fullScreen = true,
            blackFontColor = true,
            statusColorInt = Color.parseColor("#00000000")
        )
    }

    class MainPageAdapter(activity: FragmentActivity): FragmentStateAdapter(activity) {
        private val mFragmentList = arrayListOf<Fragment>()

        init {
            mFragmentList.add(PersonListFragment.newInstance())
            mFragmentList.add(GuideHomeFragment.newInstance())
            mFragmentList.add(GuideHomeFragment.newInstance())
            mFragmentList.add(GuideHomeFragment.newInstance())
        }

        override fun getItemCount(): Int = mFragmentList.size

        override fun createFragment(position: Int): Fragment {
            return mFragmentList[position]
        }
    }
}
