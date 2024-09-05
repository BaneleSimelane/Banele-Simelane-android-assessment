package com.glucode.about_you.di

import com.glucode.about_you.about.data.AboutRepositoryImpl
import com.glucode.about_you.about.data.mapper.EngineerToAbout
import com.glucode.about_you.about.domain.repository.AboutRepository
import com.glucode.about_you.about.domain.usecase.GetEngineer
import com.glucode.about_you.about.domain.usecase.UpdateImageName
import com.glucode.about_you.engineers.data.EngineerRepositoryImpl
import com.glucode.about_you.engineers.data.mapper.EngineerToEngineerData
import com.glucode.about_you.engineers.domain.repository.EngineerRepository
import com.glucode.about_you.engineers.domain.usecase.GetEngineers
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object TestAboutYouModule {

    @Provides
    @Singleton
    fun provideEngineerRepository(
        mapper: EngineerToEngineerData
    ): EngineerRepository {
        return EngineerRepositoryImpl(
            mapper
        )
    }

    @Provides
    @Singleton
    fun provideGetEngineers(repository: EngineerRepository): GetEngineers {
        return GetEngineers(repository)
    }

    @Provides
    @Singleton
    fun provideAboutRepository(
        mapper: EngineerToAbout
    ): AboutRepository {
        return AboutRepositoryImpl(
            mapper
        )
    }

    @Provides
    @Singleton
    fun provideGetEngineer(repository: AboutRepository): GetEngineer {
        return GetEngineer(repository)
    }

    @Provides
    @Singleton
    fun provideUpdateImageName(repository: AboutRepository): UpdateImageName {
        return UpdateImageName(repository)
    }
}