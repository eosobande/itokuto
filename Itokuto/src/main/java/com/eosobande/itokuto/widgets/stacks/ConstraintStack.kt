package com.eosobande.itokuto.widgets.stacks

import android.content.Context
import androidx.constraintlayout.widget.ConstraintLayout
import com.eosobande.itokuto.modifiers.ConstraintWidget

class ConstraintStack(override val context: Context) :
    ConstraintWidget.Modifier<ConstraintStack>(ConstraintLayout(context))