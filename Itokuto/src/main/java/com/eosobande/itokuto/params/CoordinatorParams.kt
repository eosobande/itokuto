package com.eosobande.itokuto.params

import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import androidx.annotation.IdRes
import androidx.coordinatorlayout.widget.CoordinatorLayout
import com.google.android.material.appbar.AppBarLayout

class CoordinatorParams(
    width: Int = ViewGroup.LayoutParams.WRAP_CONTENT,
    height: Int = ViewGroup.LayoutParams.WRAP_CONTENT,
    gravity: Int = Gravity.NO_GRAVITY,
    behave: Boolean = false,
    @IdRes anchorId: Int = View.NO_ID,
    anchorGravity: Int = Gravity.NO_GRAVITY,
    behavior: CoordinatorLayout.Behavior<*>? = null
) : CoordinatorLayout.LayoutParams(width, height) {

    init {
        this.gravity = gravity
        if (behave) {
            this.behavior = behavior ?: AppBarLayout.ScrollingViewBehavior()
        } else if (behavior != null) {
            this.behavior = behavior
        }
        this.anchorId = anchorId
        this.anchorGravity = anchorGravity
    }

}