package com.lucius.luciustower.homepage

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.google.android.material.tabs.TabLayoutMediator
import com.lucius.luciustower.R
import com.lucius.luciustower.databinding.ActivityMainBinding
import com.lucius.luciustower.databinding.ItemHomePageTabBinding
import com.lucius.luciustower.person.PersonListFragment

class MainActivity : AppCompatActivity() {
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
        initView()
    }

    private fun initView() {
        val adapter = MainPageAdapter(this)
        mBinding.vpMainPage.adapter = adapter
        TabLayoutMediator(mBinding.tabBottom, mBinding.vpMainPage) { tab, position->
            val binding = ItemHomePageTabBinding.inflate(layoutInflater)
            binding.mainBottomTabText.text = arrayForTabName[position]
            binding.aivIcon.setImageResource(arrayForIconId[position])
            tab.customView = binding.root
        }.attach()
        mBinding.tabBottom.selectTab(mBinding.tabBottom.getTabAt(2))
    }

    class MainPageAdapter(activity: FragmentActivity): FragmentStateAdapter(activity) {
        override fun getItemCount(): Int = 4

        override fun createFragment(position: Int): Fragment {
            return PersonListFragment.newInstance()
        }
    }
}
