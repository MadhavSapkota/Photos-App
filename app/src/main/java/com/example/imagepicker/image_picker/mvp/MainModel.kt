package com.example.imagepicker.image_picker.mvp
import androidx.appcompat.app.AppCompatActivity
import com.example.imagepicker.apputlis.ApiConstants.MAX_IMAGES
import com.example.imagepicker.apputlis.ApiConstants.REQUEST_CODE_PICK_IMAGES
import com.sangcomz.fishbun.FishBun
import com.sangcomz.fishbun.adapter.image.impl.GlideAdapter

class MainModel(
    private val appCompatActivity: AppCompatActivity) {

    fun openImageGallery(){
        FishBun.with(appCompatActivity)
            .setImageAdapter(GlideAdapter())
            .setMaxCount(MAX_IMAGES)
            .startAlbumWithOnActivityResult(REQUEST_CODE_PICK_IMAGES)
    }

}

