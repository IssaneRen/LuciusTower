package com.lucius.luciustower.person.detail

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.blankj.utilcode.util.LogUtils
import com.intsig.commonbase.baseclass.BaseLuciusActivity
import com.lucius.luciustower.databinding.ActivitySingleFragmentWithoutToolbarBinding

/**
 * @author Lucius Ren
 * @since 2022/2/23 17:16
 *
 * 个人详情页，展示个人信息并支持修改
 */
class PersonDetailActivity : BaseLuciusActivity() {
    companion object {
        private const val TAG = "PersonDetailActivity"

        private const val EXTRA_KEY_PERSON_UID = "extra_key_person_uid"

        fun startActivity(activity: AppCompatActivity, personUid: Long) {
            val intent = Intent(activity, PersonDetailActivity::class.java)
            intent.putExtra(EXTRA_KEY_PERSON_UID, personUid)
            activity.startActivity(intent)
        }
    }

    private lateinit var mBinding: ActivitySingleFragmentWithoutToolbarBinding

    private val mViewModel by viewModels<PersonDetailViewModel>()

    override fun initIntent(bundle: Bundle) {
        super.initIntent(bundle)
        mViewModel.personUid = bundle.getLong(EXTRA_KEY_PERSON_UID)
    }

    override fun initData() {
    }

    override fun initView() {
        mBinding = ActivitySingleFragmentWithoutToolbarBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
        // 构建fragment视图
        val f = PersonDetailFragment.newInstance()
        try {
            replaceFragment(mBinding.flContentContainer.id, f, false)
        } catch (t: Throwable) {
            LogUtils.eTag(TAG, "initialize but get error,\n $t")
        }
    }
}