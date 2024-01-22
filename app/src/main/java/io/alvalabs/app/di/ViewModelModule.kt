package io.alvalabs.app.di

import io.alvalabs.app.fragments.detail.DetailViewModel
import io.alvalabs.app.fragments.list.ListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    // Single instance of FoodViewModel
    viewModel { ListViewModel(get()) }

    viewModel { DetailViewModel(get()) }

}