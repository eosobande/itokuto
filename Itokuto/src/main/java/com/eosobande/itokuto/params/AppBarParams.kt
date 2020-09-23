package com.eosobande.itokuto.params

import android.view.ViewGroup
import com.google.android.material.appbar.AppBarLayout

class AppBarParams(
    width: Int = ViewGroup.LayoutParams.WRAP_CONTENT,
    height: Int = ViewGroup.LayoutParams.WRAP_CONTENT,
    weight: Float = 0f,
    @ScrollFlags scrollFlags: Int = SCROLL_FLAG_NO_SCROLL
) : AppBarLayout.LayoutParams(width, height, weight) {

    init {
        setScrollFlags(scrollFlags)
    }

}