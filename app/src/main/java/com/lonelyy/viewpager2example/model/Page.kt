package com.lonelyy.viewpager2example.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Page(
    val id: Long,
    val title: String,
    var value: Int
) : Parcelable

fun getPageData() = listOf(
    Page(1, "item 1", 0),
    Page(2, "item 2", 0),
    Page(3, "item 3", 0),
    Page(4, "item 4", 0),
    Page(5, "item 5", 0),
    Page(6, "item 6", 0),
)