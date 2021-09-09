package com.lucius.luciustower

import android.app.Application
import com.intsig.commonbase.AppLifecycleUtil

/**
 * @author Lucius Ren
 * @since 2021/9/9 10:53
 *
 * application相关class
 */
class TowerApplication : Application() {
    private companion object {
        private const val TAG = "TowerApplication"
    }

    private var mDeviceId = "TEST DEVICE ID" // todo Lucius 设备deviceId的赋值逻辑，这个不能简单获取OAID IMEI等，应该都会有些权限问题，需要综合处理一下

    override fun onCreate() {
        // 0. 应用onCreate启动准备
        super.onCreate()

        // 1. 注册 AppLifecycleUtil单例
        AppLifecycleUtil.registerApplication(this, object : AppLifecycleUtil.IAppCallback {
            override fun getDeviceId(): String? {
                return mDeviceId
            }
        })
    }
}