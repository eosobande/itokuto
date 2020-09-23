package com.eosobande.itokuto.widgets.stacks

import android.content.Context
import androidx.appcompat.widget.Toolbar
import com.eosobande.itokuto.modifiers.ToolBarWidget

data class ToolBar(override val context: Context) :
    ToolBarWidget.Modifier<ToolBar>(Toolbar(context))
