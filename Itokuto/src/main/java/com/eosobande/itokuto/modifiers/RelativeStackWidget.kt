package com.eosobande.itokuto.modifiers

import android.widget.RelativeLayout
import com.eosobande.itokuto.HasGravity

interface RelativeStackWidget : StackWidget {

    abstract class Modifier<T : Modifier<T>>(view: RelativeLayout) :
        StackWidget.Modifier<RelativeLayout, T>(view), RelativeStackWidget, HasGravity<T> {

        override fun gravity(gravity: Int) = this { view.gravity = gravity }

    }

}
