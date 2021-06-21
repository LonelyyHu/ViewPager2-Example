package com.lonelyy.viewpager2example

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.lonelyy.viewpager2example.adapter.StatefulPagerAdapter
import com.lonelyy.viewpager2example.databinding.ActivityStatefulBinding
import com.lonelyy.viewpager2example.model.Page
import com.lonelyy.viewpager2example.model.getPageData

class StatefulActivity2 : AppCompatActivity() {
    private lateinit var binding: ActivityStatefulBinding
    private lateinit var viewPagerAdapter: StatefulPagerAdapter
    private var pageData = listOf<Page>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityStatefulBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)

        viewPagerAdapter = StatefulPagerAdapter(this)
        binding.apply {
            pager.adapter = viewPagerAdapter
        }

        pageData = getPageData()
        viewPagerAdapter.submitList(pageData)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_item_inc -> {
                increasePageValue()
                viewPagerAdapter.submitList(pageData)
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        super.onCreateOptionsMenu(menu)
        menuInflater.inflate(R.menu.menu_inc, menu)
        return true
    }

    private fun increasePageValue() {
        pageData = pageData.map {
            it.copy(
                value = (it.value + 1)
            )
        }
    }
}