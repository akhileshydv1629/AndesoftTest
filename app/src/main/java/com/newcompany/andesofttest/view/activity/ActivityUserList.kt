package com.newcompany.andesofttest.view.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.newcompany.andesofttest.adapter.UserRecyclerAdapter
import com.newcompany.roomdatabasetask.db.AppDatabase
import com.newcompany.roomdatabasetask.repository.UserRepository
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.newcompany.andesofttest.view.Fragment.DialogFragmentImageSlider
import com.newcompany.andesofttest.viewmodel.MainActivityViewModel
import com.newcompany.andesofttest.R
import com.newcompany.andesofttest.UserViewModelFactory
import com.newcompany.andesofttest.databinding.ActivityUserlistBinding
import com.newcompany.andesofttest.model.User

class ActivityUserList: AppCompatActivity() {
    private lateinit var binding: ActivityUserlistBinding
    private lateinit var mainActivityViewModel: MainActivityViewModel
    private lateinit var adapter: UserRecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    val dao = AppDatabase.getDbInstance(application).getUserDAO()
    val repository = UserRepository(dao)
    val factory = UserViewModelFactory(repository)
    mainActivityViewModel = ViewModelProvider(this, factory).get(MainActivityViewModel::
    class.java)
    binding = DataBindingUtil.setContentView(this, R.layout.activity_userlist)
    binding.viewmodel = mainActivityViewModel
    binding.lifecycleOwner = this
    mainActivityViewModel.message.observe(this, Observer
    {
        it.getContentIfNotHandled()?.let {
            Toast.makeText(this, it, Toast.LENGTH_LONG).show()
        }
    })
        binding.addUser.setOnClickListener(View.OnClickListener {
            val intent=  Intent(this, MainActivity::class.java)
            startActivity(intent)
        })

        initRecyclerView()

}
    private fun initRecyclerView() {
        binding.subscriberRecyclerView.layoutManager = LinearLayoutManager(this)
        adapter = UserRecyclerAdapter({ selectedItem: User -> listItemClicked(selectedItem) },
            {selectedItem2: User -> listItemClicked2(selectedItem2) })
        binding.subscriberRecyclerView.adapter = adapter
        displaySubscribersList()
    }
    private fun displaySubscribersList() {
        mainActivityViewModel.getUsers().observe(this, Observer {
            adapter.setList(it)
            adapter.notifyDataSetChanged()
        })
    }


    fun listItemClicked(user: User) {

    }
    fun listItemClicked2(user: User) {
        DialogFragmentImageSlider.newInstance(user.image).show(supportFragmentManager, DialogFragmentImageSlider.TAG)

    }

}