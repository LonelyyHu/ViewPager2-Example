package com.lonelyy.viewpager2example

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.lonelyy.viewpager2example.databinding.ActivityStatefulBinding
import com.lonelyy.viewpager2example.model.Page
import com.lonelyy.viewpager2example.model.getPageData

class StatefulActivity : AppCompatActivity() {
    private lateinit var binding: ActivityStatefulBinding
    private lateinit var viewPagerAdapter: StatefulAdapter
    private var pageData = listOf<Page>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityStatefulBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)

        viewPagerAdapter = StatefulAdapter(this)
        binding.apply {
            pager.adapter = viewPagerAdapter
        }

        pageData = getPageData()
        viewPagerAdapter.submitList(pageData)
    }
}


class StatefulAdapter(activity: StatefulActivity) : FragmentStateAdapter(activity) {
    private val itemList = mutableListOf<Page>()

    override fun getItemCount() = itemList.size

    override fun createFragment(position: Int) = PagerFragment.newInstance(itemList[position])

    fun submitList(pageList: List<Page>) {
        itemList.clear()
        itemList.addAll(pageList)
        notifyDataSetChanged()
    }

}