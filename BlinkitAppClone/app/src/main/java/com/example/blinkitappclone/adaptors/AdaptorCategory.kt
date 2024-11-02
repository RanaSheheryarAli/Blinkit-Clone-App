package com.example.blinkitappclone.adaptors

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.blinkitappclone.databinding.ItemViewProductCategoryBinding
import com.example.blinkitappclone.models.Category
import java.util.zip.Inflater

class AdaptorCategory(
    var categorylist:ArrayList<Category>
):RecyclerView.Adapter<AdaptorCategory.categoryviewholder>()
{
    class categoryviewholder(val binding:ItemViewProductCategoryBinding) :ViewHolder(binding.root){


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): categoryviewholder {
        return categoryviewholder(ItemViewProductCategoryBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun getItemCount(): Int {
        return categorylist.size
    }

    override fun onBindViewHolder(holder: categoryviewholder, position: Int) {
        val category=categorylist[position]
        holder.binding.apply {
            tvcatageoryImg.setImageResource(category.img)
            tvcategoryTittle.text=category.tittle

        }
    }
}