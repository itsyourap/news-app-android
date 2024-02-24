package dev.itsyourap.newsapp.domain.usecases.news

import dev.itsyourap.newsapp.data.local.NewsDao
import dev.itsyourap.newsapp.domain.model.Article

class UpsertArticle(
    private val newsDao: NewsDao
) {
    suspend operator fun invoke(article: Article){
        newsDao.upsert(article)
    }
}