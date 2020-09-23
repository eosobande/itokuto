package com.eosobande.itokuto.params

import android.view.Gravity
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar

class ToolBarParams(
    width: Int = ViewGroup.LayoutParams.WRAP_CONTENT,
    height: Int = ViewGroup.LayoutParams.WRAP_CONTENT,
    gravity: Int = Gravity.NO_GRAVITY
) : Toolbar.LayoutParams(width, height, gravity)