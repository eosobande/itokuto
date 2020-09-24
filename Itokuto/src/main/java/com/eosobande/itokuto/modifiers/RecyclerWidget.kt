package com.eosobande.itokuto.modifiers

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

interface RecyclerWidget : StackWidget {

    abstract class Modifier<T : Modifier<T>>(view: RecyclerView) :
        StackWidget.Modifier<RecyclerView, T>(view), RecyclerWidget {

        var layoutManager: LinearLayoutManager?
            get() = view.layoutManager as LinearLayoutManager
            set(value) {
                view.layoutManager = value
            }

        var adapter: RecyclerView.Adapter<*>?
            get() = view.adapter
            set(value) {
                view.adapter = value
            }

        fun onScrollToTop(block: (recycler: T) -> Unit) = onScroll { _, _, _ ->
            if (!view.canScrollVertically(-1)) block(cast)
        }

        fun onScrollToBottom(block: (recycler: T) -> Unit) = onScroll { _, _, _ ->
            if (!view.canScrollVertically(1)) block(cast)
        }

        fun onScrollToStart(block: (recycler: T) -> Unit) = onScroll { _, _, _ ->
            if (!view.canScrollHorizontally(-1)) block(cast)
        }

        fun onScrollToEnd(block: (recycler: T) -> Unit) = onScroll { _, _, _ ->
            if (!view.canScrollHorizontally(1)) block(cast)
        }

        inline fun onScroll(crossinline block: (recycler: T, dx: Int, dy: Int) -> Unit) = view {
            addOnScrollListener(object : RecyclerView.OnScrollListener() {
                @Suppress("UNCHECKED_CAST")
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
                    block(this@Modifier as T, dx, dy)
                }
            })
        }

        fun smoothScrollTo(position: Int) = this { view.smoothScrollToPosition(position) }

        fun adapter(adapter: RecyclerView.Adapter<*>?) = this { view.adapter = adapter }

        fun addItemDecoration(itemDecoration: RecyclerView.ItemDecoration) = this {
            view.addItemDecoration(itemDecoration)
        }

        fun setItemViewCacheSize(size: Int) = this { view.setItemViewCacheSize(size) }

        fun itemAnimator(itemAnimator: RecyclerView.ItemAnimator?) =
            this { view.itemAnimator = itemAnimator }

    }

}