package dev.itsyourap.newsapp.presentation.details

import dev.itsyourap.newsapp.domain.model.Article

sealed class DetailsEvent {
    data class UpsertDeleteArticle(val article: Article): DetailsEvent()
    data object RemoveSideEffect: DetailsEvent()
}