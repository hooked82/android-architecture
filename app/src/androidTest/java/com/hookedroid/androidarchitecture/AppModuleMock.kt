package com.hookedroid.androidarchitecture

import com.hookedroid.androidarchitecture.di.AppModule
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import dagger.Provides
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import javax.inject.Singleton

class AppModuleMock : AppModule() {

    @Singleton
    @Provides
    override fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://www.someotherurl.com")
            .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
            .client(OkHttpClient.Builder().build())
            .build()
    }
}