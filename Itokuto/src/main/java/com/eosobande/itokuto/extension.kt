package com.eosobande.itokuto

import android.content.res.ColorStateList
import android.content.res.Resources
import android.util.TypedValue
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.eosobande.itokuto.modifiers.Widget


fun AppCompatActivity.setContentView(modifier: Widget.Modifier<*, *>) =
    setContentView(modifier.view)

fun AppCompatActivity.setContentView(widget: Widget) = setContentView(widget.modifier)

fun Widget.view() = modifier.view

private val displayMetrics by lazy { Resources.getSystem().displayMetrics }

internal val Widget.modifier: Widget.Modifier<*, *>
    get() {
        var layout = widget
        while (layout !is Widget.Modifier<*, *>) {
            layout = layout.widget
        }
        return layout
    }

val Int.dp: Int
    get() = TypedValue
        .applyDimension(TypedValue.COMPLEX_UNIT_DIP, toFloat(), displayMetrics)
        .toInt()

val Float.dp: Float
    get() = TypedValue
        .applyDimension(TypedValue.COMPLEX_UNIT_DIP, this, displayMetrics)

val Number.sp: Float
    get() =
        TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_SP,
            toFloat(),
            displayMetrics
        )

val Int.colorState: ColorStateList
    get() = ColorStateList.valueOf(this)


fun <T : ViewGroup.MarginLayoutParams> T.margin(size: Int): T {
    setMargins(size, size, size, size)
    return this
}

fun <T : ViewGroup.MarginLayoutParams> T.margin(
    start: Int? = null,
    top: Int? = null,
    end: Int? = null,
    bottom: Int? = null
): T {
    setMargins(
        start ?: leftMargin,
        top ?: topMargin,
        end ?: rightMargin,
        bottom ?: bottomMargin
    )
    return this
}

fun <T : ViewGroup.MarginLayoutParams> T.margin(horizontal: Int? = null, vertical: Int? = null): T {
    setMargins(
        horizontal ?: leftMargin,
        vertical ?: topMargin,
        horizontal ?: rightMargin,
        vertical ?: bottomMargin
    )
    return this
}


