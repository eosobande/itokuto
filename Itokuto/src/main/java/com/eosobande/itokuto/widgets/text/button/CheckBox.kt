package com.eosobande.itokuto.widgets.text.button

import android.content.Context
import com.eosobande.itokuto.State
import com.eosobande.itokuto.modifiers.ButtonWidget

data class CheckBox(override val context: Context) :
    ButtonWidget.Modifier<android.widget.CheckBox, CheckBox>(android.widget.CheckBox(context)) {

    constructor(context: Context, text: State<String>) : this(context) {
        text(text)
    }

    constructor(context: Context, text: String = "") : this(context) {
        text(text)
    }

}