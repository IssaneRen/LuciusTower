package com.intsig.commonbase.backfragment

import androidx.fragment.app.Fragment

/**
 * @author Lucius Ren
 * @since 2022/1/26 15:35
 *
 * 参考github项目中实现的fragment； 但是摒弃了复杂的接口实现。因为项目中不会出现非此子类的任何fragment，不需要考虑多继承问题
 */
abstract class BackPressHandleFragment: Fragment() {
    /**
     * @return 判断，该fragment是否已经处理了返回按钮的点击
     */
    fun onBackPressed(): Boolean {
        return interceptBackPressed() || handleBackPress(this)
    }

    /**
     * 拦截返回，if 需要拦截返回，重写此方法并返回true
     */
    open fun interceptBackPressed(): Boolean {
        return false
    }
}
