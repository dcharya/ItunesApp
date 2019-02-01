package com.example.rssfeeder.views.login.ui.login

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.rssfeeder.R
import com.example.rssfeeder.services.model.User
import com.example.rssfeeder.util.showShortSnackBar
import com.example.rssfeeder.viewmodels.RegisterViewModel
import com.example.rssfeeder.views.base.BaseFragment
import kotlinx.android.synthetic.main.register_fragment.*

class RegisterFragment : BaseFragment() {
    override fun layoutResource() = R.layout.register_fragment

    companion object {
        val TAG = RegisterFragment.javaClass.simpleName
        val ACTION_SHOW_LOGIN = "$TAG.showlogin"
    }

    private lateinit var viewModel: RegisterViewModel

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(RegisterViewModel::class.java)
        // TODO: Use the ViewModel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnRegister.setOnClickListener {
            val email = emailRegister.text.toString()
            val username = name.text.toString()
            val phone = mobile.text.toString()
            val password = passwordRegister.text.toString()

            if (viewModel.isValid(email, password, phone, username)) {
                observerRegistration(username, email, phone, password)
            } else {
                activity?.showShortSnackBar(register_linear_layout, R.string.err_login1)
            }
        }

        btnCancel.setOnClickListener {
            actionListener?.onAction(ACTION_SHOW_LOGIN, null)
        }
    }

    private fun observerRegistration(name: String, email: String, phone: String, password: String) {
        val user = User(name, email, phone, password)
        viewModel.registerUser(user).observe(this, Observer {
            if (it != null) {
                actionListener?.onAction(ACTION_SHOW_LOGIN, resources.getString(R.string.success_registration))
            } else {
                activity?.showShortSnackBar(register_linear_layout, R.string.err_registration_failed)
            }
        })
    }


}
