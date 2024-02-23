package dev.itsyourap.newsapp.di

import android.app.Application
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.itsyourap.newsapp.data.manager.LocalUserManagerImpl
import dev.itsyourap.newsapp.domain.manager.LocalUserManager
import dev.itsyourap.newsapp.domain.usecases.AppEntryUseCases
import dev.itsyourap.newsapp.domain.usecases.ReadAppEntry
import dev.itsyourap.newsapp.domain.usecases.SaveAppEntry
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
}