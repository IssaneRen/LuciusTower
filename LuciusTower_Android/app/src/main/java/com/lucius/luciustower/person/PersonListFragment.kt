package com.lucius.luciustower.person

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.blankj.utilcode.util.LogUtils
import com.intsig.roomdb.entity.Person
import com.lucius.luciustower.R
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

/**
 * A simple [Fragment] subclass.
 * Use the [PersonListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class PersonListFragment : Fragment() {

    private val mViewModel by viewModels<PersonListViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
//            param1 = it.getString(ARG_PARAM1)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_person_list, container, false)
    }

    private var mTvAddOneDbRow: TextView? = null
    private var mRvPersonList: RecyclerView? = null
    private var mPersonListAdapter: PersonPagingAdapter? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        LogUtils.dTag(TAG, "onViewCreated")
        super.onViewCreated(view, savedInstanceState)
        mRvPersonList = view.findViewById(R.id.rv_person_list)
        mTvAddOneDbRow = view.findViewById(R.id.bt_debug_add) // 测试增加一行数据库记录
        mRvPersonList?.layoutManager = LinearLayoutManager(context)
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
        mRvPersonList?.adapter = mPersonListAdapter

        lifecycleScope.launch {
            LogUtils.dTag(TAG, "onViewCreated and lifecycleScope.launch")
            mViewModel.allPeople.collectLatest {
                mPersonListAdapter?.submitData(it)
            }
        }

        mTvAddOneDbRow?.setOnClickListener { mViewModel.addOneDebugRowInDb() }
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