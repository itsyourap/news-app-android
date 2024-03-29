package dev.itsyourap.newsapp.di

import android.app.Application
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.itsyourap.newsapp.data.local.NewsDao
import dev.itsyourap.newsapp.data.local.NewsDatabase
import dev.itsyourap.newsapp.data.local.NewsTypeConverter
import dev.itsyourap.newsapp.data.manager.LocalUserManagerImpl
import dev.itsyourap.newsapp.data.remote.NewsApi
import dev.itsyourap.newsapp.data.repository.NewsRepositoryImpl
import dev.itsyourap.newsapp.domain.manager.LocalUserManager
import dev.itsyourap.newsapp.domain.repository.NewsRepository
import dev.itsyourap.newsapp.domain.usecases.app_entry.AppEntryUseCases
import dev.itsyourap.newsapp.domain.usecases.app_entry.ReadAppEntry
import dev.itsyourap.newsapp.domain.usecases.app_entry.SaveAppEntry
import dev.itsyourap.newsapp.domain.usecases.news.DeleteArticle
import dev.itsyourap.newsapp.domain.usecases.news.GetArticle
import dev.itsyourap.newsapp.domain.usecases.news.GetNews
import dev.itsyourap.newsapp.domain.usecases.news.NewsUseCases
import dev.itsyourap.newsapp.domain.usecases.news.SearchNews
import dev.itsyourap.newsapp.domain.usecases.news.SelectArticles
import dev.itsyourap.newsapp.domain.usecases.news.UpsertArticle
import dev.itsyourap.newsapp.util.Constants
import dev.itsyourap.newsapp.util.Constants.NEWS_DATABASE_NAME
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
    ): AppEntryUseCases = AppEntryUseCases(
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
        newsApi: NewsApi,
        newsDao: NewsDao
    ): NewsRepository = NewsRepositoryImpl(newsApi, newsDao)

    @Provides
    @Singleton
    fun provideNewsUseCases(
        newsRepository: NewsRepository,
    ): NewsUseCases = NewsUseCases(
        getNews = GetNews(newsRepository),
        searchNews = SearchNews(newsRepository),
        deleteArticle = DeleteArticle(newsRepository),
        upsertArticle = UpsertArticle(newsRepository),
        selectArticles = SelectArticles(newsRepository),
        selectArticle = GetArticle(newsRepository)
    )

    @Provides
    @Singleton
    fun provideNewsDatabase(
        application: Application
    ): NewsDatabase = Room.databaseBuilder(
        context = application,
        klass = NewsDatabase::class.java,
        name = NEWS_DATABASE_NAME
    ).addTypeConverter(NewsTypeConverter())
        .fallbackToDestructiveMigration()
        .build()

    @Provides
    @Singleton
    fun provideNewsDao(
        newsDatabase: NewsDatabase
    ): NewsDao = newsDatabase.newsDao
}