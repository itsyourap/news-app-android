package dev.itsyourap.newsapp.domain.usecases.news

import dev.itsyourap.newsapp.domain.model.Article
import dev.itsyourap.newsapp.domain.repository.NewsRepository

class GetArticle(
    private val newsRepository: NewsRepository
) {
    suspend operator fun invoke(url: String): Article? {
        return newsRepository.selectArticle(url = url)
    }
}