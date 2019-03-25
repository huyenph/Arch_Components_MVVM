package com.arch.compsmvvm.presentation.base

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.View

open class BaseFragment: Fragment() {
    private var fmResultListener: FragmentResultListener? = null
    private var requestCode = 0

    fun configToolbarMain(view: View, title: String?) {
        if (activity is BaseActivity) {
//            (activity as BaseActivity).configToolbarMain(view, title)
        }
    }

    fun setFragmentResult(requestCode: Int, listener: FragmentResultListener?) {
        this.requestCode = requestCode
        this.fmResultListener = listener
    }

    fun callBackFragmentResult(bundle: Bundle) {
        if (fmResultListener != null) {
            fmResultListener!!.onFragmentResult(requestCode, bundle)
        }
    }

    fun replaceFragment(fragment: BaseFragment, addToBackStack: Boolean, animation: Boolean) {
        if (activity is BaseActivity) {
            (activity as BaseActivity).replaceFragment(fragment, addToBackStack, animation)
        }
    }

    fun addFragment(fragment: BaseFragment, addToBackStack: Boolean, animation: Boolean) {
        if (activity is BaseActivity) {
            (activity as BaseActivity).addFragment(fragment, addToBackStack, animation)
        }
    }

    fun clearAllStack() {
        if (activity is BaseActivity) {
            (activity as BaseActivity).clearAllStack()
        }
    }

    fun clearStack() {
        if (activity is BaseActivity) {
            (activity as BaseActivity).clearStack()
        }
    }

    interface FragmentResultListener {
        fun onFragmentResult(code: Int, bundle: Bundle)
    }
}