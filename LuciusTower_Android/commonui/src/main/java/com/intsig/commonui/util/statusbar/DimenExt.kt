package com.intsig.commonui.util.statusbar

import android.content.res.Resources
import android.util.TypedValue

/**
 * @author Lucius Ren
 * @since 2022/1/26 14:19
 *
 * 关于dimen px dp sp等的换算扩展函数
 */

val Float.dp
    get() = TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP,
        this,
        Resources.getSystem().displayMetrics
    )

val Int.dp
    get() = TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP,
        this.toFloat(),
        Resources.getSystem().displayMetrics
    ).toInt()

