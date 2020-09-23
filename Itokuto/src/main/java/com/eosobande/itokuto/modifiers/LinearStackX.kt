package com.eosobande.itokuto.modifiers

import androidx.appcompat.widget.LinearLayoutCompat
import com.eosobande.itokuto.HasGravity

interface LinearStackX : StackWidget {
    abstract class Modifier<V : LinearLayoutCompat, T : Modifier<V, T>>(view: V) :
        StackWidget.Modifier<V, T>(view), LinearStackX, HasGravity<T> {

        override fun gravity(gravity: Int) = this { view.gravity = gravity }

        fun weightSum(weightSum: Float) = this { view.weightSum = weightSum }

    }
}