package com.lucius.luciustower.person.detail

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.intsig.roomdb.entity.Person

/**
 * @author Lucius Ren
 * @since 2022/2/23 17:31
 *
 * 个人详情页的viewModel，该页面的功能 -
 *   1. 根据用户id 读取数据库中的详细内容，并展示
 *   2. 判断当前页面是否有过修改 - 决定【保存】右上角是否有红点
 *   3. 点击【保存】，全量更新数据
 */
class PersonDetailViewModel(app: Application): AndroidViewModel(app) {

    /**
     * 当前页面的个人 uid，默认为-1
     */
    var personUid = -1L

    /**
     * 当前页面的个人信息info
     */
    private var personInfo: Person? = null
}