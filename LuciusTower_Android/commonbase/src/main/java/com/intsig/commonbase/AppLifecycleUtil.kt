package com.intsig.commonbase

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log

/**
 * @author Lucius Ren
 * @since 2021/9/8 20:44
 *
 * 应用生命周期相关的util类
 */
@SuppressLint("StaticFieldLeak")
object AppLifecycleUtil {
    private const val TAG = "AppLifecycleUtil"

    /**
     * 接口 - 具体实现当前app的相关运行时信息
     */
    interface IAppCallback {
        /**
         * app Info 1 - 设备id （唯一标识符）
         */
        fun getDeviceId(): String?

        /**
         * app Info more... 后续补充
         */
    }

    private var mAppCallback: IAppCallback? = null

    /**
     * AppContext上下文，因为每一个app启动时都会在此单例注册，所以可以通过这里访问
     */
    @JvmField
    var sContext: Context? = null

    /**
     * 注册application的方法。 如果此module含有可运行的apk 直接运行即可
     */
    fun registerApplication(ctx: Context, callback: IAppCallback?) {
        Log.d(TAG, "registerApplication")
        sContext = ctx
        mAppCallback = callback
    }
}
