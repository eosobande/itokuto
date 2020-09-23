package com.eosobande.itokuto.widgets.text

import android.content.Context
import android.widget.TextView
import com.eosobande.itokuto.State
import com.eosobande.itokuto.modifiers.TextWidget

data class Text(override val context: Context) :
    TextWidget.Modifier<TextView, Text>(TextView(context)) {

    constructor(context: Context, text: CharSequence) : this(context) {
        text(text)
    }

    constructor(context: Context, text: State<String>) : this(context) {
        text(text)
    }

}