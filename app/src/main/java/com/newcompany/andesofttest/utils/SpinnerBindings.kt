package com.newcompany.andesofttest.utils

import android.widget.Spinner
import androidx.databinding.BindingAdapter
import com.newcompany.andesofttest.utils.SpinnerExtensions.setSpinnerEntries
import com.newcompany.andesofttest.utils.SpinnerExtensions.setSpinnerItemSelectedListener
import com.newcompany.andesofttest.utils.SpinnerExtensions.setSpinnerValue

class SpinnerBindings {

    @BindingAdapter("entries")
    fun Spinner.setEntries(entries: List<Any>?) {
        setSpinnerEntries(entries)
    }

    @BindingAdapter("onItemSelected")
    fun Spinner.setOnItemSelectedListener(itemSelectedListener: SpinnerExtensions.ItemSelectedListener?) {
        setSpinnerItemSelectedListener(itemSelectedListener)
    }

    @BindingAdapter("newValue")
    fun Spinner.setNewValue(newValue: Any?) {
        setSpinnerValue(newValue)
    }
}