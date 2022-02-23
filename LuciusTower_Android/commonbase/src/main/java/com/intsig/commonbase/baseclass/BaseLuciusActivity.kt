package com.intsig.commonbase.baseclass

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.intsig.commonbase.backfragment.handleBackPress

/**
 * @author Lucius Ren
 * @since 2022/1/26 15:08
 *
 * 项目中所有activity基类 暂时没有复杂逻辑，但是全局集成，保证业务整体可修改
 */
abstract class BaseLuciusActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        intent?.extras?.let { bundle ->
            initIntent(bundle)
        }
        initData()
        initView()
    }

    /**
     * 基类方法 step1： 如若有跳转传递进来的bundle，统一在这里处理. 因为有些页面无传递数据，所以非必须重写
     */
    open fun initIntent(bundle: Bundle) {}

    /**
     * 基类方法 step2： 初始化数据与逻辑，常用来和liveData获取绑定关系，并初始化viewModel
     */
    abstract fun initData()

    /**
     * 基类方法 step3： 初始化视图控件状态等
     */
    abstract fun initView()

    /**
     * 重写物理返回键触发拦截 - 判断fragment的拦截
     */
    override fun onNavigateUp(): Boolean {
        return handleBackPress(this)
    }

    protected fun replaceFragment(containerId: Int, fragment: Fragment, addToBackStack: Boolean) {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(containerId, fragment)
        if (addToBackStack) {
            fragmentTransaction.addToBackStack(fragment.javaClass.simpleName)
        }
        fragmentTransaction.commitAllowingStateLoss()
    }
}