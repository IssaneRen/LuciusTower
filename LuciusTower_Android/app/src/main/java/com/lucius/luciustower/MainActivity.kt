package com.lucius.luciustower

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.intsig.roomdb.entity.Person
import com.intsig.roomdb.util.LuciusTowerDatabaseUtil
import java.util.*

class MainActivity : AppCompatActivity() {
    companion object {
        private const val TAG = "MainActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.hide()

        findViewById<View?>(R.id.tv_insert)?.setOnClickListener {
            insertPerson()
        }
        findViewById<View?>(R.id.tv_check)?.setOnClickListener {
            checkPersonSex()
        }
    }

    // todo Lucius 删除debug代码
    private fun insertPerson() {
        Log.d(TAG, "insertPerson")
        try {
            LuciusTowerDatabaseUtil.db?.personDao()?.insertAll(Person(123456L, "LuciusRen", "lucius", 1, null, Date()))
        } catch (e: Throwable) {
            Log.e(TAG, "insertPerson get error: $e")
        }
    }

    private fun checkPersonSex() {
        val person = LuciusTowerDatabaseUtil.db?.personDao()?.findByName("LuciusRen")?.getOrNull(0)
        val sex = person?.sex
        Log.d(TAG, "person = $person, sex = $sex")

        val peopleList = LuciusTowerDatabaseUtil.db?.personDao()?.getAll()
        Log.d(TAG, "personList = ${peopleList?.size},")
    }
}
