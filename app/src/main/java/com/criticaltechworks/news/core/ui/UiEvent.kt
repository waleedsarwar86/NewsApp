package com.criticaltechworks.news.core.ui

abstract class UiEvent {
    object GoBack : UiEvent()
    class ShowMessage(val message: String) : UiEvent()
}
