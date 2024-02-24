package dev.itsyourap.newsapp.presentation.bookmark

import dev.itsyourap.newsapp.domain.model.Article

data class BookmarkState(
    val articles: List<Article> = emptyList()
)