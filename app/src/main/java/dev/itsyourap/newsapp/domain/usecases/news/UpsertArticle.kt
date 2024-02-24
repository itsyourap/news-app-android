package dev.itsyourap.newsapp.domain.usecases.news

import dev.itsyourap.newsapp.domain.model.Article
import dev.itsyourap.newsapp.domain.repository.NewsRepository

class UpsertArticle(
    private val newsRepository: NewsRepository
) {
    suspend operator fun invoke(article: Article){
        newsRepository.upsertArticle(article)
    }
}