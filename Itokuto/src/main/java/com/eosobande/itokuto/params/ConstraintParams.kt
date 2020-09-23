package com.eosobande.itokuto.params

import android.view.View
import android.view.ViewGroup
import androidx.annotation.IdRes
import androidx.constraintlayout.widget.ConstraintLayout

class ConstraintParams(
    width: Int = ViewGroup.LayoutParams.WRAP_CONTENT,
    height: Int = ViewGroup.LayoutParams.WRAP_CONTENT
) : ConstraintLayout.LayoutParams(width, height) {

    fun guideBegin(value: Int): ConstraintParams {
        this.guideBegin = value
        return this
    }

    fun guideEnd(value: Int): ConstraintParams {
        this.guideEnd = value
        return this
    }

    fun guidePercent(value: Float): ConstraintParams {
        this.guidePercent = value
        return this
    }

    fun leftToLeft(@IdRes value: Int = View.NO_ID): ConstraintParams {
        this.leftToLeft = value
        return this
    }

    fun leftToRight(@IdRes value: Int): ConstraintParams {
        this.leftToRight = value
        return this
    }

    fun rightToLeft(@IdRes value: Int): ConstraintParams {
        this.rightToLeft = value
        return this
    }

    fun rightToRight(@IdRes value: Int = View.NO_ID): ConstraintParams {
        this.rightToRight = value
        return this
    }

    fun topToTop(@IdRes value: Int): ConstraintParams {
        this.topToTop = value
        return this
    }

    fun topToBottom(@IdRes value: Int): ConstraintParams {
        this.topToBottom = value
        return this
    }

    fun bottomToTop(@IdRes value: Int): ConstraintParams {
        this.bottomToTop = value
        return this
    }

    fun bottomToBottom(@IdRes value: Int): ConstraintParams {
        this.bottomToBottom = value
        return this
    }

    fun baselineToBaseline(@IdRes value: Int): ConstraintParams {
        this.baselineToBaseline = value
        return this
    }

    fun startToEnd(@IdRes value: Int): ConstraintParams {
        this.startToEnd = value
        return this
    }

    fun startToStart(@IdRes value: Int): ConstraintParams {
        this.startToStart = value
        return this
    }

    fun endToStart(@IdRes value: Int): ConstraintParams {
        this.endToStart = value
        return this
    }

    fun endToEnd(@IdRes value: Int): ConstraintParams {
        this.endToEnd = value
        return this
    }

    fun circleConstraint(value: Int): ConstraintParams {
        this.circleConstraint = value
        return this
    }

    fun circleRadius(value: Int): ConstraintParams {
        this.circleRadius = value
        return this
    }

    fun circleAngle(value: Float): ConstraintParams {
        this.circleAngle = value
        return this
    }

    fun goneLeftMargin(value: Int): ConstraintParams {
        this.goneLeftMargin = value
        return this
    }

    fun goneTopMargin(value: Int): ConstraintParams {
        this.goneTopMargin = value
        return this
    }

    fun goneRightMargin(value: Int): ConstraintParams {
        this.goneRightMargin = value
        return this
    }

    fun goneBottomMargin(value: Int): ConstraintParams {
        this.goneBottomMargin = value
        return this
    }

    fun goneStartMargin(value: Int): ConstraintParams {
        this.goneStartMargin = value
        return this
    }

    fun goneEndMargin(value: Int): ConstraintParams {
        this.goneEndMargin = value
        return this
    }

    fun horizontalBias(value: Float): ConstraintParams {
        this.horizontalBias = value
        return this
    }

    fun verticalBias(value: Float): ConstraintParams {
        this.verticalBias = value
        return this
    }

    fun dimensionRatio(value: String): ConstraintParams {
        this.dimensionRatio = value
        return this
    }

    fun horizontalWeight(value: Float): ConstraintParams {
        this.horizontalWeight = value
        return this
    }

    fun verticalWeight(value: Float): ConstraintParams {
        this.verticalWeight = value
        return this
    }

    fun horizontalChainStyle(value: Int): ConstraintParams {
        this.horizontalChainStyle = value
        return this
    }

    fun verticalChainStyle(value: Int): ConstraintParams {
        this.verticalChainStyle = value
        return this
    }

    fun matchConstraintMinWidth(value: Int): ConstraintParams {
        this.matchConstraintMinWidth = value
        return this
    }

    fun matchConstraintMinHeight(value: Int): ConstraintParams {
        this.matchConstraintMinHeight = value
        return this
    }

    fun matchConstraintMaxWidth(value: Int): ConstraintParams {
        this.matchConstraintMaxWidth = value
        return this
    }

    fun matchConstraintMaxHeight(value: Int): ConstraintParams {
        this.matchConstraintMaxHeight = value
        return this
    }

    fun matchConstraintPercentWidth(value: Float): ConstraintParams {
        this.matchConstraintPercentWidth = value
        return this
    }

    fun matchConstraintPercentHeight(value: Float): ConstraintParams {
        this.matchConstraintPercentHeight = value
        return this
    }

    fun editorAbsoluteX(value: Int): ConstraintParams {
        this.editorAbsoluteX = value
        return this
    }

    fun editorAbsoluteY(value: Int): ConstraintParams {
        this.editorAbsoluteY = value
        return this
    }

    fun orientation(value: Int): ConstraintParams {
        this.orientation = value
        return this
    }

    fun constrainedWidth(value: Boolean): ConstraintParams {
        this.constrainedWidth = value
        return this
    }

    fun constrainedHeight(value: Boolean): ConstraintParams {
        this.constrainedHeight = value
        return this
    }

}