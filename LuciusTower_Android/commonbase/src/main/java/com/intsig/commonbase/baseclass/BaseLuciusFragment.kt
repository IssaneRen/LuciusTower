package com.intsig.commonbase.baseclass

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.intsig.commonbase.backfragment.BackPressHandleFragment

/**
 * @author Lucius Ren
 * @since 2022/1/26 15:15
 *
 * 项目中所有fragment基类 暂时没有复杂逻辑，但是全局集成，保证业务整体可修改
 */
abstract class BaseLuciusFragment: BackPressHandleFragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let { initIntent(it) }
        initData()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflateFragmentView(inflater)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView(view)
    }

    override fun onResume() {
        super.onResume()
        onFragmentVisible()
    }

    /**
     * 基类方法 step1： 如若有跳转传递进来的bundle，统一在这里处理. 因为有些页面无传递数据，所以非必须重写
     */
    open fun initIntent(bundle: Bundle) {}

    /**
     * 基类方法 step2: 如果有在视图刷新前就要进行的数据更新，请写在这里
     */
    abstract fun initData()

    /**
     * 基类方法 step3： inflate页面，之所以只封装到膨胀这一步，是为了兼顾 layoutId 与 viewBinding。 虽然项目里应该基本都是后者
     */
    abstract fun inflateFragmentView(inflater: LayoutInflater): View

    /**
     * 基类方法step4: view已经inflate完成，可以利用view初始化控件状态
     */
    abstract fun initView(view: View)

    /**
     * 基类方法step5: 每次fragment能够可见的时候会调用这个
     */
    abstract fun onFragmentVisible()
}

