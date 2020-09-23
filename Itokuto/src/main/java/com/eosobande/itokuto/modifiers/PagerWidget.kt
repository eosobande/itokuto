package com.eosobande.itokuto.modifiers

import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.eosobande.itokuto.widgets.stacks.Pager

interface PagerWidget : StackWidget {

    abstract class Modifier<T : Modifier<T>>(view: ViewPager2) :
        StackWidget.Modifier<ViewPager2, T>(view), PagerWidget {

        val currentPage
            get() = view.currentItem

        fun pageCallBack(callback: ViewPager2.OnPageChangeCallback) = this {
            view.registerOnPageChangeCallback(callback)
        }

        fun selectPage(page: Int) = this { view.currentItem = page }

        fun next(): Boolean {
            if (view.currentItem < view.adapter!!.itemCount - 1) {
                view.currentItem += 1
                return true
            }
            return false
        }

        fun previous(): Boolean {
            if (view.currentItem > 0) {
                view.currentItem -= 1
                return true
            }
            return false
        }

        fun offScreenPageLimit(limit: Int) = this { view.offscreenPageLimit = limit }

        fun adapter(activity: FragmentActivity, adapter: Pager.PagerDelegate) = this {
            view.adapter = object : FragmentStateAdapter(activity) {
                override fun getItemCount() = adapter.pageCount
                override fun createFragment(position: Int) = adapter.getFragment(position)
            }
        }

        fun adapter(adapter: RecyclerView.Adapter<*>) = this { view.adapter = adapter }

    }

}