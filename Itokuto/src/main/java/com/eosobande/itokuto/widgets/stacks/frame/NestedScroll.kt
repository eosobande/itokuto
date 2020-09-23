package com.eosobande.itokuto.widgets.stacks.frame

import android.content.Context
import androidx.core.widget.NestedScrollView
import com.eosobande.itokuto.modifiers.NestedScrollWidget


data class NestedScroll(override val context: Context) :
    NestedScrollWidget.Modifier<NestedScroll>(NestedScrollView(context))
