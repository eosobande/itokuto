package com.eosobande.itokuto.widgets.image

import android.content.Context
import android.graphics.drawable.Drawable
import com.eosobande.itokuto.State
import com.eosobande.itokuto.modifiers.ImageWidget

class ImageButton(override val context: Context) :
    ImageWidget.Modifier<android.widget.ImageButton, ImageButton>(android.widget.ImageButton(context)) {

    constructor(context: Context, image: Drawable? = null) : this(context) {
        image(image)
    }

    constructor(context: Context, image: State<Drawable?>) : this(context) {
        image(image)
    }

}