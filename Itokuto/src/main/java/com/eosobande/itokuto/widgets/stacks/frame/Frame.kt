package com.eosobande.itokuto.widgets.stacks.frame

import android.content.Context
import android.widget.FrameLayout
import com.eosobande.itokuto.modifiers.FrameWidget

class Frame(override val context: Context) :
    FrameWidget.Modifier<FrameLayout, Frame>(FrameLayout(context))