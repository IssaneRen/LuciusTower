package com.lucius.luciustower.splash

import android.content.Intent
import androidx.lifecycle.lifecycleScope
import com.intsig.commonbase.baseclass.BaseLuciusActivity
import com.lucius.luciustower.databinding.ActivityLuciusTowerSplashBinding
import com.lucius.luciustower.homepage.MainActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

/**
 * @author Lucius Ren
 * @since 2022/1/26 16:52
 *
 * 新增一个简单的splash页面
 */
class LuciusTowerSplashActivity: BaseLuciusActivity() {

    private lateinit var mBinding: ActivityLuciusTowerSplashBinding

    override fun initData() {
        lifecycleScope.launchWhenCreated {
            withContext(Dispatchers.IO) { delay(500L) }
            withContext(Dispatchers.Main) {
                startActivity(Intent(this@LuciusTowerSplashActivity, MainActivity::class.java))
            }
        }
    }

    override fun initView() {
        mBinding = ActivityLuciusTowerSplashBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
    }
}