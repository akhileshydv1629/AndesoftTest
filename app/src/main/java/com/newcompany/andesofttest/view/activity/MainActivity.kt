package com.newcompany.andesofttest.view.activity

import android.Manifest
import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import android.content.ContentValues.TAG
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.newcompany.andesofttest.viewmodel.MainActivityViewModel
import com.newcompany.andesofttest.R
import com.newcompany.andesofttest.UserViewModelFactory
import com.newcompany.andesofttest.databinding.ActivityMainBinding
import com.newcompany.andesofttest.utils.ImageBitmapString.fromArrayList
import com.newcompany.roomdatabasetask.db.AppDatabase
import com.newcompany.roomdatabasetask.repository.UserRepository
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var mainActivityViewModel: MainActivityViewModel
    private  var myCalendar=Calendar.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val dao = AppDatabase.getDbInstance(application).getUserDAO()
        val repository = UserRepository(dao)
        val factory = UserViewModelFactory(repository)
        mainActivityViewModel = ViewModelProvider(this, factory).get(MainActivityViewModel::class.java)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.viewmodel = mainActivityViewModel
        binding.lifecycleOwner = this
        mainActivityViewModel.message.observe(this, Observer {
            it.getContentIfNotHandled()?.let {
                Toast.makeText(this, it, Toast.LENGTH_LONG).show()
            }
        })

        binding.uploadMultipleImages.setOnClickListener(View.OnClickListener {
            loadImagesFromGallery()
        })


        val date = OnDateSetListener { view, year, monthOfYear, dayOfMonth -> // TODO Auto-generated method stub
            myCalendar.set(Calendar.YEAR, year)
            myCalendar.set(Calendar.MONTH, monthOfYear)
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
            updateLabel()
        }
        binding.etDOI.setOnClickListener {

            DatePickerDialog(this, date, myCalendar[Calendar.YEAR], myCalendar[Calendar.MONTH],
                    myCalendar[Calendar.DAY_OF_MONTH]).show()
        }


    }

    private fun updateLabel() {
        val myFormat = "MM/dd/yy" //In which you need put here
        val sdf = SimpleDateFormat(myFormat, Locale.US)
        mainActivityViewModel.DOI.value=sdf.format(myCalendar.time)
    }


    private fun loadImagesFromGallery() {
        if (ActivityCompat.checkSelfPermission(
                        this,
                        Manifest.permission.READ_EXTERNAL_STORAGE
                ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                    this, arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
                    100
            )
            return
        }
        if(Build.VERSION.SDK_INT<19)
        {
            val intent = Intent(Intent.ACTION_GET_CONTENT)
            intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true)
            intent.type = "image/*"
            startActivityForResult(Intent.createChooser(intent, "select a picture"), 1);
        }
        else{
            val intent = Intent(Intent.ACTION_OPEN_DOCUMENT)
            intent.addCategory(Intent.CATEGORY_OPENABLE)
            intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true)
            intent.type = "image/*"
            startActivityForResult(Intent.createChooser(intent, "select a picture"), 1);

        }

    }


    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1 && resultCode == RESULT_OK) {
            //imageFragmentContainer.setVisibility(View.VISIBLE)
           val bitmaps = ArrayList<Bitmap>()
            val imageSources = ArrayList<String?>()
            val clipData = data!!.clipData
            //clip data will be null if user select one item from gallery
            if (clipData != null) {
                for (i in 0 until clipData.itemCount) {
                    val imageUri: Uri = clipData.getItemAt(i).uri

                        imageSources.add(imageUri.toString()!!)


                }
            } else {
                val imageUri: Uri? = data.data

                    imageSources.add(imageUri.toString()!!)


            }
            mainActivityViewModel.imageUpload(fromArrayList(imageSources))
        }
    }

    override fun onRequestPermissionsResult(
            requestCode: Int,
            permissions: Array<String>,
            grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            Log.v(TAG, "Permission: " + permissions[0] + "was " + grantResults[0])
            if(Build.VERSION.SDK_INT<19)
            {
                val intent = Intent(Intent.ACTION_GET_CONTENT)
                intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true)
                intent.type = "image/*"
                startActivityForResult(intent, 1);
            }
            else{
                val intent = Intent(Intent.ACTION_OPEN_DOCUMENT)
                intent.addCategory(Intent.CATEGORY_OPENABLE)
                intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true)
                intent.type = "image/*"
                startActivityForResult(intent, 1);

            }        //resume tasks needing this permission
        }
    }


}