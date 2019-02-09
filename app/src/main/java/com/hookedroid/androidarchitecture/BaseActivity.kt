package com.hookedroid.androidarchitecture

import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import androidx.preference.PreferenceManager
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject

abstract class BaseActivity : AppCompatActivity(), HasSupportFragmentInjector, SharedPreferences.OnSharedPreferenceChangeListener {

    @Inject
    lateinit var mDispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val darkModeEnabled = PreferenceManager.getDefaultSharedPreferences(this).getBoolean(getString(R.string.settings_key_dark_mode), false)

        if (darkModeEnabled) {
            delegate.setLocalNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        } else {
            delegate.setLocalNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }
    }

    override fun onSharedPreferenceChanged(sharedPreferences: SharedPreferences?, key: String?) {
        when (key) {
            getString(R.string.settings_key_dark_mode) -> {
                sharedPreferences?.getBoolean(key, false).let {
                    val dayNightMode: Int = if (it == true) {
                        AppCompatDelegate.MODE_NIGHT_YES
                    } else {
                        AppCompatDelegate.MODE_NIGHT_NO
                    }

                    delegate.setLocalNightMode(dayNightMode)
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        PreferenceManager.getDefaultSharedPreferences(this).registerOnSharedPreferenceChangeListener(this)
    }

    override fun onPause() {
        super.onPause()
        PreferenceManager.getDefaultSharedPreferences(this).unregisterOnSharedPreferenceChangeListener(this)
    }

    override fun supportFragmentInjector() = mDispatchingAndroidInjector
}