package com.hookedroid.androidarchitecture

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.hookedroid.androidarchitecture.character.CharactersFragment
import com.hookedroid.androidarchitecture.databinding.ActivityMainBinding

class MainActivity : BaseActivity() {

    private lateinit var mBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        supportFragmentManager.beginTransaction().replace(R.id.content_frame, CharactersFragment(), "CharactersFragment")
    }
}