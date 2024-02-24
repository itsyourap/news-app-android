package dev.itsyourap.newsapp.presentation.details

sealed class DetailsEvent {
    data object SaveArticleEvent: DetailsEvent()
}