package com.hookedroid.androidarchitecture.di

import android.app.Application
import com.hookedroid.androidarchitecture.ArchApplication
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AndroidInjectionModule::class,
    AppModule::class,
    MainActivityModule::class
])
interface AppComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder
        @BindsInstance
        fun appModule(appModule: AppModule): Builder

        fun build(): AppComponent
    }

    fun inject(archApplication: ArchApplication)
}