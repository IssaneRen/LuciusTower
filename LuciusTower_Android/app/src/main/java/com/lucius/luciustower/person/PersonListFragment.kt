package com.lucius.luciustower.person

import android.view.LayoutInflater
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.blankj.utilcode.util.LogUtils
import com.blankj.utilcode.util.ToastUtils
import com.intsig.commonbase.baseclass.BaseLuciusFragment
import com.intsig.roomdb.entity.Person
import com.lucius.luciustower.R
import com.lucius.luciustower.databinding.FragmentPersonListBinding
import com.lucius.luciustower.homepage.MainActivity
import com.lucius.luciustower.homepage.MainModel
import com.lucius.luciustower.homepage.MainViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

/**
 * A simple [Fragment] subclass.
 * Use the [PersonListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class PersonListFragment : BaseLuciusFragment() {

    private val mViewModel by viewModels<PersonListViewModel>()

    /**
     * 和activity公用viewModel
     */
    private val mActivityViewModel by lazy {
        ViewModelProvider(activity as MainActivity)[MainViewModel::class.java]
    }

    private lateinit var mBinding: FragmentPersonListBinding

    override fun initData() {
    }

    override fun inflateFragmentView(inflater: LayoutInflater): View {
        mBinding = FragmentPersonListBinding.inflate(inflater)
        return mBinding.root
    }

    private var mPersonListAdapter: PersonPagingAdapter? = null

    override fun initView(view: View) {
        LogUtils.dTag(TAG, "initView")
        mBinding.rvPersonList.layoutManager = LinearLayoutManager(context)
        activity?.let { act ->
            val diffUtilCallback = object : DiffUtil.ItemCallback<Person>() {
                override fun areItemsTheSame(oldItem: Person, newItem: Person): Boolean {
                    return oldItem.uidNum == newItem.uidNum
                }

                override fun areContentsTheSame(oldItem: Person, newItem: Person): Boolean {
                    return oldItem == newItem
                }
            }
            mPersonListAdapter = PersonPagingAdapter(act, diffUtilCallback)
        }
        mBinding.rvPersonList.adapter = mPersonListAdapter

        lifecycleScope.launch {
            LogUtils.dTag(TAG, "onViewCreated and lifecycleScope.launch")
            mViewModel.allPeople.collectLatest {
                mPersonListAdapter?.submitData(it)
            }
        }
        mBinding.btDebugAdd.setOnClickListener { mViewModel.addOneDebugRowInDb() }
    }

    override fun onFragmentVisible() {
        // 更新首页标题
        mActivityViewModel.liveDataForToolbarTitle.postValue("联系人")
        // 更新右上角按钮icon
        mActivityViewModel.liveDataForToolbarIconRight1.postValue(
            MainModel.MenuIconModel(R.drawable.ic_lucius_ui_menu, View.VISIBLE) {
                ToastUtils.showShort("联系人页菜单显示！")
            })
    }

    companion object {
        private const val TAG = "PersonListFragment"

        @JvmStatic
        fun newInstance() = PersonListFragment().apply {
//                arguments = Bundle().apply {
//                    putString(ARG_PARAM1, param1)
//                    putString(ARG_PARAM2, param2)
//                }
        }
    }
}