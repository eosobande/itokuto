package com.eosobande.itokuto.widgets.text

import android.content.Context
import android.net.Uri
import android.os.Build
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputConnection
import androidx.appcompat.widget.AppCompatEditText
import androidx.core.view.inputmethod.EditorInfoCompat
import androidx.core.view.inputmethod.InputConnectionCompat
import androidx.lifecycle.Observer

class RichEditText(context: Context) : AppCompatEditText(context) {

    var richContentUriListener: Observer<Uri>? = null

    override fun onCreateInputConnection(editorInfo: EditorInfo): InputConnection {
        val inputConnection = super.onCreateInputConnection(editorInfo)
        EditorInfoCompat.setContentMimeTypes(
            editorInfo, arrayOf("image/png", "image/gif", "image/jpeg", "image/webp")
        )
        return InputConnectionCompat.createWrapper(
            inputConnection, editorInfo,
            InputConnectionCompat.OnCommitContentListener { inputContentInfo, flags, _ ->
                try {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N_MR1 &&
                        flags and InputConnectionCompat.INPUT_CONTENT_GRANT_READ_URI_PERMISSION != 0
                    ) {
                        inputContentInfo.requestPermission()
                    }
                    richContentUriListener?.onChanged(inputContentInfo.contentUri)
                } catch (e: Exception) {
                    return@OnCommitContentListener false
                }
                true
            })
    }

}