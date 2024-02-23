package dev.itsyourap.newsapp.data.remote.dto

import dev.itsyourap.newsapp.domain.model.Article

data class NewsResponse(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)