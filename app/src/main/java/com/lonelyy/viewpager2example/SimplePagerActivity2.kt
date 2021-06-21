package com.lonelyy.viewpager2example

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.lonelyy.viewpager2example.adapter.SimplePagerAdapter
import com.lonelyy.viewpager2example.databinding.ActivitySimplePagerBinding
import com.lonelyy.viewpager2example.model.Page
import com.lonelyy.viewpager2example.model.getPageData

class SimplePagerActivity2 : AppCompatActivity() {
    private lateinit var viewPagerAdapter: SimplePagerAdapter
    private var pageData = listOf<Page>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivitySimplePagerBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)

        viewPagerAdapter = SimplePagerAdapter()
        binding.apply {
            pager.adapter = viewPagerAdapter
        }

        pageData = getPageData()
        viewPagerAdapter.submitList(pageData)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_item_inc -> {
                Log.wtf("SimplePagerActivity", "onOptionsItemSelected: ")
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
        /*
         *  the same list will not trigger diff util
         */
//        pageData.forEach {
//            it.value += 1
//        }


        pageData = pageData.map {
            it.copy(
                value = (it.value + 1)
            )
        }
    }
}