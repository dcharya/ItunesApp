package com.example.rssfeeder.views.login.ui.login

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.rssfeeder.R
import com.example.rssfeeder.util.showShortSnackBar
import com.example.rssfeeder.viewmodels.LoginViewModel
import com.example.rssfeeder.views.base.BaseFragment
import kotlinx.android.synthetic.main.login_fragment.*
import kotlinx.android.synthetic.main.login_fragment.view.*

class LoginFragment : BaseFragment() {

    override fun layoutResource() = R.layout.login_fragment

    companion object {
        private val TAG = LoginFragment.javaClass.simpleName
        val ACTION_SHOW_REGISTER = "$TAG.show_register_fragment"
        val ACTION_SHOW_HOME = "$TAG.showHome"
    }

    private lateinit var viewModel: LoginViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        btnLogin.setOnClickListener {
            val userEmail = emailLogin.text.toString()
            val userPassword = passwordLogin.text.toString()
            if (viewModel.isValid(userEmail, userPassword)) {
                observerLogin(userEmail, userPassword)
            } else {
                emailLogin.setText("")
                passwordLogin.setText("")
                emailLogin.requestFocus()
                activity?.showShortSnackBar(login_linear_layout, R.string.err_login1)
            }
        }

        btnRegister.setOnClickListener {
            actionListener?.onAction(ACTION_SHOW_REGISTER, null)
        }
    }


    @SuppressLint("NewApi")
    private fun observerLogin(email: String, password: String) {
        viewModel.loginUser(email,password).observe(this, Observer {loginUser->
            if (loginUser!=null){
                actionListener?.onAction(ACTION_SHOW_HOME, null)
            } else{
                activity?.showShortSnackBar(login_linear_layout,R.string.err_login2)
                emailLogin.setText("")
                passwordLogin.setText("")
                emailLogin.requestFocus()
            }

        })
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(LoginViewModel::class.java)
    }

}
