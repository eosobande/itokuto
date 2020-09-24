package com.eosobande.itokuto.widgets.stacks.linear

import android.content.Context
import android.widget.LinearLayout
import com.eosobande.itokuto.modifiers.LinearStack

class HStack(override val context: Context) :
    LinearStack.Modifier<LinearLayout, HStack>(LinearLayout(context)) {

    init {
        view.orientation = LinearLayout.HORIZONTAL
    }

}
