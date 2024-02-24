package dev.itsyourap.newsapp.domain.usecases.news

import dev.itsyourap.newsapp.domain.model.Article
import dev.itsyourap.newsapp.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow

class SelectArticles(
    private val newsRepository: NewsRepository
) {
    operator fun invoke(): Flow<List<Article>> {
        return newsRepository.selectArticles()
    }
}