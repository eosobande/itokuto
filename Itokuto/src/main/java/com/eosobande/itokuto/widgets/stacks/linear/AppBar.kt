package com.eosobande.itokuto.widgets.stacks.linear

import android.content.Context
import com.google.android.material.appbar.AppBarLayout
import com.eosobande.itokuto.modifiers.AppBarWidget

class AppBar(override val context: Context) :
    AppBarWidget.Modifier<AppBar>(AppBarLayout(context))