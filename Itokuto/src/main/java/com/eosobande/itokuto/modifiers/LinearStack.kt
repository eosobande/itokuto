package com.eosobande.itokuto.modifiers

import android.widget.LinearLayout
import com.eosobande.itokuto.HasGravity

interface LinearStack : StackWidget {
    abstract class Modifier<V : LinearLayout, T : Modifier<V, T>>(view: V) :
        StackWidget.Modifier<V, T>(view), LinearStack, HasGravity<T> {

        override fun gravity(gravity: Int) = this { view.gravity = gravity }

        fun weightSum(weightSum: Float) = this { view.weightSum = weightSum }

    }
}
