package com.example.rssfeeder.views.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.rssfeeder.util.ActionListener

abstract class BaseFragment : Fragment() {

    private var needToUpdateView = false
    var actionListener: ActionListener? = null

    abstract fun layoutResource(): Int
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View =
        inflater.inflate(layoutResource(), container, false)

    private fun viewUpdateOnResume() {
        if (needToUpdateView) {
            updateView()
            needToUpdateView = false
        }
    }

    override fun onResume() {
        super.onResume()
        needToUpdateView = true
        viewUpdateOnResume()
    }

    open fun updateView() {
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is ActionListener) {
            actionListener = context
        } else {
            throw Exception("Activity is expected to implement ActionListener")
        }

    }

    override fun onDetach() {
        super.onDetach()
        actionListener = null
    }

    fun wrapActionListener() = object : ActionListener {
        override fun onAction(action: String, data: Any?) {
            actionListener?.onAction(action, data)
        }

    }
}