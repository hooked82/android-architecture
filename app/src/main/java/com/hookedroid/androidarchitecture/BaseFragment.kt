package com.hookedroid.androidarchitecture

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.hookedroid.androidarchitecture.di.Injectable
import javax.inject.Inject

abstract class BaseFragment<T : ViewModel> : Fragment(), Injectable {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    @Inject
    lateinit var mViewModel: T

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        ViewModelProviders.of(this, viewModelFactory).get(mViewModel.javaClass)
    }
}