package com.intsig.commonui.util

import android.app.Activity
import android.os.Build
import android.view.*
import androidx.annotation.ColorInt
import androidx.annotation.RequiresApi
import androidx.core.view.ViewCompat
import com.blankj.utilcode.util.LogUtils
import java.lang.Exception

/**
 * @author Lucius Ren
 * @since 2022/1/25 19:37
 * 设置statusbar为透明
 */

private const val TAG = "StatusBarUtil"

/**
 * 设置状态栏颜色与模式
 */
fun setStatusBarColor(
    activity: Activity, fullScreen: Boolean, blackFontColor: Boolean,
    @ColorInt statusColorInt: Int
) {
    try {
        activity.window?.let { window ->
            // 1. 如果是android 11 以上，可以使用新的  WindowInsetsController Api替代旧的 setSystemUiVisibility(int)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                setStatusBarColorR(window, fullScreen, blackFontColor, statusColorInt)
                return
            }
            // 2. 如果是android 11以下，会使用旧的window获取flag的方式进行判断
            var flag = window.decorView.systemUiVisibility
            flag = when (fullScreen) {
                true -> flag or (View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LAYOUT_STABLE)
                else -> flag and View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN.inv()
            }
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                flag = if (blackFontColor) {
                    flag or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
                } else {
                    flag and View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR.inv()
                }
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            }
            window.decorView.systemUiVisibility = flag
            // 2. 如果小于R大于M(6.0)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                window.statusBarColor = statusColorInt
            }
        }
    } catch (e: Exception) {
        LogUtils.eTag(TAG, e)
    }
}

@RequiresApi(api = Build.VERSION_CODES.R)
private fun setStatusBarColorR(
    window: Window, fullScreen: Boolean, blackFontColor: Boolean, @ColorInt statusColorInt: Int
) {
    val controller = ViewCompat.getWindowInsetsController(window.decorView)
    window.setDecorFitsSystemWindows(!fullScreen)
    controller?.isAppearanceLightStatusBars = blackFontColor
    window.statusBarColor = statusColorInt
}
// 参考：https://juejin.cn/post/6940048488071856164
//        var controller = ViewCompat.getWindowInsetsController(window.decorView)
//        // 设置状态栏反色
//        controller?.isAppearanceLightStatusBars = true
//        // 取消状态栏反色
//        controller?.isAppearanceLightStatusBars = false
//        // 设置导航栏反色
//        controller?.isAppearanceLightNavigationBars = true
//        // 取消导航栏反色
//        controller?.isAppearanceLightNavigationBars = false
//        // 隐藏状态栏
//        controller?.hide(WindowInsets.Type.statusBars())
//        // 显示状态栏
//        controller?.show(WindowInsets.Type.statusBars())
//        // 隐藏导航栏
//        controller?.hide(WindowInsets.Type.navigationBars())
//        // 显示导航栏
//        controller?.show(WindowInsets.Type.navigationBars())
//        // 同时隐藏状态栏和导航栏
//        controller?.hide(WindowInsets.Type.systemBars())
//        // 同时隐藏状态栏和导航栏
//        controller?.show(WindowInsets.Type.systemBars())
