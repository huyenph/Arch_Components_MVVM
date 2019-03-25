package com.arch.compsmvvm.presentation.base

import android.support.v7.app.AppCompatActivity
import com.arch.compsmvvm.R

open class BaseActivity : AppCompatActivity() {
    private fun transactionFragment(
        fragment: BaseFragment,
        replace: Boolean,
        addToBackStack: Boolean,
        animation: Boolean
    ) {
        if (supportFragmentManager != null) {
            val fmTransaction = supportFragmentManager.beginTransaction()
            if (replace) {
                fmTransaction.replace(R.id.fmContainer, fragment, fragment::class.java.simpleName)
            } else {
                val currentFm = supportFragmentManager.findFragmentById(R.id.fmContainer) as BaseFragment?
                if (currentFm != null) {
                    fmTransaction.hide(currentFm)
                }
                fmTransaction.add(R.id.fmContainer, fragment, fragment::class.java.simpleName)
            }
            if (addToBackStack) {
                fmTransaction.addToBackStack(fragment::class.java.simpleName)
            }
            if (animation) {
                fmTransaction.setCustomAnimations(
                    R.anim.activity_new_in,
                    R.anim.activity_old_out,
                    R.anim.activity_old_in,
                    R.anim.activity_new_out
                )
            } else {
                fmTransaction.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out)
            }
            fmTransaction.commit()
        }
    }

    fun replaceFragment(fragment: BaseFragment, addToBackStack: Boolean, animation: Boolean) {
        transactionFragment(fragment, true, addToBackStack, animation)
    }

    fun addFragment(fragment: BaseFragment, addToBackStack: Boolean, animation: Boolean) {
        transactionFragment(fragment, false, addToBackStack, animation)
    }

    fun clearAllStack() {
        val fmCount = supportFragmentManager.backStackEntryCount
        for (i in 0..fmCount) {
            supportFragmentManager.popBackStack()
        }
    }

    fun clearStack() {
        supportFragmentManager.popBackStack()
    }

    interface BackStackListener {
        fun onBack()
    }
}