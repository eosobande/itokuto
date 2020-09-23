package com.eosobande.itokuto.widgets.stacks.recycler

import com.eosobande.itokuto.State
import com.eosobande.itokuto.modifiers.Widget

abstract class Item<D> : Widget {

    var bound: Boolean = false
        internal set

    val data: State<D> = State()

}