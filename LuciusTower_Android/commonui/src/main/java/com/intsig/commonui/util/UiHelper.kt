package com.intsig.commonui.util

import android.app.Activity
import com.intsig.commonui.util.statusbar.StatusBarHelper

/**
 * @author Lucius Ren
 * @since 2022/1/26 14:39
 *
 * UI 相关通用帮助类
 */
object UiHelper {
    /**
     * 当app的第一个页面，启动时，绑定了页面的时候，需要做的操作
     */
    fun onFirstActivityWindowFocusChangedTrue(activity: Activity) {
        // 1. 初始化statusBar高度计算
        StatusBarHelper.init(activity.window.decorView)
    }
}