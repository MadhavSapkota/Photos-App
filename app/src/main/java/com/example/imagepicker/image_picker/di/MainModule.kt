package com.example.imagepicker.image_picker.di
import androidx.appcompat.app.AppCompatActivity
import com.example.imagepicker.app.AppActivity
import com.example.imagepicker.image_picker.adapter.ImageAdapter
import com.example.imagepicker.image_picker.mvp.MainModel
import com.example.imagepicker.image_picker.mvp.MainPresenter
import com.example.imagepicker.image_picker.mvp.MainView
import dagger.Module
import dagger.Provides

@Module
class MainModule(private val appCompatActivity: AppCompatActivity) {

    @AppActivity
    @Provides
    fun getMainView(imageAdapter:ImageAdapter): MainView {
        return MainView(appCompatActivity, imageAdapter)

    }

    @Provides
    fun getMainModel(): MainModel {
        return MainModel(appCompatActivity)
    }

    @Provides
    fun getMainPresenter(
        mainView: MainView,
        mainModel: MainModel,
    ): MainPresenter {
        return MainPresenter(mainView,mainModel)
    }

    @Provides
    fun getImageAdapter(): ImageAdapter {
        return ImageAdapter(appCompatActivity)
    }

}