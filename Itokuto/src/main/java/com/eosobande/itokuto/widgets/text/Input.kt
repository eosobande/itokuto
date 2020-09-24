package com.eosobande.itokuto.widgets.text

import android.content.Context
import android.widget.EditText
import com.eosobande.itokuto.State
import com.eosobande.itokuto.modifiers.InputWidget

class Input(
    override val context: Context,
    richContent: Boolean = false
) : InputWidget.Modifier<EditText, Input>(
    if (richContent) RichEditText(context) else EditText(context)
) {

    constructor(
        context: Context,
        text: String,
        hint: String? = null,
        richContent: Boolean = false
    ) : this(context, richContent) {
        text(text)
        hint(hint)
    }

    constructor(
        context: Context,
        text: State<String>,
        hint: String? = null,
        richContent: Boolean = false
    ) : this(context, richContent) {
        text(text)
        hint(hint)
    }

}