package com.eosobande.itokuto.widgets.stacks.linear

import android.content.Context
import com.google.android.material.appbar.AppBarLayout
import com.eosobande.itokuto.modifiers.AppBarWidget

data class AppBar(override val context: Context) :
    AppBarWidget.Modifier<AppBar>(AppBarLayout(context))