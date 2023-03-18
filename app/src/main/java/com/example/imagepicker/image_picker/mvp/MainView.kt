package com.example.imagepicker.image_picker.mvp
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.imagepicker.databinding.ActivityMainBinding
import com.example.imagepicker.image_picker.adapter.ImageAdapter
import com.jakewharton.rxbinding2.view.RxView
import com.sangcomz.fishbun.Fishton.selectedImages
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
//        var layoutmanager: LinearLayoutManager? = LinearLayoutManager(appCompatActivity, LinearLayoutManager.VERTICAL, false)
//        binding!!.recyclerViewImage.dashboardRecyclerView.setHasFixedSize(true)
//        binding!!.recyclerViewImage.dashboardRecyclerView.layoutManager = layoutmanager
//        binding!!.recyclerViewImage.dashboardRecyclerView.adapter = imageAdapter
//

        val gridLayoutManager = GridLayoutManager(appCompatActivity,2 )
        binding!!.recyclerViewImage.dashboardRecyclerView.setHasFixedSize(true)
        binding!!.recyclerViewImage.dashboardRecyclerView.layoutManager = gridLayoutManager
        binding!!.recyclerViewImage.dashboardRecyclerView.adapter = imageAdapter


    }


}

