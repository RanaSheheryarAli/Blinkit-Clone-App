package com.example.adminblinkitclone.activity

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.databinding.DataBindingUtil
import com.example.adminblinkitclone.R
import com.example.adminblinkitclone.databinding.ActivityAdminMainBinding
import com.example.adminblinkitclone.utils

class AdminMainActivity : AppCompatActivity() {
    private lateinit var binding:ActivityAdminMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_admin_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        binding=DataBindingUtil.setContentView(this,R.layout.activity_admin_main)
        val userid=utils.getauthinstance().currentUser
        binding.tvid.text=userid.toString()
    }
}