package com.lonelyy.viewpager2example.adapter

import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DiffUtil
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.adapter.FragmentViewHolder
import com.lonelyy.viewpager2example.PagerFragment
import com.lonelyy.viewpager2example.model.Page

class StatefulPagerAdapter(private val activity: AppCompatActivity) : FragmentStateAdapter(activity) {
    private val itemList = mutableListOf<Page>()

    override fun getItemCount() = itemList.size

    override fun createFragment(position: Int) = PagerFragment.newInstance(itemList[position])

    override fun getItemId(position: Int) = itemList[position].id

    override fun containsItem(itemId: Long) = itemList.any { it.id == itemId }

    // onBindViewHolder(holder, int) is not called for the currently visible item,
    // we can override this method in our adapter and manually update RecyclerView.ViewHolder.itemView in our case Fragment to display new data
    override fun onBindViewHolder(holder: FragmentViewHolder, position: Int, payloads: MutableList<Any>) {
        val tag = "f${holder.itemId}"
        val fragment = activity.supportFragmentManager.findFragmentByTag(tag)

        val page = itemList[position]
        fragment?.let {
            (fragment as PagerFragment).setPage(page)
        } ?: run {
            super.onBindViewHolder(holder, position, payloads)
        }
    }

    fun submitList(pageList: List<Page>) {
        val callback = PageDiffUtil(itemList, pageList)
        val diff = DiffUtil.calculateDiff(callback)

        itemList.clear()
        itemList.addAll(pageList)

        diff.dispatchUpdatesTo(this)
    }
}

internal class PageDiffUtil(private val oldList: List<Page>, private val newList: List<Page>) : DiffUtil.Callback() {
    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldItem = oldList[oldItemPosition]
        val newItem = newList[newItemPosition]
        Log.wtf("DiffUtil", "oldItemPosition: $oldItemPosition, newItemPosition: $newItemPosition => areItemsTheSame: ${oldItem.id == newItem.id}")
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldItem = oldList[oldItemPosition]
        val newItem = newList[newItemPosition]
        Log.wtf("DiffUtil", "areContentsTheSame: old = $oldItem")
        Log.wtf("DiffUtil", "areContentsTheSame: new = $newItem")
        Log.wtf("DiffUtil", "areContentsTheSame: ${oldItem == newItem}")
        return oldItem == newItem
    }

    // When this method been override the recyclerview item refresh animation will not be trigger
    override fun getChangePayload(oldItemPosition: Int, newItemPosition: Int): Any {
        return true
    }
}