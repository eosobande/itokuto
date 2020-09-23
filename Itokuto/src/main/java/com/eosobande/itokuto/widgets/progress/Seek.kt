package com.eosobande.itokuto.widgets.progress

import android.content.Context
import android.widget.SeekBar
import com.eosobande.itokuto.modifiers.ProgressBarWidget

class Seek(override val context: Context) :
    ProgressBarWidget.Modifier<SeekBar, Seek>(SeekBar(context)) {
}