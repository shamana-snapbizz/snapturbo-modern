package com.snapbizz.snapturbo.commons.utils.extensions_functions

import android.content.Context
import android.widget.Toast
import androidx.compose.ui.focus.FocusRequester


fun Context.toastShort(message: String) =
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()

fun Context.toastLong(message: String) =
    Toast.makeText(this, message, Toast.LENGTH_LONG).show()

