package com.lucius.luciustower.person

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.blankj.utilcode.util.LogUtils
import com.intsig.commonbase.baseclass.BaseLuciusFragment
import com.intsig.roomdb.entity.Person
import com.lucius.luciustower.databinding.FragmentPersonListBinding
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

/**
 * A simple [Fragment] subclass.
 * Use the [PersonListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class PersonListFragment : BaseLuciusFragment() {

    private val mViewModel by viewModels<PersonListViewModel>()
    private lateinit var mBinding: FragmentPersonListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
//            param1 = it.getString(ARG_PARAM1)
        }
    }

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