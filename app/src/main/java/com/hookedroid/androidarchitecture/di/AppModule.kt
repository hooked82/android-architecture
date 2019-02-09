package com.hookedroid.androidarchitecture.di

import android.app.Application
import androidx.room.Room
import com.hookedroid.androidarchitecture.BuildConfig
import com.hookedroid.androidarchitecture.data.dao.NoteDao
import com.hookedroid.androidarchitecture.data.db.ArchDb
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import dagger.Module
import dagger.Provides
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import javax.inject.Singleton

@Module(includes = [RepositoryModule::class, ViewModelModule::class])
class AppModule {
    @Singleton
    @Provides
    fun provideDb(app: Application): ArchDb {
        return Room.databaseBuilder(app, ArchDb::class.java, "arch.db")
                .fallbackToDestructiveMigration()
                .build()
    }

    @Singleton
    @Provides
    fun provideNoteDao(db: ArchDb): NoteDao {
        return db.noteDao()
    }

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
                .baseUrl(BuildConfig.API_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
                .client(getOkHttpClient())
                .build()
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