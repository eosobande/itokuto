package com.eosobande.itokuto.widgets.stacks

import android.content.Context
import android.graphics.drawable.Drawable
import android.widget.AdapterView
import android.widget.Spinner
import android.widget.SpinnerAdapter
import androidx.annotation.DrawableRes
import com.eosobande.itokuto.HasGravity
import com.eosobande.itokuto.modifiers.StackWidget


data class DropDown(override val context: Context) :
    StackWidget.Modifier<Spinner, DropDown>(Spinner(context)), HasGravity<DropDown> {

    override fun gravity(gravity: Int) = this { view.gravity = gravity }

    fun itemSelectedListener(listener: AdapterView.OnItemSelectedListener): DropDown {
        view.onItemSelectedListener = listener
        return this
    }

    fun setPopupBackgroundDrawable(background: Drawable?) {
        view.setPopupBackgroundDrawable(background)
    }

    fun setPopupBackgroundResource(@DrawableRes resId: Int) {
        view.setPopupBackgroundResource(resId)
    }

    fun adapter(adapter: SpinnerAdapter): DropDown {
        view.adapter = adapter
        return this
    }

}