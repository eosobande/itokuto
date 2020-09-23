package com.eosobande.itokuto.modifiers

import android.widget.Button

interface ButtonWidget : TextWidget {

    abstract class Modifier<V : Button, T : Modifier<V, T>>(view: V) :
        TextWidget.Modifier<V, T>(view), ButtonWidget

}