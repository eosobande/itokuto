package com.eosobande.itokuto.modifiers

import android.graphics.drawable.Drawable
import com.google.android.material.appbar.AppBarLayout

interface AppBarWidget : LinearStack {
    abstract class Modifier<T: Modifier<T>>(view: AppBarLayout) :
        LinearStack.Modifier<AppBarLayout, T>(view), AppBarWidget {

        fun expanded(expanded: Boolean) = this {
            view.setExpanded(expanded)
        }

        fun statusBarForeground(drawable: Drawable?) = this {
            view.statusBarForeground = drawable
        }

    }
}
