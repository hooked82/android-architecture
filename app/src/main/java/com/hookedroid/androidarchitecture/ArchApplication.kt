package com.hookedroid.androidarchitecture

import android.app.Activity
import android.app.Application
import com.hookedroid.androidarchitecture.di.AppComponent
import com.hookedroid.androidarchitecture.di.AppInjector
import com.hookedroid.androidarchitecture.di.DaggerAppComponent
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject

class ArchApplication : Application(), HasActivityInjector {

    @Inject
    lateinit var mDispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

    private lateinit var mAppComponent: AppComponent

    override fun onCreate() {
        super.onCreate()

        mAppComponent = DaggerAppComponent.builder().application(this).build().apply {
            inject(this@ArchApplication)
            AppInjector.init(this@ArchApplication)
        }
    }

    fun setAppComponent(appComponent: AppComponent) {
        mAppComponent = appComponent
    }

    override fun activityInjector() = mDispatchingAndroidInjector
}