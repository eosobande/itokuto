package com.eosobande.itokuto.widgets.stacks

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.eosobande.itokuto.modifiers.PagerWidget


data class Pager(override val context: Context) :
    PagerWidget.Modifier<Pager>(ViewPager2(context)) {

    constructor(context: Context, adapter: RecyclerView.Adapter<*>) : this(context) {
        adapter(adapter)
    }

    interface
    PagerDelegate {

        val pageCount: Int

        fun getFragment(position: Int): Fragment

        fun getPageTitle(position: Int): CharSequence?

    }

}

