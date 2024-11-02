package com.example.adminblinkitclone.viewmodels


import android.app.Activity
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.adminblinkitclone.models.Admins
import com.example.adminblinkitclone.utils
import com.google.firebase.FirebaseException
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import com.google.firebase.database.FirebaseDatabase
import java.util.concurrent.TimeUnit
class AuthViewModel : ViewModel() {
    var numbermld = MutableLiveData<String>()
    var verifactionIdmld = MutableLiveData<String>()
    private val otpsent = MutableLiveData(false)
    private val currentusermld = MutableLiveData<Boolean>()
    val currentuser = currentusermld



    init {
        if(utils.getuid()!=null){
            currentusermld.value=true
        }

        else if(utils.getuid()==null){
            currentusermld.value=false
        }

    }

    private val issignedsuccfullymld = MutableLiveData<Boolean?>(null) // Initialized as null

    var _issignsuccessfully = issignedsuccfullymld
    var _otpsent = otpsent

    var invalid = true

    fun checknumber() {
        val num = numbermld.value
        invalid = num.isNullOrEmpty() || num.length != 10
    }


    fun sendotp(usernumber: String, activity: Activity) {

        val callbacks = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

            override fun onVerificationCompleted(credential: PhoneAuthCredential) {
                utils.getauthinstance().signInWithCredential(credential)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            issignedsuccfullymld.value = true
                        } else {
                            issignedsuccfullymld.value = false
                            utils.Toast(activity, "Sign in failed: ${task.exception?.message}")
                        }
                    }
            }

            override fun onVerificationFailed(e: FirebaseException) {
                Log.e("OTP Verification", "Failed: ${e.message}")
                utils.Toast(activity, "Verification failed: ${e.message}")
                otpsent.value = false
            }

            override fun onCodeSent(
                verificationId: String,
                token: PhoneAuthProvider.ForceResendingToken,
            ) {
                verifactionIdmld.value = verificationId
                otpsent.value = true
            }
        }

        val options = PhoneAuthOptions.newBuilder(utils.getauthinstance())
            .setPhoneNumber("+92$usernumber") // Phone number to verify
            .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
            .setActivity(activity) // Activity (for callback binding)
            .setCallbacks(callbacks) // OnVerificationStateChangedCallbacks
            .build()
        PhoneAuthProvider.verifyPhoneNumber(options)
    }

    fun signInWithPhoneAuthCredential(otp: String, admin: Admins) {
        issignedsuccfullymld.value = null // Reset before starting the process

        val credential = PhoneAuthProvider.getCredential(verifactionIdmld.value!!, otp)
        utils.getauthinstance().signInWithCredential(credential)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    FirebaseDatabase.getInstance().getReference("All Admins").child("Admins").child(admin.uid!!).setValue(admin)
                    issignedsuccfullymld.value = true
                } else {
                    issignedsuccfullymld.value = false
                }
            }
    }

}
