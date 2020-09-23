package com.eosobande.itokuto.modifiers

import android.net.Uri
import android.text.InputFilter
import android.widget.EditText
import androidx.core.widget.doAfterTextChanged
import androidx.lifecycle.Observer
import com.eosobande.itokuto.State
import com.eosobande.itokuto.widgets.text.RichEditText

interface InputWidget : TextWidget {

    abstract class Modifier<V : EditText, T : Modifier<V, T>>(view: V) :
        TextWidget.Modifier<V, T>(view), InputWidget {

        fun richContentUriListener(listener: Observer<Uri>?) = view {
            require(this is RichEditText)
            richContentUriListener = listener
        }

        fun hint(hint: String?) = view { this.hint = hint }

        fun bind(state: State<String>) = afterTextChanged { state(it) }

        fun inputType(type: Int, raw: Boolean = false) = view {
            if (raw) setRawInputType(type) else inputType = type
        }

        fun maxLength(maxLength: Int) = view {
            filters = arrayOf(InputFilter.LengthFilter(maxLength))
        }

        fun selectOnFocus(select: Boolean = true) = view { setSelectAllOnFocus(select) }

        inline fun afterTextChanged(crossinline block: (String) -> Unit) = view {
            doAfterTextChanged {
                block(it?.toString() ?: "")
            }
        }

    }

}