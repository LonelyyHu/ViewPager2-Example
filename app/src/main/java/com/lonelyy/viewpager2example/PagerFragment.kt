package com.lonelyy.viewpager2example

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.lonelyy.viewpager2example.databinding.FragmentPagerBinding
import com.lonelyy.viewpager2example.model.Page

private const val ARG_PAGE = "ARG_PAGE"

class PagerFragment : Fragment() {
    private lateinit var binding: FragmentPagerBinding
    private var page: Page? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            page = it.getParcelable(ARG_PAGE)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPagerBinding.inflate(inflater, container, false)
        binding.apply {
            page?.let {
                tvTitle.text = it.title
                tvValue.text = it.value.toString()
            }
        }
        return binding.root
    }

    fun setPage(page: Page) {
        binding.apply {
            tvTitle.text = page.title
            tvValue.text = page.value.toString()
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(page: Page) =
            PagerFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(ARG_PAGE, page)
                }
            }
    }
}