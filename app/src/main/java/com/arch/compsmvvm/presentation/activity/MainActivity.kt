package com.arch.compsmvvm.presentation.activity

import android.os.Bundle
import com.arch.compsmvvm.R
import com.arch.compsmvvm.presentation.base.BaseActivity

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        configNavigation(0, elevation = true, scrim = true)
    }
}
