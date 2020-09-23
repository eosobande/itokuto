package com.eosobande.itokuto.modifiers

import android.animation.Animator
import android.animation.AnimatorInflater
import android.animation.StateListAnimator
import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Typeface
import android.graphics.drawable.Drawable
import android.os.Build
import androidx.annotation.*
import androidx.annotation.IntRange
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat

interface WidgetBuilder {

    val context: Context

    fun colorStateList(@ColorInt color: Int): ColorStateList = ColorStateList.valueOf(color)

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    fun stateListAnimator(@AnimatorRes animator: Int): StateListAnimator =
        AnimatorInflater.loadStateListAnimator(context, animator)

    fun animator(@AnimatorRes animator: Int): Animator =
        AnimatorInflater.loadAnimator(context, animator)

    fun drawable(@DrawableRes drawable: Int): Drawable? =
        ContextCompat.getDrawable(context, drawable)

    fun string(@StringRes string: Int) = context.getString(string)
    fun string(@StringRes string: Int, vararg args: Any) = context.getString(string, *args)
    fun array(@ArrayRes array: Int): Array<String> = context.resources.getStringArray(array)
    fun font(@FontRes font: Int): Typeface = ResourcesCompat.getFont(context, font)!!
    fun font(@FontRes font: Int, style: Int = Typeface.NORMAL): Typeface =
        Typeface.create(font(font), style)

    @RequiresApi(Build.VERSION_CODES.P)
    fun font(
        @FontRes font: Int,
        @IntRange(from = 1, to = 1000) weight: Int,
        italic: Boolean = false
    ): Typeface = Typeface.create(font(font), weight, italic)

}