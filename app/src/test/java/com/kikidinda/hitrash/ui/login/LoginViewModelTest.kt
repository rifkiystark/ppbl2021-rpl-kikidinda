package com.kikidinda.hitrash.ui.login

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class LoginViewModelTest {

    @Mock
    private lateinit var context: Context

    @Mock
    private lateinit var viewModel : LoginViewModel
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        //viewModel = LoginViewModel()
    }

    @Test
    fun login() {
        viewModel.login("ananda.rifkiy32@gmail.com", "kiki", context)
        Thread.sleep(10000)
        val user = viewModel.successBroadcaster().value

        assertEquals(user?.email, "ananda.rifkiy32@gmail.com")
    }
}