package com.eosobande.itokuto.modifiers

import android.content.res.ColorStateList
import android.graphics.drawable.Drawable
import android.net.Uri
import android.widget.ImageView
import androidx.core.net.toUri
import androidx.core.widget.ImageViewCompat
import com.eosobande.itokuto.State

interface ImageWidget : Widget {

    abstract class Modifier<V : ImageView, T : Modifier<V, T>>(view: V) :
        Widget.Modifier<V, T>(view), ImageWidget {

        fun image(uri: String?): T = image(uri?.toUri())
        fun image(uri: Uri?): T = this { view.setImageURI(uri) }
        fun image(drawable: Drawable?): T = this { view.setImageDrawable(drawable) }
        fun image(drawable: State<Drawable?>): T = this {
            image(drawable())
            drawable { image(it) }
        }

        fun imageUri(uri: State<Uri?>) = this {
            image(uri())
            uri { image(it) }
        }

        fun imageTint(stateList: ColorStateList?) = this {
            ImageViewCompat.setImageTintList(view, stateList)
        }

        fun scale(type: ImageView.ScaleType?) = this { view.scaleType = type }

        fun adjustBounds(adjust: Boolean = true) = this { view.adjustViewBounds = adjust }

    }

}
