package com.eosobande.itokuto.widgets.stacks

import android.content.Context
import androidx.coordinatorlayout.widget.CoordinatorLayout
import com.eosobande.itokuto.modifiers.CoordinatorWidget


class Coordinator(override val context: Context) :
    CoordinatorWidget.Modifier<Coordinator>(CoordinatorLayout(context))
