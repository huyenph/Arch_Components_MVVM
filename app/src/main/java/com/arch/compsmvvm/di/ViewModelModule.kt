package com.arch.compsmvvm.di

import com.arch.compsmvvm.presentation.activity.MainViewModel
import com.arch.compsmvvm.presentation.fragment.main.MainFmViewModel
import com.arch.compsmvvm.presentation.fragment.question.QuestionViewModel
import com.arch.compsmvvm.presentation.fragment.site.SiteViewModel
import org.koin.android.viewmodel.experimental.builder.viewModel
import org.koin.dsl.module.module

val viewModelModule = module {
    viewModel<MainViewModel>()
    viewModel<MainFmViewModel>()
    viewModel<QuestionViewModel>()
    viewModel<SiteViewModel>()
}