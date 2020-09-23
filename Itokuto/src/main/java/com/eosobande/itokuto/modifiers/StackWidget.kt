package com.eosobande.itokuto.modifiers

import android.view.ViewGroup
import com.eosobande.itokuto.modifier

interface StackWidget : Widget {

    abstract class Modifier<V : ViewGroup, T : Modifier<V, T>>(view: V) :
        Widget.Modifier<V, T>(view), StackWidget {

        fun clipToPadding(clip: Boolean = true) = this { view.clipToPadding = clip }
        fun clipChildren(clip: Boolean = true) = this { view.clipChildren = clip }

        operator fun Widget.unaryPlus(): Widget = apply {
            this@Modifier.view.addView(modifier.view)
        }

        operator fun Widget.unaryMinus(): Widget = apply {
            this@Modifier.view.removeView(modifier.view)
        }

        operator fun Merge.unaryPlus() {
            widgets.forEach {
                this@Modifier.view.addView(it.modifier.view)
            }
        }

        operator fun Merge.unaryMinus() {
            widgets.forEach {
                this@Modifier.view.removeView(it.modifier.view)
            }
        }

    }

}

