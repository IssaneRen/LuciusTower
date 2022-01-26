package com.intsig.commonui.util.statusbar

import android.annotation.TargetApi
import android.app.Activity
import android.os.Build
import android.view.View
import android.view.Window
import android.view.WindowInsets
import android.view.WindowManager
import androidx.annotation.ColorInt
import androidx.annotation.RequiresApi
import androidx.core.view.ViewCompat
import com.blankj.utilcode.util.LogUtils
import com.intsig.commonbase.AppLifecycleUtil
import java.lang.Exception

/**
 * @author Lucius Ren
 * @since 2022/1/26 12:06
 *
 * 计算statusBar高度的内容，方式
 */

object StatusBarHelper {
    /**
     * 记录当前计算出来的statusBar高度
     */
    private var mStatusBarHeight = 0

    private const val DEFAULT_MAX_STATUS_BAR_HEIGHT = 120
    private const val DEFAULT_MIN_STATUS_BAR_HEIGHT = 20

    /**
     * 默认的status bar 高度，只有异常了，没获取到状态栏高度的时候才会使用这个
     */
    private const val DEFAULT_STATUS_BAR_HEIGHT = 24


    /**
     * 获取当前的的状态栏的高度， 有默认值，如果调用此方法会刷新一次获取，并覆盖之前保存的值
     */
    fun getStatusBarHeight(): Int {
        if (mStatusBarHeight > 0) {
            return mStatusBarHeight
        }
        val resourceId = AppLifecycleUtil.sContext.resources.getIdentifier(
            "status_bar_height",
            "dimen", "android"
        )
        var statusBarHeight = 0
        if (resourceId > 0) {
            statusBarHeight = AppLifecycleUtil.sContext.resources.getDimensionPixelSize(resourceId)
        }
        return if (statusBarHeight > 0) statusBarHeight else DEFAULT_STATUS_BAR_HEIGHT.dp.also {
            mStatusBarHeight = it
        }
    }

    /**
     * 初始化帮助类
     *
     * @param decorView 非沉浸式的页面DecorView
     * @return 当前帮助类
     */
    fun init(decorView: View): StatusBarHelper {
        LogUtils.dTag(
            TAG,
            "init version: " + Build.VERSION.SDK_INT + "  | height: " + mStatusBarHeight
        )
        // if height is not correct get data again
        if (mStatusBarHeight in DEFAULT_MIN_STATUS_BAR_HEIGHT until DEFAULT_MAX_STATUS_BAR_HEIGHT) {
            return this
        }
        //default value
        mStatusBarHeight = getStatusBarHeight()
        // for p above
        if (Build.VERSION.SDK_INT >= 27) {
            decorView.setOnApplyWindowInsetsListener(InnerApplyWindowInsetsListener())
        }
        return this
    }

    @TargetApi(Build.VERSION_CODES.KITKAT_WATCH)
    private class InnerApplyWindowInsetsListener : View.OnApplyWindowInsetsListener {
        override fun onApplyWindowInsets(v: View, insets: WindowInsets): WindowInsets {
            v.post {
                LogUtils.dTag(
                    TAG,
                    "view post begin run mStatusBarHeight: $mStatusBarHeight"
                )
                val contentView = v.findViewById<View>(android.R.id.content)
                val locations = IntArray(2)
                contentView.getLocationOnScreen(locations)
                LogUtils.dTag(TAG, "view post location :${locations.contentToString()}")
                val viewTop = locations[1]

                //insure the viewTop is correct
                if (viewTop in DEFAULT_MIN_STATUS_BAR_HEIGHT until DEFAULT_MAX_STATUS_BAR_HEIGHT) {
                    mStatusBarHeight = viewTop
                } else {
                    LogUtils.dTag(TAG, "get status bar height fail!")
                }

                //check data again
                mStatusBarHeight = mStatusBarHeight
                    .coerceAtLeast(DEFAULT_MIN_STATUS_BAR_HEIGHT)
                    .coerceAtMost(DEFAULT_MAX_STATUS_BAR_HEIGHT)
            }
            return insets
        }
    }
}

private const val TAG = "StatusBarHelper"

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