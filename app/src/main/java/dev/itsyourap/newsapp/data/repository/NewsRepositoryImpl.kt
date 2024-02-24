package dev.itsyourap.newsapp.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import dev.itsyourap.newsapp.data.local.NewsDao
import dev.itsyourap.newsapp.data.remote.NewsApi
import dev.itsyourap.newsapp.data.remote.NewsPagingSource
import dev.itsyourap.newsapp.data.remote.SearchNewsPagingSource
import dev.itsyourap.newsapp.domain.model.Article
import dev.itsyourap.newsapp.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow

class NewsRepositoryImpl(
    private val newsApi: NewsApi,
    private val newsDao: NewsDao
) : NewsRepository {
    override fun getNews(sources: List<String>): Flow<PagingData<Article>> {
        return Pager(config = PagingConfig(pageSize = 10), pagingSourceFactory = {
            NewsPagingSource(
                newsApi = newsApi, sources = sources.joinToString(separator = ",")
            )
        }).flow
    }

    override fun searchNews(searchQuery: String, sources: List<String>): Flow<PagingData<Article>> {
        return Pager(config = PagingConfig(pageSize = 10), pagingSourceFactory = {
            SearchNewsPagingSource(
                newsApi = newsApi,
                searchQuery = searchQuery,
                sources = sources.joinToString(separator = ",")
            )
        }).flow
    }

    override suspend fun upsertArticle(article: Article) {
        newsDao.upsert(article)
    }

    override suspend fun deleteArticle(article: Article) {
        newsDao.delete(article)
    }

    override fun selectArticles(): Flow<List<Article>> {
        return newsDao.getArticles()
    }

    override suspend fun selectArticle(url: String): Article? {
        return newsDao.getArticle(url)
    }
}