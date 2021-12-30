package com.lucius.luciustower.person

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.blankj.utilcode.util.LogUtils
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.intsig.roomdb.entity.Person
import com.lucius.luciustower.R
import java.text.SimpleDateFormat

// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [PersonListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class PersonListFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_person_list, container, false)
    }

    private var mRvPersonList: RecyclerView? = null
    private var mPersonListAdapter: BaseQuickAdapter<Person, BaseViewHolder>? = null
    private val mDateFormatter = SimpleDateFormat("yyyy-MM-dd")

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        LogUtils.dTag("测试", "onViewCreated")
        mRvPersonList = view.findViewById(R.id.rv_person_list)
        mRvPersonList?.layoutManager = LinearLayoutManager(context)
        mPersonListAdapter =
            object : BaseQuickAdapter<Person, BaseViewHolder>(R.layout.item_person_list) {

                override fun convert(holder: BaseViewHolder, item: Person) {
                    LogUtils.dTag("测试", "convert item=$item")
                    holder.getView<AppCompatTextView>(R.id.atv_person_name).text = item.name
                    item.birthDay?.let {
                        holder.getView<AppCompatTextView>(R.id.atv_person_birthday).text =
                            mDateFormatter.format(it)
                    }
                    holder.getView<AppCompatImageView>(R.id.aiv_person_sex).setImageResource(when(item.sex) {
                        1 -> R.drawable.ic_male_symbol
                        2 -> R.drawable.ic_female_symbol
                        else -> R.drawable.ic_unknown_filled
                    })

                }
            }
        mRvPersonList?.adapter = mPersonListAdapter
        mPersonListAdapter?.addData(
            // todo Lucius 删除测试代码
            listOf(
                Person(1, "张三", "小张", 1, null, mDateFormatter.parse("1997-05-11")),
                Person(1, "李四", "xx", 2, null, mDateFormatter.parse("1996-01-11")),
                Person(1, "王五", "ff", 1, null, mDateFormatter.parse("1995-12-12"))
            )
        )

    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment PersonListFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            PersonListFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}