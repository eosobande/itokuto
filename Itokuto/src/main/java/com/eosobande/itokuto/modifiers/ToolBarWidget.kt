package com.eosobande.itokuto.modifiers

import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar

interface ToolBarWidget : StackWidget {

    abstract class Modifier<T : Modifier<T>>(view: Toolbar) :
        StackWidget.Modifier<Toolbar, T>(view), ToolBarWidget {

        fun navigationIcon(drawable: Drawable?) = this { view.navigationIcon = drawable }
        fun supportActionBar(activity: AppCompatActivity) = this {
            activity.setSupportActionBar(view)
        }

    }

}

