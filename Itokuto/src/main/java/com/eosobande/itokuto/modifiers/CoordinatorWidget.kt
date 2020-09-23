package com.eosobande.itokuto.modifiers

import androidx.coordinatorlayout.widget.CoordinatorLayout

interface CoordinatorWidget : StackWidget {
    abstract class Modifier<T : Modifier<T>>(view: CoordinatorLayout) :
        StackWidget.Modifier<CoordinatorLayout, T>(view), CoordinatorWidget
}