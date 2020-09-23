package com.eosobande.itokuto.widgets.stacks.recycler

import android.content.Context
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.eosobande.itokuto.Rectangle
import com.eosobande.itokuto.State
import com.eosobande.itokuto.modifiers.Widget
import com.eosobande.itokuto.view
import java.util.*
import kotlin.collections.ArrayList


abstract class RecyclerAdapter<D, H : RecyclerAdapter.WidgetHolder<D, out RecyclerAdapter.Item<D>>> :
    RecyclerView.Adapter<H>() {

    open val itemsClickable: Boolean = true

    private val diffCallback: DiffUtil.ItemCallback<D> = object : DiffUtil.ItemCallback<D>() {

        override fun areItemsTheSame(old: D, new: D) =
            this@RecyclerAdapter.areItemsTheSame(old, new)

        override fun areContentsTheSame(old: D, neww: D) =
            this@RecyclerAdapter.areContentsTheSame(old, neww)

    }

    private val differ by lazy {
        AsyncListDiffer(this, diffCallback)
    }

    private val itemsCopy: MutableList<D>
        get() = ArrayList(items)

    private var dataSetCopy: List<D>? = null

    val items: List<D>
        get() = differ.currentList

    protected abstract fun areItemsTheSame(old: D, new: D): Boolean
    protected abstract fun areContentsTheSame(old: D, new: D): Boolean
    protected abstract fun createWidgetHolder(context: Context, viewType: Int): H
    protected abstract fun onFilter(filter: String, data: D): Boolean
    protected abstract fun onItemClick(holder: H, data: D)

    override fun getItemCount(): Int = items.size

    final override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): H =
        createWidgetHolder(parent.context, viewType).apply {
            if (itemsClickable) itemView.setOnClickListener {
                onItemClick(this, items[adapterPosition])
            }
        }

    fun refresh() = notifyItemRangeChanged(0, itemCount)

    override fun onViewRecycled(holder: H) {
        holder.item.bound = false
    }

    override fun onBindViewHolder(holder: H, position: Int) {
        holder.item.data(items[position])
        holder.item.bound = true
    }

    fun filter(filter: String) {

        if (dataSetCopy == null) dataSetCopy = itemsCopy

        if (filter.isEmpty()) {
            differ.submitList(dataSetCopy!!)
            dataSetCopy = null
        } else {
            differ.submitList(
                dataSetCopy!!.filter {
                    onFilter(filter.toLowerCase(Locale.getDefault()), it)
                }
            )
        }

    }

    fun addOnEmptyChangedObserver(observer: Observer<Boolean>) =
        differ.addListListener { _, currentList ->
            observer.onChanged(currentList.isEmpty())
        }

    fun add(dataList: List<D>, position: Int) =
        differ.submitList(itemsCopy.apply { addAll(position, dataList) })

    fun add(data: D) = differ.submitList(itemsCopy.apply { add(data) })

    fun add(data: D, position: Int, replace: Boolean = false) =
        differ.submitList(itemsCopy.apply {
            if (replace) set(position, data) else add(position, data)
        })

    fun add(dataList: List<D>, replace: Boolean = false) = differ.submitList(
        if (replace) ArrayList(dataList) else itemsCopy.also { it.addAll(0, dataList) }
    )

    fun remove(position: Int) = differ.submitList(itemsCopy.apply { removeAt(position) })

    fun clear() = differ.submitList(emptyList())

    abstract class Item<D> : Widget {

        var bound: Boolean = false
            internal set

        val data: State<D> = State()

    }

    abstract class WidgetHolder<D, I : Item<D>>(val item: I) :
        RecyclerView.ViewHolder(item.widget.view())

}
