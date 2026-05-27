package com.snapbizz.snapturbo.ui.snackbar

import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow

object SnackbarManager {

    private val _messages = MutableSharedFlow<SnackbarEvent>(
        extraBufferCapacity = 1
    )

    val messages = _messages.asSharedFlow()

    fun show(
        message: String,
        type: SnackbarType = SnackbarType.INFO,
        position: SnackbarPosition = SnackbarPosition.BOTTOM,
        actionLabel: String? = null,
        onAction: (() -> Unit)? = null
    ) {
        _messages.tryEmit(
            SnackbarEvent(
                message = message,
                type = type,
                position = position,
                actionLabel = actionLabel,
                onAction = onAction
            )
        )
    }
}

data class SnackbarEvent(
    val message: String,
    val type: SnackbarType = SnackbarType.INFO,
    val position: SnackbarPosition = SnackbarPosition.BOTTOM,
    val actionLabel: String? = null,
    val onAction: (() -> Unit)? = null
)

enum class SnackbarType {
    ERROR,
    SUCCESS,
    INFO
}

enum class SnackbarPosition {
    TOP,
    BOTTOM,
    CENTER
}