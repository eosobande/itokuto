package com.eosobande.itokuto.modifiers

import android.content.res.ColorStateList
import android.graphics.Typeface
import android.os.Build
import android.os.LocaleList
import android.text.TextUtils
import android.util.TypedValue
import android.widget.TextView
import androidx.annotation.ColorInt
import androidx.annotation.IntRange
import androidx.annotation.RequiresApi
import androidx.annotation.StyleRes
import androidx.core.widget.TextViewCompat
import com.eosobande.itokuto.State
import com.eosobande.itokuto.widgets.text.TextAppearance
import java.util.*


interface TextWidget : Widget {

    abstract class Modifier<V : TextView, T : Modifier<V, T>>(view: V) :
        Widget.Modifier<V, T>(view), TextWidget {

        val text: String get() = view.text.toString()

        fun includeFontPadding(include: Boolean = true) = view { includeFontPadding = include }

        fun font(font: Typeface?) = this { view.typeface = font }

        fun font(
            font: Typeface?,
            @IntRange(from = 0, to = 3) style: Int = Typeface.NORMAL
        ) = this { view.setTypeface(font, style) }

        fun fontStyle(@IntRange(from = 0, to = 3) style: Int) =
            this { view.setTypeface(view.typeface, style) }

        fun shadow(
            @ColorInt color: Int,
            radius: Float = 0f,
            dx: Float = 0f,
            dy: Float = 0f
        ) = this { view.setShadowLayer(radius, dx, dy, color) }

        fun text(state: State<String>): T = this {
            text(state())
            state { if (it != text) text(it) }
        }

        fun lineSpacing(multiplier: Float, extra: Float) = this {
            view.setLineSpacing(extra, multiplier)
        }

        fun text(text: CharSequence?) = this { view.text = text }

        fun textAppearance(@StyleRes style: Int) = this {
            TextViewCompat.setTextAppearance(view, style)
        }

        fun textAppearance(appearance: TextAppearance) = this {

            appearance.apply {
                allCaps?.let { capitalize(it) }
                font?.let { font(it) }
                fontStyle?.let { fontStyle(it) }
                fontSize?.let { fontSize(it) }
                includeFontPadding?.let { includeFontPadding(it) }
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    elegant?.let { elegantTextHeight(it) }
                    fontFeatureSettings?.let { fontFeatureSettings(it) }
                    letterSpacing?.let { letterSpacing(it) }
                }
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                    fallbackLineSpacing?.let { fallbackLineSpacing(it) }
                    fontWeight?.let { fontWeight(it) }
                }
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
//                    textLocales?.let { textLocales(it) }
                }
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    fontVariationSettings?.let { fontVariationSettings(it) }
                }
                if (lineSpacingExtra != null && lineSpacingMultiplier != null) {
                    lineSpacing(lineSpacingMultiplier, lineSpacingExtra)
                }
                shadowColor?.let { shadow(it, shadowRadius, shadowDx, shadowDy) }
                textColor?.let { textColor(it) }
                textColorHint?.let { textColorHint(it) }
                textColorHighlight?.let { textColorHighLight(it) }
                textColorLink?.let { textColorLink(it) }
            }

        }

        fun bold() = this { fontStyle(Typeface.BOLD) }
        fun boldItalic() = this { fontStyle(Typeface.BOLD_ITALIC) }
        fun italic() = this { fontStyle(Typeface.ITALIC) }
        fun normal() = this { fontStyle(Typeface.NORMAL) }

        @RequiresApi(Build.VERSION_CODES.P)
        fun fontWeight(@IntRange(from = 1, to = 1000) weight: Int) =
            this { view.typeface.let { font(Typeface.create(it, weight, it.isItalic)) } }

        fun capitalize(caps: Boolean = true) = this { view.isAllCaps = caps }

        @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
        fun elegantTextHeight(elegant: Boolean = true) = this { view.isElegantTextHeight = elegant }

        @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
        fun fontFeatureSettings(settings: String) = this { view.fontFeatureSettings = settings }

        @RequiresApi(Build.VERSION_CODES.O)
        fun fontVariationSettings(settings: String) = this { view.fontVariationSettings = settings }

        @RequiresApi(Build.VERSION_CODES.P)
        fun fallbackLineSpacing(enabled: Boolean = true) =
            this { view.isFallbackLineSpacing = enabled }

        fun lines(lines: Int) = this { view.setLines(lines) }

        @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
        fun letterSpacing(spacing: Float) = this { view.letterSpacing = spacing }

        fun singleLine(singleLine: Boolean = true) = this { view.isSingleLine = singleLine }

        fun ellipsize(ellipsize: TextUtils.TruncateAt = TextUtils.TruncateAt.END) =
            this { view.ellipsize = ellipsize }

        fun maxLines(lines: Int) = this { view.maxLines = lines }

        fun imeOptions(imeOptions: Int) = this { view.imeOptions = imeOptions }

        fun textColor(color: ColorStateList?) = this { view.setTextColor(color) }
        fun textColorHint(color: ColorStateList?) = this { view.setHintTextColor(color) }
        fun textColorHighLight(@ColorInt color: Int) = this { view.highlightColor = color }
        fun textColorLink(color: ColorStateList?) = this { view.setLinkTextColor(color) }

        @RequiresApi(Build.VERSION_CODES.N)
        fun textLocales(locales: LocaleList) = this { view.textLocales = locales }

        @RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
        fun textLocale(locale: Locale) = this { view.textLocale = locale }

        fun fontSize(size: Float) = this { view.setTextSize(TypedValue.COMPLEX_UNIT_PX, size) }

        fun gravity(gravity: Int) = this { view.gravity = gravity }

    }

}