package com.eosobande.itokuto.params

import android.view.ViewGroup

class Params(
    width: Int = ViewGroup.LayoutParams.WRAP_CONTENT,
    height: Int = ViewGroup.LayoutParams.WRAP_CONTENT
) : ViewGroup.MarginLayoutParams(width, height)