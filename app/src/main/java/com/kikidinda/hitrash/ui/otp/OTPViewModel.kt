package com.kikidinda.hitrash.ui.otp

import android.app.Activity
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.FirebaseException
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider
import com.kikidinda.hitrash.Auth
import com.kikidinda.hitrash.model.User
import com.kikidinda.hitrash.repository.firestore.FirestoreUser
import java.util.concurrent.TimeUnit

class OTPViewModel : ViewModel() {
    companion object {
        const val TAG = "OTPViewModel"
    }

    val firestoreUser = FirestoreUser()

    val loading = MutableLiveData<Boolean>().apply {
        value = false
    }
    val message = MutableLiveData<String>()
    val successRegister = MutableLiveData<User>()

    lateinit var verificationId: String

    fun loadingBroadcaster(): LiveData<Boolean> = loading
    fun messageBroadcaster(): LiveData<String> = message
    fun successRegisterBroadcaster(): LiveData<User> = successRegister

    fun requestCode(
        number: String,
        activity: Activity
    ) {
        var phoneNumber = number
//        if(phoneNumber[0].equals("0")){
//            phoneNumber = phoneNumber.removeRange(0,0)
//        }
//        Log.d(TAG, "requestCode: " + phoneNumber)
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
            "+62$phoneNumber",
            60,
            TimeUnit.SECONDS,
            activity,
            object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                override fun onVerificationCompleted(p0: PhoneAuthCredential) {
                    Log.d(TAG, "onVerificationCompleted: " + p0.smsCode)
                    //view.OTPCodeSent()
                }

                override fun onVerificationFailed(p0: FirebaseException) {
                    Log.d(TAG, "onVerificationFailed: " + p0.localizedMessage)
                }

                override fun onCodeSent(
                    verifId: String,
                    token: PhoneAuthProvider.ForceResendingToken
                ) {
                    super.onCodeSent(verifId, token)
                    verificationId = verifId
                }

                override fun onCodeAutoRetrievalTimeOut(p0: String) {
                    super.onCodeAutoRetrievalTimeOut(p0)
                }
            }
        )
    }

    fun verifiyOTPCode(otpCode: String, user: User) {
        Log.d(TAG, "verifiyOTPCode: " + otpCode)
        loading.value = true
        val credential = PhoneAuthProvider.getCredential(verificationId, otpCode)
        when {
            //otpCode.isEmpty() -> view.setErorOTPCode("OTP Masih Kosong")
            else -> {

                Auth.instance
                    .signInWithCredential(credential)
                    .addOnCompleteListener {
                        if (it.isSuccessful) {
                            val id = it.result?.user?.uid!!
                            firestoreUser.registerNewUser(id, user) { message, status ->
                                if (status) {
                                    user.id = id
                                    successRegister.value = user
                                } else {
                                    this.message.value = "Registrasi gagal, coba lagi nanti"
                                }
                            }
                        } else {
                            message.value = "Kode OTP Salah"
                        }
                    }
            }
        }
    }
}