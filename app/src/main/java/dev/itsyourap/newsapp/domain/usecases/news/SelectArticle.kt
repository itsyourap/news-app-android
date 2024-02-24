package dev.itsyourap.newsapp.domain.usecases.news

import dev.itsyourap.newsapp.data.local.NewsDao
import dev.itsyourap.newsapp.domain.model.Article
import kotlinx.coroutines.flow.Flow

class SelectArticle(
    private val newsDao: NewsDao
) {
    operator fun invoke(): Flow<List<Article>> {
        return newsDao.getArticles()
    }
}