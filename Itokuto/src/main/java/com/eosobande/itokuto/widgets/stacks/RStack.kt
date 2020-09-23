package com.eosobande.itokuto.widgets.stacks

import android.content.Context
import android.widget.RelativeLayout
import com.eosobande.itokuto.modifiers.RelativeStackWidget

data class RStack(override val context: Context) :
    RelativeStackWidget.Modifier<RStack>(RelativeLayout(context))