package com.example.myapplication

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin


class AppMain : Application() {
    override fun onCreate() {
        super.onCreate()

        //DI 관리
        startKoin {
            //??
            androidContext(this@AppMain)
            //모듈 추가
            modules(AppModules.modules)
        }
    }
}