package com.newcompany.andesofttest

import android.app.Application
import androidx.databinding.DataBindingUtil
import com.newcompany.andesofttest.utils.BindingComponent

class MyApp: Application(){
    override fun onCreate() {
        super.onCreate()
        DataBindingUtil.setDefaultComponent(BindingComponent())
    }

}