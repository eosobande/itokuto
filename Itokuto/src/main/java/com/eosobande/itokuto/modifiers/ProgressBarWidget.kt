package com.eosobande.itokuto.modifiers

import android.widget.ProgressBar

interface ProgressBarWidget : Widget {

    abstract class Modifier<V : ProgressBar, T:Modifier<V,T>>(view: V) : Widget.Modifier<V,T>(view)

}