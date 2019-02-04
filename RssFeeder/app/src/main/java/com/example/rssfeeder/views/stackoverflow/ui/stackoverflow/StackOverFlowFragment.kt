package com.example.rssfeeder.views.stackoverflow.ui.stackoverflow

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.rssfeeder.viewmodels.StackOverFlowViewModel
import com.example.rssfeeder.views.stackoverflow.R

class StackOverFlowFragment : Fragment() {

    companion object {
        fun newInstance() = StackOverFlowFragment()
    }

    private lateinit var viewModel: StackOverFlowViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.stack_over_flow_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(StackOverFlowViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
