package com.eosobande.itokuto.widgets.image

import android.content.Context
import android.graphics.drawable.Drawable
import android.widget.ImageView
import com.eosobande.itokuto.State
import com.eosobande.itokuto.modifiers.ImageWidget


class Image(override val context: Context) :
    ImageWidget.Modifier<ImageView, Image>(ImageView(context)) {

    constructor(context: Context, image: Drawable? = null) : this(context) {
        image(image)
    }

    constructor(context: Context, image: State<Drawable?>) : this(context) {
        image(image)
    }

}