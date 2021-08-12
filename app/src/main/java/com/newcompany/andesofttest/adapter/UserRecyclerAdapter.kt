package com.newcompany.andesofttest.adapter

import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.newcompany.andesofttest.R
import com.newcompany.andesofttest.databinding.ListUserItemsBinding
import com.newcompany.andesofttest.utils.ImageBitmapString.fromString
import com.newcompany.andesofttest.model.User
import com.squareup.picasso.Picasso

class UserRecyclerAdapter(
        private val clickListener: (User) -> Unit,
        private val clickListener2: (User) -> Unit
):
    RecyclerView.Adapter<UserRecyclerAdapter.UserViewHolder>() {
    private  val  userList= ArrayList<User>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val layoutInflater= LayoutInflater.from(parent.context)
        val binding: ListUserItemsBinding = DataBindingUtil.inflate(
            layoutInflater,
            R.layout.list_user_items,
            parent,
            false
        )

        return UserViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind(userList[position], clickListener, clickListener2)
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    fun setList(userlist: List<User>)
    {
        userList.clear()
          userList.addAll(userlist)
    }

    class UserViewHolder(val binding: ListUserItemsBinding):RecyclerView.ViewHolder(binding.root)
    {
        fun bind(user: User, clickListener: (User) -> Unit, clickListener2: (User) -> Unit)
        {

            binding.apply {

                tvBookName.text="Book Name: "+user.inpuBookName
                tvAuthorName.text="Author Name: "+user.inputAuthorName
                tvPrice.text="Price: "+user.inputPrice
                tvDOI.text="DOI: "+user.DOI
                val uri = Uri.parse(fromString(user.image)!!.get(0)!!)
                Log.d("testdh", fromString(user.image)!!.get(0)!!)
               // Glide.with(binding.root.context).load(uri).into(thumbImage)
                Picasso.with(binding.root.context).load(uri).into(thumbImage);


                itemCLickListener.setOnClickListener {
                    clickListener(user)
                }
                thumbImage.setOnClickListener{
                    clickListener2(user)
                }
            }


        }

    }



}