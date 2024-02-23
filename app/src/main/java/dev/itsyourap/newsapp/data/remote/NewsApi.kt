package dev.itsyourap.newsapp.data.remote

import dev.itsyourap.newsapp.data.remote.dto.NewsResponse
import dev.itsyourap.newsapp.util.Constants
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {
    @GET("everything")
    suspend fun getNews(
        @Query("page") page: Int,
        @Query("sources") sources: String,
        @Query("apiKey") apiKey: String = Constants.NEWS_API_KEY
    ): NewsResponse

    @GET("everything")
    suspend fun searchNews(
        @Query("q") searchQuery: String,
        @Query("page") page: Int,
        @Query("sources") sources: String,
        @Query("apiKey") apiKey: String = Constants.NEWS_API_KEY
    ): NewsResponse
}