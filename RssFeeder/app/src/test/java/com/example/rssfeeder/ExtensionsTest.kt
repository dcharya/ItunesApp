package com.example.rssfeeder

import com.example.rssfeeder.util.isValidEmail
import com.example.rssfeeder.util.isValidPhone
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(manifest = Config.NONE, sdk = [19])
class ExtensionsTest {

    @Test
    fun emailValidationTest() {
        Assert.assertTrue(validEmail.isValidEmail())
        Assert.assertFalse(invalidEmail.isValidEmail())
    }

    @Test
    fun phoneValidationTest(){
        Assert.assertTrue(validPhone.isValidPhone())
        Assert.assertTrue(!inValidPhone.isValidPhone())
    }

    companion object {
        val validPhone = "9988851509"
        val inValidPhone = "dsafew121234"
        val validEmail = "deepak.charya@gmail.com"
        val invalidEmail = "deepak kumar"
    }
}