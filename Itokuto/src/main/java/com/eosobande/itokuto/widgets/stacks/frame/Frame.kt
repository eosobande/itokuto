package com.eosobande.itokuto.widgets.stacks.frame

import android.content.Context
import android.widget.FrameLayout
import com.eosobande.itokuto.modifiers.FrameWidget

data class Frame(override val context: Context) :
    FrameWidget.Modifier<FrameLayout, Frame>(FrameLayout(context))