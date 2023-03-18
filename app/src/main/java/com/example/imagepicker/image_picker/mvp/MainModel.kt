package com.example.imagepicker.image_picker.mvp
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import com.example.imagepicker.apputlis.AppUtils
import com.example.imagepicker.apputlis.ApiConstants.REQUEST_CODE_PICK_IMAGES
import com.example.imagepicker.image_picker.MainActivity
import com.sangcomz.fishbun.FishBun
import com.sangcomz.fishbun.adapter.image.impl.GlideAdapter

class MainModel(
    private val appCompatActivity: AppCompatActivity) {

    fun finished(){
        AppUtils.backObserables(appCompatActivity)
    }
    fun getMainView() {
        MainActivity.start(appCompatActivity)
    }


    fun openImageGallery(){
//        val intent = Intent(Intent.ACTION_GET_CONTENT)
//        intent.type = "image/*"
//        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true)
//        intent.setAction(Intent.ACTION_GET_CONTENT)
//        appCompatActivity.startActivityForResult(Intent.createChooser(intent, "Select Images"), REQUEST_CODE_PICK_IMAGES)

        FishBun.with(appCompatActivity)
            .setImageAdapter(GlideAdapter())
            .setMaxCount(2)
            .startAlbumWithOnActivityResult(REQUEST_CODE_PICK_IMAGES)
    }

}

