package com.example.imagepicker.image_picker.mvp
import android.net.Uri
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.example.imagepicker.R
import com.example.imagepicker.databinding.ActivityMainBinding
import com.example.imagepicker.image_picker.adapter.ImageAdapter
import com.jakewharton.rxbinding2.view.RxView
import io.reactivex.Observable
import java.util.ArrayList

class MainView(
    private val appCompatActivity: AppCompatActivity,
    private val imageAdapter: ImageAdapter
    ) {
    var binding: ActivityMainBinding? = null

    fun start(binding_main: ActivityMainBinding) {
        binding = binding_main
        setImageAdapter()
    }

    fun showListItems(imageData: ArrayList<Uri>, aboolean: Boolean) {
        imageAdapter.showImageItem(imageData, aboolean)
    }

    fun getBackObserable(): Observable<Any> {
        return RxView.clicks(binding!!.pickImageButton)
    }

    fun setImageAdapter() {
        val gridLayoutManager = GridLayoutManager(appCompatActivity,2 )
        binding!!.recyclerViewImage.dashboardRecyclerView.setHasFixedSize(true)
        binding!!.recyclerViewImage.dashboardRecyclerView.layoutManager = gridLayoutManager
        binding!!.recyclerViewImage.dashboardRecyclerView.adapter = imageAdapter
    }

    fun showToastMessage(){
        Toast.makeText(appCompatActivity, R.string.toast_message, Toast.LENGTH_SHORT).show()
    }
     fun hidePickImage(){
         binding!!.pickImageButton.visibility = View.GONE
     }
}

