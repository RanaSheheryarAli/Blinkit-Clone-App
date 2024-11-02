package com.example.blinkitappclone

import android.app.ProgressDialog
import android.content.Context
import android.view.LayoutInflater
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import com.example.blinkitappclone.databinding.ProgressBinding
import com.google.firebase.auth.FirebaseAuth

object utils {

    private var dialog: AlertDialog? = null

    fun showDialog(context: Context, message: String) {
        val progress=ProgressBinding.inflate(LayoutInflater.from(context))
        progress.tvMessage.text=message
        dialog=AlertDialog.Builder(context).setView(progress.root).setCancelable(false).create()
        dialog!!.show()

    }

    fun hidDiloge(){
        dialog?.dismiss()

    }

    fun Toast(context: Context, message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

    private var firebaseauthinstance: FirebaseAuth?=null

    fun getauthinstance():FirebaseAuth{
        if(firebaseauthinstance==null){
            firebaseauthinstance=FirebaseAuth.getInstance()
        }
        return firebaseauthinstance!!
    }

    fun getuid():String{
        return FirebaseAuth.getInstance().currentUser?.uid.toString()
    }
}
