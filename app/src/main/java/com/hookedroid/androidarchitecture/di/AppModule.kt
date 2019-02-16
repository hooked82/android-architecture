package com.hookedroid.androidarchitecture.di

import android.app.Application
import androidx.room.Room
import com.hookedroid.androidarchitecture.BuildConfig
import com.hookedroid.androidarchitecture.api.CharacterApi
import com.hookedroid.androidarchitecture.api.LocationApi
import com.hookedroid.androidarchitecture.data.dao.CharacterDao
import com.hookedroid.androidarchitecture.data.dao.LocationDao
import com.hookedroid.androidarchitecture.data.db.ArchDb
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import dagger.Module
import dagger.Provides
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module(includes = [RepositoryModule::class, ViewModelModule::class])
open class AppModule {
    @Singleton
    @Provides
    open fun provideDb(app: Application): ArchDb {
        return Room.databaseBuilder(app, ArchDb::class.java, "arch.db")
                .fallbackToDestructiveMigration()
                .build()
    }

    @Singleton
    @Provides
    open fun provideCharacterDao(db: ArchDb): CharacterDao {
        return db.characterDao()
    }

    @Singleton
    @Provides
    open fun provideLocationDao(db: ArchDb): LocationDao {
        return db.locationDao()
    }

    @Singleton
    @Provides
    open fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.API_URL)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
            .addConverterFactory(GsonConverterFactory.create())
            .client(getOkHttpClient())
            .build()
    }

    @Singleton
    @Provides
    fun provideCharacterApi(retrofit: Retrofit): CharacterApi {
        return retrofit.create(CharacterApi::class.java)
    }

    @Singleton
    @Provides
    fun provideLocationApi(retrofit: Retrofit): LocationApi {
        return retrofit.create(LocationApi::class.java)
    }

    private fun getOkHttpClient(): OkHttpClient {
        val client = OkHttpClient.Builder()

        @Suppress("ConstantConditionIf")
        if (BuildConfig.DEBUG) {
            // Add Http call logging
            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BODY
            client.addInterceptor(interceptor)
        }

        return client.build()
    }
}