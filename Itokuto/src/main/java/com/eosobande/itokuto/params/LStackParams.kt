package com.eosobande.itokuto.params

import android.view.ViewGroup
import android.widget.LinearLayout

class LStackParams(
    width: Int = ViewGroup.LayoutParams.WRAP_CONTENT,
    height: Int = ViewGroup.LayoutParams.WRAP_CONTENT,
    weight: Float = 0f
) : LinearLayout.LayoutParams(width, height, weight) {

    fun gravity(gravity: Int): LStackParams {
        this.gravity = gravity
        return this
    }

}