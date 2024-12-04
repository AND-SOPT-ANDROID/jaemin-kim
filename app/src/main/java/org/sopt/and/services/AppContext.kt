package org.sopt.and.services

import android.content.Context

object AppContext {
    private lateinit var applicationContext: Context

    fun init(context: Context) {
        applicationContext = context.applicationContext
    }

    fun get(): Context = applicationContext
}