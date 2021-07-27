package com.kikidinda.hitrash.ui.login

import android.content.Context
import android.util.Log
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.kikidinda.hitrash.model.User
import com.kikidinda.hitrash.repository.firestore.FirestoreUser
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import io.mockk.spyk
import org.junit.Before
import org.junit.Test

import org.junit.Assert.*
import org.junit.Rule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.*

@RunWith(MockitoJUnitRunner::class)
class LoginViewModelTest {

    private val userTestData = User(email = "ananda.rifkiy32@gmail.com", password = "wakwaw123")

    private val viewModel = spy<LoginViewModel>()

    private val repo = mock<FirestoreUser>()

    private var observer = mock<Observer<User>>()
    private var context = mock<Context>()

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {

    }

    @Test
    fun login() {

        viewModel.successBroadcaster().observeForever(observer)
        viewModel.login(eq("ananda.rifkiy32@gmail.com"), eq("wakwaw123"), eq(context))
        // Assert.assertEquals(userTestData, viewModel.message.value)

        verify(observer).onChanged(userTestData)
       // assertEquals(userTestData, viewModel.successBroadcaster().value)
    }
}