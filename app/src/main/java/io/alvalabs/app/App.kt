package io.alvalabs.app

import android.app.Application
import com.example.data.di.networkModule
import com.example.data.di.repositoryModule
import io.alvalabs.app.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin

class App: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin{
            androidContext(this@App)
            modules(networkModule, repositoryModule, viewModelModule)
        }
    }
}