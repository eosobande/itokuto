package com.eosobande.itokuto.params

import android.view.Gravity
import android.view.ViewGroup
import android.widget.FrameLayout

class FrameParams(
    width: Int = ViewGroup.LayoutParams.WRAP_CONTENT,
    height: Int = ViewGroup.LayoutParams.WRAP_CONTENT,
    gravity: Int = Gravity.NO_GRAVITY
) : FrameLayout.LayoutParams(width, height, gravity) {

    fun gravity(gravity: Int) = apply { this.gravity = gravity }

}