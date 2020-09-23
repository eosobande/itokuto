package com.eosobande.itokuto.modifiers

import android.widget.FrameLayout

interface FrameWidget : StackWidget {
    abstract class Modifier<V : FrameLayout, T : Modifier<V, T>>(view: V) :
        StackWidget.Modifier<V, T>(view), FrameWidget {

        var measureAllChildren: Boolean
            get() = view.measureAllChildren
            set(value) {
                view.measureAllChildren = value
            }

    }
}