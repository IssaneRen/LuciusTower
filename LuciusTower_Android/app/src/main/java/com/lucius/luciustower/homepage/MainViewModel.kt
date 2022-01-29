package com.lucius.luciustower.homepage

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData

/**
 * @author Lucius Ren
 * @since 2022/1/29 16:10
 *
 * 首页的viewModel
 */
class MainViewModel(app: Application) : AndroidViewModel(app) {
    // 1. liveData for 首页标题更新
    val liveDataForToolbarTitle = MutableLiveData<String>()

    // 2. liveData for 首页顶部右侧icon 1 更新
    val liveDataForToolbarIconRight1 = MutableLiveData<MainModel.MenuIconModel>()
}