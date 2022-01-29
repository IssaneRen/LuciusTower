package com.lucius.luciustower.person

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.blankj.utilcode.util.LogUtils
import com.intsig.roomdb.entity.Person
import com.lucius.luciustower.R
import java.text.SimpleDateFormat

/**
 * @author Lucius Ren
 * @since 2021/12/30 21:00
 *
 * 使用pagingAdapter实现一个person相关的刷新类库
 */
class PersonPagingAdapter(
    private val context: Context,
    diffCallback: DiffUtil.ItemCallback<Person>,
) :
    PagingDataAdapter<Person, PersonPagingAdapter.PersonViewHolder>(diffCallback) {
    companion object {
        private const val TAG = "PersonPagingAdapter"
    }

    private val mDateFormatter = SimpleDateFormat("yyyy-MM-dd")

    override fun onBindViewHolder(holder: PersonViewHolder, position: Int) {
        getItem(position)?.let { item ->
            LogUtils.dTag(TAG, "convert position=$position, ")
            holder.itemView.findViewById<AppCompatTextView>(R.id.atv_person_name).text = item.name
            item.birthDay?.let {
                holder.itemView.findViewById<AppCompatTextView>(R.id.atv_person_birthday).text =
                    mDateFormatter.format(it)
            }
            holder.itemView.findViewById<AppCompatImageView>(R.id.aiv_person_sex).setImageResource(
                when (item.sex) {
                    1 -> R.drawable.ic_male_symbol
                    2 -> R.drawable.ic_female_symbol
                    else -> R.drawable.ic_unknown_filled
                }
            )
        } ?: run {
            LogUtils.eTag(TAG, "onBindViewHolder, error, person is null!")
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_person_list, parent, false)
        return PersonViewHolder(view)
    }

    inner class PersonViewHolder(view: View) : RecyclerView.ViewHolder(view)
}
