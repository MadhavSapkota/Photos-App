package com.example.imagepicker.image_picker.adapter
import android.annotation.SuppressLint
import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.imagepicker.R
import com.example.imagepicker.databinding.ImageDataBinding

class ImageAdapter(var context: Context) : RecyclerView.Adapter<ImageHolder>() {
    private var uriList = ArrayList<Uri>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageHolder {
        val inflator = LayoutInflater.from(parent.context)
        val view = ImageDataBinding.inflate(inflator, parent, false)
        val viewHolder = ImageHolder(view)
        return viewHolder
    }

    @SuppressLint("ResourceAsColor")
    override fun onBindViewHolder(holder: ImageHolder, position: Int) {
        try {
            Glide.with(context)
                .load(uriList.get(position))
                .placeholder(R.drawable.ic_launcher_foreground)
                .error(R.drawable.common_full_open_on_phone)
                .into(holder.ivImage)

        }catch (ex: Exception) {

        }
        holder.textView.text= (position+1).toString()
    }


    //add animal list
    @SuppressLint("NotifyDataSetChanged")
    fun showImageItem(imageData: ArrayList<Uri>, aboolean: Boolean) {
        when {
            aboolean -> uriList.clear()
        }
        if (imageData != null) {
            uriList.clear()
            var dogCount = 0
            var catCount = 0
            for (i in 1 until 51) {
                if (i == 1 || i == 3 || i == 6 || i == 10 || i == 15 || i == 21) {
                    uriList.add(imageData[0]) // add image of dog
                    dogCount++
                } else {
                    uriList.add(imageData[1]) // add image of cat
                    catCount++
                }
            }
            notifyDataSetChanged()
        }
    }

    override fun getItemCount(): Int {
        return uriList.size
    }
}