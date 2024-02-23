package dev.itsyourap.newsapp.di

import android.app.Application
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.itsyourap.newsapp.data.manager.LocalUserManagerImpl
import dev.itsyourap.newsapp.data.remote.NewsApi
import dev.itsyourap.newsapp.data.repository.NewsRepositoryImpl
import dev.itsyourap.newsapp.domain.manager.LocalUserManager
import dev.itsyourap.newsapp.domain.repository.NewsRepository
import dev.itsyourap.newsapp.domain.usecases.app_entry.AppEntryUseCases
import dev.itsyourap.newsapp.domain.usecases.app_entry.ReadAppEntry
import dev.itsyourap.newsapp.domain.usecases.app_entry.SaveAppEntry
import dev.itsyourap.newsapp.domain.usecases.news.GetNews
import dev.itsyourap.newsapp.domain.usecases.news.NewsUseCases
import dev.itsyourap.newsapp.domain.usecases.news.SearchNews
import dev.itsyourap.newsapp.util.Constants
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideLocalUserManager(
        application: Application
    ): LocalUserManager = LocalUserManagerImpl(application)

    @Provides
    @Singleton
    fun provideAppEntryUseCases(
        localUserManager: LocalUserManager
    ) = AppEntryUseCases(
        readAppEntry = ReadAppEntry(localUserManager),
        saveAppEntry = SaveAppEntry(localUserManager)
    )

    @Provides
    @Singleton
    fun provideNewsApi(): NewsApi =
        Retrofit.Builder()
            .baseUrl(Constants.NEWS_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(NewsApi::class.java)


    @Provides
    @Singleton
    fun provideNewsRepository(
        newsApi: NewsApi
    ): NewsRepository = NewsRepositoryImpl(newsApi)

    @Provides
    @Singleton
    fun provideNewsUseCases(
        newsRepository: NewsRepository
    ): NewsUseCases = NewsUseCases(
        getNews = GetNews(newsRepository),
        searchNews = SearchNews(newsRepository)
    )
}