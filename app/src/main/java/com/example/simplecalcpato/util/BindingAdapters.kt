package com.example.simplecalcpato.util

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.example.simplecalcpato.R
import com.example.simplecalcpato.viewmodel.Operation

/**
 * Binding Adapter to show corresponding ImageView for current operation
 */

@BindingAdapter("operationCurrent")
fun bindOperation(operationImageView: ImageView,
               operation: Operation?) {

    when (operation) {
        //when Operation is ADD, set ImageView to corresponding image
        Operation.ADD -> {
            operationImageView.visibility = View.VISIBLE
            operationImageView.setImageResource(R.drawable.ic_add_24)
        }
        //when Operation is SUB, set ImageView to corresponding image
        Operation.SUB -> {
            operationImageView.visibility = View.VISIBLE
            operationImageView.setImageResource(R.drawable.ic_sub_24)
        }
        //when Operation is MUL, set ImageView to corresponding image
        Operation.MUL -> {
            operationImageView.visibility = View.VISIBLE
            operationImageView.setImageResource(R.drawable.ic_mul_24)
        }

        //when Operation is DIV, set ImageView to corresponding image
        Operation.DIV -> {
            operationImageView.visibility = View.VISIBLE
            operationImageView.setImageResource(R.drawable.ic_div_24)
        }
        else -> {operationImageView.visibility = View.GONE}
    }


}