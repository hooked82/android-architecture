package com.hookedroid.androidarchitecture

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.hookedroid.androidarchitecture.databinding.ActivityMainBinding

class MainActivity : BaseActivity() {

    private lateinit var mBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        setSupportActionBar(mBinding.toolbar)

        val navController = findNavController(R.id.nav_host_fragment)
        setupActionBarWithNavController(navController)
        mBinding.toolbar.setupWithNavController(navController)
        mBinding.bottomNav.setupWithNavController(navController)
    }
}