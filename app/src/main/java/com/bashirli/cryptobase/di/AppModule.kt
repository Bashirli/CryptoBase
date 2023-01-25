package com.bashirli.cryptobase.di

import com.bashirli.cryptobase.repo.DownloadRepo
import com.bashirli.cryptobase.repo.Repo
import com.bashirli.cryptobase.service.Service
import com.bashirli.cryptobase.util.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun injectService()=
        Retrofit.Builder().
        baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
            .build()
        .create(Service::class.java)

    @Singleton
    @Provides
    fun injectRepo(service: Service)=DownloadRepo(service) as Repo


}