package com.intsig.commonbase.backfragment

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.intsig.commonbase.baseclass.BaseLuciusActivity

/**
 * @author Lucius Ren
 * @since 2022/1/26 15:24
 *
 * 处理fragment间的回退拦截，不退出activity
 * 参考：https://github.com/ikidou/FragmentBackHandler
 */
// ----  start of static method

/**
 * 尝试将back事件分发给activity中包含的fragment
 */
fun handleBackPress(activity: BaseLuciusActivity): Boolean {
    return handleBackPress(activity.supportFragmentManager)
}

/**
 * 尝试将back事件分发给activity中包含的fragment
 */
internal fun handleBackPress(fragment: Fragment): Boolean {
    return handleBackPress(fragment.childFragmentManager)
}

/**
 * 传入fragmentManager，依次按子fragment处理back点击。
 * 如果所有的子fragment都没有处理back，则尝试 popBackStack
 */
private fun handleBackPress(fragmentManager: FragmentManager): Boolean {
    // 1. 首先尝试自己处理back点击
    fragmentManager.fragments.takeIf { it.isNotEmpty() }?.let { fragments->
        fragments.forEach { fg->
            if (isFragmentHandledBackPress(fg)) return@handleBackPress true
        }
    }
    // 2. 如果自己的点击没有人重写，那么执行back回退栈的pop
    if (fragmentManager.backStackEntryCount > 0) {
        fragmentManager.popBackStack()
        return true
    }
    // 3. 如果以上条件都不满足，说明没有任何拦截
    return false
}

/**
 * 判断当前的fragment是否处理了返回点击
 */
private fun isFragmentHandledBackPress(fragment: Fragment?): Boolean {
    (fragment as? BackPressHandleFragment)?.let {
        if (it.isVisible && it.userVisibleHint && it.onBackPressed()) return true
    }
    return false
}
