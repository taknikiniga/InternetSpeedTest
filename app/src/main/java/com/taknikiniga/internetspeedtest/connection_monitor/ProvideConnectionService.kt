package com.taknikiniga.internetspeedtest.connection_monitor

import android.content.Context
import android.net.ConnectivityManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class ProvideConnectionService {

    @Provides
    @Singleton
    fun provideSysService(@ApplicationContext context: Context): ConnectivityManager {
        return context?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    }
}