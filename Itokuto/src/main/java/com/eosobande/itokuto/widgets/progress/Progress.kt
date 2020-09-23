package com.eosobande.itokuto.widgets.progress

import android.content.Context
import android.widget.ProgressBar
import com.eosobande.itokuto.modifiers.ProgressBarWidget

class Progress(override val context: Context) :
    ProgressBarWidget.Modifier<ProgressBar, Progress>(ProgressBar(context)) {
}