package com.lonelyy.viewpager2example

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.lonelyy.viewpager2example.databinding.ActivitySimplePagerBinding
import com.lonelyy.viewpager2example.databinding.ItemViewpagerBinding
import com.lonelyy.viewpager2example.model.Page
import com.lonelyy.viewpager2example.model.getPageData

class SimplePagerActivity : AppCompatActivity() {
    private lateinit var viewPagerAdapter: ViewPagerAdapter
    private var pageData = listOf<Page>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivitySimplePagerBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)

        viewPagerAdapter = ViewPagerAdapter()
        binding.apply {
            pager.adapter = viewPagerAdapter
        }

        pageData = getPageData()
        viewPagerAdapter.submitList(pageData)
    }
}


class ViewPagerAdapter : ListAdapter<Page, PagerViewHolder>(PageDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PagerViewHolder {
        val binding =
            ItemViewpagerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PagerViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PagerViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class PageDiffCallback : DiffUtil.ItemCallback<Page>() {
        override fun areItemsTheSame(oldItem: Page, newItem: Page): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Page, newItem: Page): Boolean {
            return oldItem == newItem
        }
    }
}


class PagerViewHolder(private val binding: ItemViewpagerBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(page: Page) {
        binding.tvTitle.text = page.title
        binding.tvValue.text = page.value.toString()
    }
}