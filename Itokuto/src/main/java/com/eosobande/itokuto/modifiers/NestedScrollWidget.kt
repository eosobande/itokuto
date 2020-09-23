package com.eosobande.itokuto.modifiers

import androidx.core.widget.NestedScrollView

interface NestedScrollWidget : FrameWidget {

    abstract class Modifier<T : Modifier<T>>(view: NestedScrollView) :
        FrameWidget.Modifier<NestedScrollView, T>(view), FrameWidget

}