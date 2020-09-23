package com.eosobande.itokuto.widgets.text

import android.content.res.ColorStateList
import android.graphics.Typeface
import androidx.annotation.ColorInt
import androidx.annotation.IntRange

data class TextAppearance(
    val allCaps: Boolean? = null,
    val elegant: Boolean? = null,
    val fallbackLineSpacing: Boolean? = null,
    val fontFeatureSettings: String? = null,
    val font: Typeface? = null,
    @IntRange(from = 0, to = 3) val fontStyle: Int? = null,
    val fontSize: Float? = null,
    val fontVariationSettings: String? = null,
    val includeFontPadding: Boolean? = null,
    @IntRange(from = 1, to = 1000) val fontWeight: Int? = null,
    val letterSpacing: Float? = null,
    val lineSpacingExtra: Float? = null,
    val lineSpacingMultiplier: Float? = null,
    @ColorInt val shadowColor: Int? = null,
    val shadowDx: Float = 0f,
    val shadowDy: Float = 0f,
    val shadowRadius: Float = 0f,
    val textColor: ColorStateList? = null,
    val textColorHint: ColorStateList? = null,
    @ColorInt val textColorHighlight: Int? = null,
    val textColorLink: ColorStateList? = null
//    val textLocales: LocaleList? = null
)

