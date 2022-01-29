package com.lucius.luciustower.homepage

import android.view.View
import androidx.annotation.DrawableRes

/**
 * @author Lucius Ren
 * @since 2022/1/29 16:13
 *
 * 首页相关实体类
 */
class MainModel {
    /**
     * 首页的top菜单按钮实体类，通过配置内容进行更新。
     */
    data class MenuIconModel(
        @DrawableRes val resId: Int,
        val visibility: Int,
        val clickListener: View.OnClickListener?
    )
}