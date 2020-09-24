package com.eosobande.itokuto.widgets.text.button

import android.content.Context
import com.eosobande.itokuto.State
import com.eosobande.itokuto.modifiers.ButtonWidget

class Button(override val context: Context) :
    ButtonWidget.Modifier<android.widget.Button, Button>(android.widget.Button(context)) {

    constructor(context: Context, text: State<String>) : this(context) {
        text(text)
    }

    constructor(context: Context, text: String = "") : this(context) {
        text(text)
    }

}