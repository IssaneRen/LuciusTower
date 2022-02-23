package com.lucius.luciustower.person.detail

import android.view.LayoutInflater
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.blankj.utilcode.util.LogUtils
import com.intsig.commonbase.baseclass.BaseLuciusFragment
import com.lucius.luciustower.databinding.FragmentPersonDetailBinding

/**
 * @author Lucius Ren
 * @since 2022/2/23 17:30
 *
 * 个人详情页，支持查看及
 */
class PersonDetailFragment: BaseLuciusFragment() {
    companion object {
        private const val TAG = "PersonDetailFragment"

        @JvmStatic
        fun newInstance() = PersonDetailFragment().apply {
//                arguments = Bundle().apply {
//                    putString(ARG_PARAM1, param1)
//                    putString(ARG_PARAM2, param2)
//                }
        }
    }

    private lateinit var mBinding: FragmentPersonDetailBinding

    /**
     * 和activity公用viewModel
     */
    private val mViewModel by lazy {
        ViewModelProvider(activity as PersonDetailActivity)[PersonDetailViewModel::class.java]
    }


    override fun initData() {
    }

    override fun inflateFragmentView(inflater: LayoutInflater): View {
        mBinding = FragmentPersonDetailBinding.inflate(inflater)
        return mBinding.root
    }

    override fun initView(view: View) {
    }

    override fun onFragmentVisible() {
        LogUtils.dTag(TAG, "onFragmentVisible")
    }
}