package com.example.rssfeeder.viewmodels

import com.example.rssfeeder.services.model.User
import com.example.rssfeeder.utils.LiveDataTestUtil
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(manifest = Config.NONE, sdk = [19])
class RegisterViewModelTest {
    private lateinit var registerViewModel: RegisterViewModel
    private lateinit var loginViewModel: LoginViewModel
    @Before
    fun init() {
        registerViewModel = Mockito.spy(RegisterViewModel::class.java)
        loginViewModel = Mockito.spy(LoginViewModel::class.java)
    }

    @Test
    fun testRegistration() {

        val userRegister = LiveDataTestUtil.getValue(registerViewModel.registerUser(user))
        Assert.assertTrue(userRegister != null)
        userRegister?.let { user ->
            Assert.assertEquals(name, user.userName)
            Assert.assertEquals(email, user.email)
            Assert.assertEquals(mobile, user.mobile)
            Assert.assertEquals(password, user.password)
        }
    }

    @Test
    fun testIsValid(){
        Assert.assertTrue(registerViewModel.isValid(email, password, mobile, name))
        Assert.assertFalse(registerViewModel.isValid(email, password,"",name))
        Assert.assertFalse(registerViewModel.isValid(invalidEmail, password, mobile, name))
    }


    companion object {
        const val name = "Deepak"
        const val email = "deepak.charya@gmail.com"
        const val mobile = "9988851509"
        const val password = "Diya@1234"

        const val invalidEmail = "deepak@kumar@gmail.com"

        val user = User(name, email, mobile, password)
    }
}