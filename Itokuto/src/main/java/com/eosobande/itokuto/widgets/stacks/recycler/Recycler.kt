package com.eosobande.itokuto.widgets.stacks.recycler

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import com.eosobande.itokuto.modifiers.RecyclerWidget


class Recycler(override val context: Context) :
    RecyclerWidget.Modifier<Recycler>(RecyclerView(context))