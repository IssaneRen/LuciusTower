package com.lucius.luciustower.person

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.blankj.utilcode.util.LogUtils
import com.intsig.roomdb.entity.Person
import com.intsig.roomdb.util.LuciusTowerDatabaseUtil
import kotlinx.coroutines.flow.Flow
import java.text.SimpleDateFormat

/**
 * @author Lucius Ren
 * @since 2021/12/30 20:07
 *
 * 引入viewModel，使用MVVM架构定义，便于使用Flow从数据库更新
 */
class PersonListViewModel(app: Application): AndroidViewModel(app) {

    companion object {
        private const val TAG = "PersonListViewModel"
    }

    private val mDateFormatter = SimpleDateFormat("yyyy-MM-dd")

    // 1. 使用flow的方式监听
    val allPeople: Flow<PagingData<Person>>
            = Pager(
        PagingConfig(
            pageSize = 20,
            prefetchDistance = 3,
            initialLoadSize = 40,
            maxSize = 200
        )
    ) { LuciusTowerDatabaseUtil.db.personDao().getAllByPagingSource() }.flow

    // appendix-1 room数据库中增加一条记录
    fun addOneDebugRowInDb() {
        val currentCount = LuciusTowerDatabaseUtil.db.personDao().getAll().size
        LogUtils.dTag(TAG, "点击debug添加一行数据库，现在已经有 $currentCount 个数据库列了")
        val name = "第${currentCount + 1}个"
        val nickName = "小${currentCount + 1}"
        val person = Person(null, name, nickName, 1, null, mDateFormatter.parse("1997-05-11"), null)
        LuciusTowerDatabaseUtil.db.personDao().insertAll(person)
        LogUtils.dTag(TAG, "点击debug添加一行数据库，刚测试插入了一行$person")
    }
}
