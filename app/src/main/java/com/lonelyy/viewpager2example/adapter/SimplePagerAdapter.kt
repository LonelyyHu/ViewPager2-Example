package com.lonelyy.viewpager2example.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.lonelyy.viewpager2example.databinding.ItemViewpagerBinding
import com.lonelyy.viewpager2example.model.Page

class SimplePagerAdapter :
    ListAdapter<Page, SimplePagerAdapter.SimplePagerViewHolder>(PageDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SimplePagerViewHolder {
        val binding =
            ItemViewpagerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SimplePagerViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SimplePagerViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class PageDiffCallback : DiffUtil.ItemCallback<Page>() {
        override fun areItemsTheSame(oldItem: Page, newItem: Page): Boolean {
            Log.wtf("DiffUtil", "areItemsTheSame: ${oldItem.id == newItem.id}")
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Page, newItem: Page): Boolean {
            Log.wtf("DiffUtil", "areContentsTheSame: old = $oldItem")
            Log.wtf("DiffUtil", "areContentsTheSame: new = $newItem")
            Log.wtf("DiffUtil", "areContentsTheSame: ${oldItem == newItem}")
            return oldItem == newItem
        }

        // When this method been override the recyclerview item refresh animation will not be trigger
        override fun getChangePayload(oldItem: Page, newItem: Page): Any? {
            return true
        }

    }

    class SimplePagerViewHolder(private val binding: ItemViewpagerBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(page: Page) {
            binding.tvTitle.text = page.title
            binding.tvValue.text = page.value.toString()
        }
    }
}


