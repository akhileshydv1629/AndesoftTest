package com.newcompany.andesofttest.adapter

import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.newcompany.andesofttest.R
import com.newcompany.andesofttest.databinding.ImageSliderItemsBinding

class ImageSliderRecyclerAdapter(userlist: ArrayList<String>, private val clickListener: (String) -> Unit):
    RecyclerView.Adapter<ImageSliderRecyclerAdapter.UserViewHolder>() {
    private  val  userList= userlist


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val layoutInflater= LayoutInflater.from(parent.context)
        val binding: ImageSliderItemsBinding = DataBindingUtil.inflate(layoutInflater, R.layout.image_slider_items, parent, false)

        return UserViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind(userList[position]!!, clickListener)
    }

    override fun getItemCount(): Int {
        return userList.size
    }


    class UserViewHolder(val binding: ImageSliderItemsBinding):RecyclerView.ViewHolder(binding.root)
    {
        fun bind(user: String, clickListener: (String) -> Unit)
        {

            binding.apply {


                Glide.with(binding.root).load(Uri.parse(user)).into(thumbImageSlider)



                binding.thumbImageSlider.setOnClickListener {
                    clickListener(user)
                }

            }


        }

    }



}