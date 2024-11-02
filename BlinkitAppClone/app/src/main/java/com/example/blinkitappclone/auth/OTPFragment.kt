package com.example.blinkitappclone.auth

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.blinkitappclone.R
import com.example.blinkitappclone.activity.UserMainActivity
import com.example.blinkitappclone.databinding.FragmentOTPBinding
import com.example.blinkitappclone.models.Users
import com.example.blinkitappclone.utils
import com.example.blinkitappclone.viewmodels.AuthViewModel

class OTPFragment : Fragment() {

    private lateinit var viewmodel:AuthViewModel

    private lateinit var binding: FragmentOTPBinding

    private lateinit var number: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            number = it.getString("number").orEmpty()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        viewmodel=ViewModelProvider(this).get(AuthViewModel::class.java)
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_o_t_p, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.tvusernumber.text = number
        customizeotprdt()
        onbackbtnclick()
        sendotp()
        binding.btnLogin.setOnClickListener{
            utils.showDialog(requireContext(),"Verifying...")
            getotp()
        }
        return binding.root
    }

    private fun getotp() {
        val edittxt = arrayOf(binding.et0tp1, binding.et0tp2, binding.et0tp3, binding.et0tp4, binding.et0tp5, binding.et0tp6)
        val userenterotp= edittxt.joinToString (""){it.text.toString() }
        if(userenterotp.length < edittxt.size){
            utils.Toast(requireContext(),"please enter valid otp")
            utils.hidDiloge()
        }
        else{
            edittxt.forEach { it.text?.clear() ; it.clearFocus()}
         verifyotp(userenterotp)
        }
    }

    private fun verifyotp(otp: String) {
        val user=Users(utils.getuid(),number,null)
        viewmodel.signInWithPhoneAuthCredential(otp, user)
        viewmodel._issignsuccessfully.observe(viewLifecycleOwner) { isSuccess ->

            if (isSuccess == true) {
                utils.hidDiloge()
                utils.Toast(requireContext(), "Successfully signed in")

                startActivity(Intent(requireContext(),UserMainActivity::class.java))
                requireActivity().finish()
            } else if(isSuccess==false){
                utils.hidDiloge()
                utils.Toast(requireContext(),"incorrect otp")
            }
        }

    }

    private fun sendotp() {
        utils.showDialog(requireContext(),"Loading")
        viewmodel.apply {
            sendotp(number,requireActivity())
            _otpsent.observe(viewLifecycleOwner){
                if(it){
                utils.hidDiloge()
                    utils.Toast(requireContext(),"Otp sent")
                }
            }

        }
    }

    fun onbackbtnclick(){
    binding.tbotpfragment.setNavigationOnClickListener{
        findNavController().navigate(R.id.action_OTPFragment_to_signInFragment)
    }
    }

    fun customizeotprdt() {
        val edittxt = arrayOf(binding.et0tp1, binding.et0tp2, binding.et0tp3, binding.et0tp4, binding.et0tp5, binding.et0tp6)
        for (i in edittxt.indices) {
            edittxt[i].addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
                override fun afterTextChanged(s: Editable?) {
                    if (s?.length == 1) {
                        if (i < edittxt.size - 1) {
                            edittxt[i + 1].requestFocus()
                        } else {
                            // Automatically click the Verify button when the last digit is entered

                        }
                    } else if (s?.length == 0) {
                        if (i > 0) {
                            edittxt[i - 1].requestFocus()
                        }
                    }
                }
            })
        }
    }

}
