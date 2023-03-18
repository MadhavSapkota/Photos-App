package com.example.imagepicker.image_picker.adapter
import android.net.Uri
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.imagepicker.databinding.ImageDataBinding
import com.google.android.material.imageview.ShapeableImageView

class ImageHolder(val binding: ImageDataBinding): RecyclerView.ViewHolder(binding.root) {
    var ivImage: ShapeableImageView = binding!!.imageView

}