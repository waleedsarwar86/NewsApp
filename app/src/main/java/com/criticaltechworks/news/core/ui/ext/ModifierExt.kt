package com.criticaltechworks.news.core.ui.ext

import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.PointerEventPass
import androidx.compose.ui.input.pointer.PointerEventType
import androidx.compose.ui.input.pointer.pointerInput
import kotlinx.coroutines.currentCoroutineContext
import kotlinx.coroutines.isActive

fun Modifier.userInteractionNotification(onInteracted: () -> Unit): Modifier {
    return pointerInput(onInteracted) {
        val currentContext = currentCoroutineContext()
        awaitPointerEventScope {
            while (currentContext.isActive) {
                val event = awaitPointerEvent(PointerEventPass.Initial)
                // if user taps (down) or scrolls - consider it an interaction signal
                if (
                    event.type == PointerEventType.Press || event.type == PointerEventType.Scroll
                ) {
                    onInteracted.invoke()
                }
            }
        }
    }
}