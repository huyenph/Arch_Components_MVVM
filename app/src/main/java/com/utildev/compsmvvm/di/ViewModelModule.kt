package com.utildev.compsmvvm.di

import com.utildev.compsmvvm.presentation.activity.MainViewModel
import com.utildev.compsmvvm.presentation.fragment.main.MainFmViewModel
import com.utildev.compsmvvm.presentation.fragment.question.QuestionViewModel
import com.utildev.compsmvvm.presentation.fragment.site.SiteViewModel
import org.koin.android.viewmodel.experimental.builder.viewModel
import org.koin.dsl.module.module

val viewModelModule = module {
    viewModel<MainViewModel>()
    viewModel<MainFmViewModel>()
    viewModel<QuestionViewModel>()
    viewModel<SiteViewModel>()
}