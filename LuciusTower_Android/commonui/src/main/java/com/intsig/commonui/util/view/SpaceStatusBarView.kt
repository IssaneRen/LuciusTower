package com.intsig.commonui.util.view

import android.content.Context
import android.util.AttributeSet
import android.view.View
import com.intsig.commonui.util.statusbar.StatusBarHelper

/**
 * @author Lucius Ren
 * @since 2022/1/26 12:04
 * 自动根据当前的StatusBar的高度进行空View的高度 在onMeasure方法中计算状态栏的高度
 */
class SpaceStatusBarView : View {
    constructor(context: Context?)
            : super(context)

    constructor(context: Context?, attrs: AttributeSet?)
            : super(context, attrs)

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int)
            : super(context, attrs, defStyleAttr)

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int)
            : super(context, attrs, defStyleAttr, defStyleRes)

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val statusBarHeight: Int = StatusBarHelper.getStatusBarHeight()
        super.onMeasure(
            widthMeasureSpec, MeasureSpec.makeMeasureSpec(
                statusBarHeight,
                MeasureSpec.EXACTLY
            )
        )
    }
}
