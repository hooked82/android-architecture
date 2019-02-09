package com.hookedroid.androidarchitecture

import android.app.Activity
import android.app.Application
import com.hookedroid.androidarchitecture.di.AppInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject

class ArchApplication : Application(), HasActivityInjector {

    @Inject
    lateinit var mDispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

    override fun onCreate() {
        super.onCreate()

        AppInjector.init(this)
    }

    override fun activityInjector() = mDispatchingAndroidInjector
}