package com.eosobande.itokuto.widgets.stacks.linear

import android.content.Context
import android.widget.LinearLayout
import com.eosobande.itokuto.modifiers.LinearStack

class VStack(override val context: Context) :
    LinearStack.Modifier<LinearLayout, VStack>(LinearLayout(context)) {

    init {
        view.orientation = LinearLayout.VERTICAL
    }

}
