package com.lucius.luciustower

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.blankj.utilcode.util.LogUtils
import com.lucius.luciustower.person.PersonListFragment

class MainActivity : AppCompatActivity() {
    companion object {
        private const val TAG = "MainActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.hide()

        val f = PersonListFragment.newInstance()
        try {
            replaceFragment(R.id.fl_container, f)
        } catch (t: Throwable) {
            LogUtils.dTag(TAG, "initialize but get error,\n $t")
        }
    }

    private fun replaceFragment(containerId: Int, fragment: Fragment) {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(containerId, fragment)
        fragmentTransaction.commitAllowingStateLoss()
    }
}
