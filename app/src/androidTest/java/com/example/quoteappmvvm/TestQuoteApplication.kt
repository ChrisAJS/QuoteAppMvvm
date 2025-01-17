package com.example.quoteappmvvm

import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import dagger.android.DaggerApplication
import javax.inject.Inject

class TestQuoteApplication : DaggerApplication(), HasAndroidInjector {

    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Any>

    override fun androidInjector(): AndroidInjector<Any> = androidInjector

    override fun applicationInjector(): AndroidInjector<out DaggerApplication>? {
        return DaggerTestApplicationComponent.factory().create(this)
    }
}