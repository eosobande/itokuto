package com.eosobande.itokuto

import androidx.databinding.Observable
import androidx.databinding.ObservableField


class State<T> : ObservableField<T> {

    constructor() : super()
    constructor(value: T) : super(value)

    inline operator fun invoke(crossinline block: (T) -> Unit) =
        apply {
            addOnPropertyChangedCallback(object : Observable.OnPropertyChangedCallback() {
                override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
                    @Suppress("UNCHECKED_CAST")
                    block(get())
                }
            })
        }

    operator fun invoke(value: T) = set(value)

    operator fun invoke() = get()

    @Suppress("UNCHECKED_CAST")
    override fun get() = super.get() as T

    override fun toString() = get().toString()

}

