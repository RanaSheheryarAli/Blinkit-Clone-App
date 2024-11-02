package com.example.blinkitappclone

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.blinkitappclone.adaptors.AdaptorCategory
import com.example.blinkitappclone.databinding.FragmentHomeBinding
import com.example.blinkitappclone.models.Category

class HomeFragment : Fragment() {
    private lateinit var binding:FragmentHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding=DataBindingUtil.inflate(inflater,R.layout.fragment_home, container, false)

        val categorylist=ArrayList<Category>()

        for(i in 0 until Constants.AllProductCategoryIcons.size){
            categorylist.add(Category(Constants.allProductsCategory[i],Constants.AllProductCategoryIcons[i]))

        }

        binding.rcvcategoris.adapter=AdaptorCategory(categorylist)

        return binding.root
    }

}