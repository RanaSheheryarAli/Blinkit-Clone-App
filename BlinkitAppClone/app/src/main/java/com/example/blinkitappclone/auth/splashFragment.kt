package com.example.blinkitappclone.auth

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.blinkitappclone.R
import com.example.blinkitappclone.activity.UserMainActivity
import com.example.blinkitappclone.viewmodels.AuthViewModel


class splashFragment : Fragment() {

    private lateinit var viewModel: AuthViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        viewModel=ViewModelProvider(this).get(AuthViewModel::class.java)

        Handler(Looper.getMainLooper()).postDelayed({
        viewModel.currentuser.observe(viewLifecycleOwner){
//            if (it==true){
//                startActivity(Intent(requireContext(),UserMainActivity::class.java))
//                requireActivity().finish()
//            }
//            else
//            {
                findNavController().navigate(R.id.action_splashFragment_to_signInFragment)
//            }
        }

        },3000)

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_splash, container, false)
    }


}