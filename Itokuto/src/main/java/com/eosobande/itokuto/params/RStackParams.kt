package com.eosobande.itokuto.params

import android.os.Build
import android.view.ViewGroup
import android.widget.RelativeLayout
import androidx.annotation.IdRes
import androidx.annotation.RequiresApi

class RStackParams(
    width: Int = ViewGroup.LayoutParams.WRAP_CONTENT,
    height: Int = ViewGroup.LayoutParams.WRAP_CONTENT
) : RelativeLayout.LayoutParams(width, height) {

    private fun Boolean.toInt(): Int = if (this) -1 else 0

    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    fun startOf(@IdRes id: Int): RStackParams {
        addRule(RelativeLayout.START_OF, id)
        return this
    }

    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    fun endOf(@IdRes id: Int): RStackParams {
        addRule(RelativeLayout.END_OF, id)
        return this
    }

    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    fun alignStart(@IdRes id: Int): RStackParams {
        addRule(RelativeLayout.ALIGN_START, id)
        return this
    }

    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    fun alignParentStart(align: Boolean = true): RStackParams {
        addRule(RelativeLayout.ALIGN_PARENT_START, align.toInt())
        return this
    }

    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    fun alignParentEnd(align: Boolean = true): RStackParams {
        addRule(RelativeLayout.ALIGN_PARENT_END, align.toInt())
        return this
    }

    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    fun alignEnd(@IdRes id: Int): RStackParams {
        addRule(RelativeLayout.ALIGN_END, id)
        return this
    }

    fun leftOf(@IdRes id: Int): RStackParams {
        addRule(RelativeLayout.LEFT_OF, id)
        return this
    }

    fun rightOf(@IdRes id: Int): RStackParams {
        addRule(RelativeLayout.RIGHT_OF, id)
        return this
    }

    fun alignLeft(@IdRes id: Int): RStackParams {
        addRule(RelativeLayout.ALIGN_LEFT, id)
        return this
    }

    fun alignRight(@IdRes id: Int): RStackParams {
        addRule(RelativeLayout.ALIGN_RIGHT, id)
        return this
    }

    fun alignParentRight(align: Boolean = true): RStackParams {
        addRule(RelativeLayout.ALIGN_PARENT_RIGHT, align.toInt())
        return this
    }

    fun alignParentLeft(align: Boolean = true): RStackParams {
        addRule(RelativeLayout.ALIGN_PARENT_LEFT, align.toInt())
        return this
    }

    fun above(@IdRes id: Int): RStackParams {
        addRule(RelativeLayout.ABOVE, id)
        return this
    }

    fun below(@IdRes id: Int): RStackParams {
        addRule(RelativeLayout.BELOW, id)
        return this
    }

    fun alignBaseline(@IdRes id: Int): RStackParams {
        addRule(RelativeLayout.ALIGN_BASELINE, id)
        return this
    }

    fun alignBottom(@IdRes id: Int): RStackParams {
        addRule(RelativeLayout.ALIGN_BOTTOM, id)
        return this
    }

    fun alignTop(@IdRes id: Int): RStackParams {
        addRule(RelativeLayout.ALIGN_TOP, id)
        return this
    }

    fun alignParentBottom(align: Boolean = true): RStackParams {
        addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, align.toInt())
        return this
    }

    fun alignParentTop(align: Boolean = true): RStackParams {
        addRule(RelativeLayout.ALIGN_PARENT_TOP, align.toInt())
        return this
    }

    fun centerHorizontal(align: Boolean = true): RStackParams {
        addRule(RelativeLayout.CENTER_HORIZONTAL, align.toInt())
        return this
    }

    fun centerInParent(align: Boolean = true): RStackParams {
        addRule(RelativeLayout.CENTER_IN_PARENT, align.toInt())
        return this
    }

    fun centerVertical(align: Boolean = true): RStackParams {
        addRule(RelativeLayout.CENTER_VERTICAL, align.toInt())
        return this
    }

}