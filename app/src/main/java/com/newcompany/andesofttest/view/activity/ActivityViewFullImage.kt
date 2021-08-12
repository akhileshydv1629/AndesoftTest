package com.newcompany.andesofttest.view.activity

import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.newcompany.andesofttest.R

class ActivityViewFullImage :AppCompatActivity() {

    var imageView:ImageView?=null
    var imageUrl:String?=null
    var close:Button?=null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_full_image)
        imageUrl = getIntent().getStringExtra("image_url");

        imageView = findViewById(R.id.thumbImageSlider);
        close = findViewById(R.id.close);
        close!!.setOnClickListener(View.OnClickListener {
            finish()
        })
        Glide.with(this).load(Uri.parse(imageUrl))
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background)
                .into(imageView as ImageView);

    }


}