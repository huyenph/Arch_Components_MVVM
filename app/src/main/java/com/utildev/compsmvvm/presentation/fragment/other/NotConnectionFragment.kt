package com.utildev.compsmvvm.presentation.fragment.other

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.utildev.compsmvvm.R
import com.utildev.compsmvvm.common.extensions.isNetworkAvailable
import com.utildev.compsmvvm.presentation.base.BaseActivity
import com.utildev.compsmvvm.presentation.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_not_connection.view.*

class NotConnectionFragment: BaseFragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_not_connection, container, false)
        view.fmNotConnection_tvTryAgain.setOnClickListener {
            if (isNetworkAvailable(context!!)) {
                val intent = (activity as BaseActivity).intent
                (activity as BaseActivity).finish()
                (activity as BaseActivity).startActivity(intent)
            }
        }
        return view
    }
}