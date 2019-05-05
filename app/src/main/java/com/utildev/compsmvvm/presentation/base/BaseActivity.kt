package com.utildev.compsmvvm.presentation.base

import android.annotation.SuppressLint
import android.graphics.Color
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.view.Gravity
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import com.utildev.compsmvvm.R

@SuppressLint("Registered")
open class BaseActivity : AppCompatActivity() {
    fun configNavigation(type: Int, elevation: Boolean, scrim: Boolean) {
        val drawer: DrawerLayout = findViewById(R.id.actMain_dl)
        val content: FrameLayout = findViewById(R.id.fmContainer)
        if (!scrim) drawer.setScrimColor(Color.TRANSPARENT)
        if (!elevation) drawer.drawerElevation = 0f
        val toggle =
            object : ActionBarDrawerToggle(this, drawer, R.string.open_navigation, R.string.close_navigation) {
                override fun onDrawerSlide(drawerView: View, slideOffset: Float) {
                    super.onDrawerSlide(drawerView, slideOffset)
                    val slideX = drawerView.width * slideOffset
                    when (type) {
                        // Default
                        0 -> {
                        }
                        // Slide content
                        1 -> {
                            content.translationX = slideX
                        }
                        // Slide + scale full screen
                        2 -> {
                            content.translationX = slideX
                            content.scaleX = 1 - slideOffset
                            content.scaleY = 1 - slideOffset
                        }
                        // Slide + scale 1
                        3 -> {
                            content.translationX = slideX
                            content.scaleX = 1 - (slideOffset / 8f)
                            content.scaleY = 1 - (slideOffset / 8f)
                        }
                    }
                }
            }
        drawer.addDrawerListener(toggle)
    }

    fun configToolbarMain(view: View, title: String?) {
        val drawer: DrawerLayout = findViewById(R.id.actMain_dl)
        val ivNav: ImageView = view.findViewById(R.id.tbMain_ivNav)
        val tvTitle: TextView = view.findViewById(R.id.tbMain_tvTitle)
        tvTitle.text = title
        ivNav.setOnClickListener {
            drawer.openDrawer(Gravity.START)
        }
    }

    fun configToolbar(view: View, title: String?, listener: BackStackListener?) {
        val ivBack: ImageView = view.findViewById(R.id.tb_ivBack)
        val tvTitle: TextView = view.findViewById(R.id.tb_tvTitle)
        tvTitle.text = title
        ivBack.setOnClickListener {
            if (listener != null) {
                listener.onBack()
            } else {
                clearStack()
            }
        }
    }

    private fun transactionFragment(
        fragment: BaseFragment,
        replace: Boolean,
        addToBackStack: Boolean,
        animation: Boolean
    ) {
        if (supportFragmentManager != null) {
            val fmTransaction = supportFragmentManager.beginTransaction()
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