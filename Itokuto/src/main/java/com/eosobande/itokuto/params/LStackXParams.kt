package com.eosobande.itokuto.params

import android.view.ViewGroup
import androidx.appcompat.widget.LinearLayoutCompat

class LStackXParams(
    width: Int = ViewGroup.LayoutParams.WRAP_CONTENT,
    height: Int = ViewGroup.LayoutParams.WRAP_CONTENT,
    weight: Float = 0f
) : LinearLayoutCompat.LayoutParams(width, height, weight) {

    fun gravity(gravity: Int): LStackXParams {
        this.gravity = gravity
        return this
    }

}