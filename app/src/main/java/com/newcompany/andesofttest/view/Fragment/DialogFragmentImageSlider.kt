package com.newcompany.andesofttest.view.Fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.newcompany.andesofttest.R
import com.newcompany.andesofttest.view.activity.ActivityViewFullImage
import com.newcompany.andesofttest.adapter.ImageSliderRecyclerAdapter
import com.newcompany.andesofttest.utils.ImageBitmapString.fromString
import kotlinx.android.synthetic.main.fragment_image_slider.view.*


class DialogFragmentImageSlider: DialogFragment() {


    companion object {

        const val TAG = "SimpleDialog"

        private const val KEY_IMAGE = "KEY_IMAGE"

        fun newInstance(image: String): DialogFragmentImageSlider {
            val args = Bundle()
            args.putString(KEY_IMAGE, image)
            val fragment = DialogFragmentImageSlider()
            fragment.arguments = args
            return fragment
        }

    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_image_slider, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView(view)
        setupClickListeners(view)
    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(
                WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.WRAP_CONTENT
        )
    }

    private fun setupView(view: View) {
        val imageString= arguments?.getString(KEY_IMAGE)


        val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(
                activity,
                LinearLayoutManager.HORIZONTAL, false
        )
        view.horizontalImageSlider.apply {
            horizontalImageSlider.layoutManager = layoutManager
            val recyclerAdapter = ImageSliderRecyclerAdapter(
                    fromString(imageString) as ArrayList<String>,
                    { selectedItem: String ->
                        listItemClicked(
                                selectedItem
                        )
                    })
            adapter= recyclerAdapter

        }
        view.indicator.attachToRecyclerView(view.horizontalImageSlider)


    }
    fun listItemClicked(stringImage: String) {
        val intent = Intent (getActivity(), ActivityViewFullImage::class.java)
        intent.putExtra("image_url", stringImage)
       startActivity(intent)
    }
    private fun setupClickListeners(view: View) {
        view.close.setOnClickListener {
            // TODO: Do some task here
            dismiss()
        }

    }

}