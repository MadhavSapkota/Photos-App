package com.example.imagepicker.image_picker.mvp
import android.net.Uri

class MainPresenter(
    private val mainView: MainView,
    private val mainModel: MainModel
) {

    fun onCreateView() {
        onClick()
    }

    fun onClick() {
        mainView.getBackObserable().doOnNext {
            mainModel.openImageGallery()
        }.subscribe()
    }

    fun setAdapter(imageData:ArrayList<Uri>) {
        mainView.showListItems(imageData, true)
        mainView.hidePickImage()
    }
}