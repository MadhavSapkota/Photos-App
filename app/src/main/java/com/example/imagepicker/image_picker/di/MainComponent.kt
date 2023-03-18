package com.example.imagepicker.image_picker.di
import com.example.imagepicker.app.AppActivity
import com.example.imagepicker.app.AppComponent
import com.example.imagepicker.image_picker.MainActivity
import dagger.Component

@AppActivity
@Component(modules=[(MainModule::class)], dependencies=[(AppComponent::class)])
interface MainComponent {
    abstract fun inject(mainActivity: MainActivity)
}