package com.eosobande.itokuto.widgets.stacks.recycler

import androidx.recyclerview.widget.RecyclerView
import com.eosobande.itokuto.view

abstract class WidgetHolder<D, I : Item<D>>(val item: I) :
    RecyclerView.ViewHolder(item.widget.view())