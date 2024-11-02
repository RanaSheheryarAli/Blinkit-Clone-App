package com.example.adminblinkitclone.auth

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.adminblinkitclone.R
import com.example.adminblinkitclone.activity.AdminMainActivity
import com.example.adminblinkitclone.viewmodels.AuthViewModel

class SplashFragment : Fragment() {
    private lateinit var viewModel: AuthViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        viewModel= ViewModelProvider(this).get(AuthViewModel::class.java)

        Handler(Looper.getMainLooper()).postDelayed({
            viewModel.currentuser.observe(viewLifecycleOwner){
//                if (it==true){
//                    startActivity(Intent(requireContext(),AdminMainActivity::class.java))
//                    requireActivity().finish()
//                }
//                else
//                {
                    findNavController().navigate(R.id.action_splashFragment2_to_signinFragment)
                }
//            }

        },3000)

        return inflater.inflate(R.layout.fragment_splash, container, false)
    }

}