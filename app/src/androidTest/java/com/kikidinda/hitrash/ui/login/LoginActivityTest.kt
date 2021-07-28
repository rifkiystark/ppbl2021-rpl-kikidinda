package com.kikidinda.hitrash.ui.login

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.kikidinda.hitrash.R
import com.kikidinda.hitrash.utils.EspressoIdlingResource
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

class LoginActivityTest{

    private val email = "ananda.rifkiy32@gmail.com"
    private val password = "kiki"

    @Before
    fun setup(){
       ActivityScenario.launch(LoginActivity::class.java)
        IdlingRegistry.getInstance().register(EspressoIdlingResource.idlingResource)
    }

    @Test
    fun login() {
        onView(withId(R.id.etEmail)).perform(typeText(email), closeSoftKeyboard())
        onView(withId(R.id.etPassword)).perform(typeText(password), closeSoftKeyboard())
        onView(withId(R.id.btnMasuk)).perform(click())
    }
}