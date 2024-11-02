package com.example.adminblinkitclone.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.adminblinkitclone.R
import com.example.adminblinkitclone.databinding.FragmentSigninBinding
import com.example.adminblinkitclone.utils
import com.example.adminblinkitclone.viewmodels.AuthViewModel


class signinFragment : Fragment() {
    private lateinit var binding: FragmentSigninBinding
    private lateinit var viewmodel1: AuthViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_signin, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        viewmodel1 = ViewModelProvider(this)[AuthViewModel::class.java]
        binding.viewmodel = viewmodel1
        viewmodel1.numbermld.observe(viewLifecycleOwner) {
            if (it.length == 10) {
                binding.btnContinue.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.green))
            } else {
                binding.btnContinue.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.gry))
            }
        }

        binding.btnContinue.setOnClickListener {
            viewmodel1.checknumber()

            if (viewmodel1.invalid) {

                utils.Toast(requireContext(), "Invalid Number")
            } else {
                val bundle = Bundle()
                bundle.putString("number", viewmodel1.numbermld.value.toString())
                findNavController().navigate(R.id.action_signinFragment_to_otpFragment2, bundle)
            }
        }
        return binding.root
    }

}