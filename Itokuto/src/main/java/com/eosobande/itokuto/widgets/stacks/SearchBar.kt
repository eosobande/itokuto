package com.eosobande.itokuto.widgets.stacks

import android.content.Context
import androidx.appcompat.widget.SearchView
import com.eosobande.itokuto.modifiers.LinearStackX

data class SearchBar(override val context: Context) :
    LinearStackX.Modifier<SearchView, SearchBar>(SearchView(context))
