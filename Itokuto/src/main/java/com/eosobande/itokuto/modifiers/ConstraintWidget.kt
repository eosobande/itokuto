package com.eosobande.itokuto.modifiers

import androidx.constraintlayout.widget.ConstraintLayout

interface ConstraintWidget : StackWidget {
    abstract class Modifier<T : Modifier<T>>(view: ConstraintLayout) :
        StackWidget.Modifier<ConstraintLayout, T>(view), ConstraintWidget
}