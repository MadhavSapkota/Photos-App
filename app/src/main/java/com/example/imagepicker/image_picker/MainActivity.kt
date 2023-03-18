package com.example.imagepicker.image_picker

import android.Manifest
import android.Manifest.permission.READ_MEDIA_IMAGES
import android.R.attr.path
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.os.Parcelable
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.imagepicker.app.AppApplication
import com.example.imagepicker.databinding.ActivityMainBinding
import com.example.imagepicker.image_picker.di.DaggerMainComponent
import com.example.imagepicker.image_picker.di.MainModule
import com.example.imagepicker.image_picker.mvp.MainPresenter
import com.example.imagepicker.image_picker.mvp.MainView
import com.sangcomz.fishbun.FishBun
import com.sangcomz.fishbun.FishBun.Companion.INTENT_PATH
import javax.inject.Inject


class MainActivity : AppCompatActivity() {
    @Inject
    lateinit var mainView: MainView

    @Inject
    lateinit var mainPresenter: MainPresenter


    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val mvpKotlinApplication = AppApplication()
        DaggerMainComponent.builder()
            .appComponent(mvpKotlinApplication.get(this).appComponent)
            .mainModule(MainModule(this))
            .build()
            .inject(this)
        binding = ActivityMainBinding.inflate(layoutInflater)

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(READ_MEDIA_IMAGES), PackageManager.PERMISSION_GRANTED)
        }
        val view = binding.root
        setContentView(view)
        mainView.start(binding)
        mainPresenter.onCreateView()
    }

    override fun onResume() {
        super.onResume()
    }


    companion object {
        fun start(context: Context) {
            context.startActivity(Intent(context, MainActivity::class.java))
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            FishBun.FISHBUN_REQUEST_CODE -> if (resultCode == RESULT_OK) {
                val selectedImages = data?.getParcelableArrayListExtra<Uri>(FishBun.INTENT_PATH)
                if (selectedImages != null) {
//                    for (i in 0 until 50) {
                        mainPresenter.setAdapter(selectedImages)
//                    }



                    }
                } else {
                    println("No images selected")
                }
            }
        }
    }





//    @Deprecated("Deprecated in Java")
//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        super.onActivityResult(requestCode, resultCode, data)
//        if (requestCode == REQUEST_CODE_PICK_IMAGES && resultCode == RESULT_OK) {
//            val selectedImage= ArrayList<Uri>()
//            val clipData = data?.clipData
//
//            println("Clip Data $clipData")
//            if (clipData != null) {
//                // Multiple images selected
//                for (i in 0 until clipData.itemCount) {
//                    if (selectedImage.size < MAX_IMAGES) {
//                        selectedImage.add(clipData.getItemAt(i).uri)
//                        println("Selected Image$selectedImage")
//                    }
//                }
//            } else {
//                // Single image selected
//                val imageUri = data?.data?.path
//                if (imageUri != null && selectedImage.size < MAX_IMAGES) {
//                    println("UnSelected Image$selectedImage")
//                    selectedImage.add(Uri.parse(imageUri.toString()))
//                }
//            }
//            mainPresenter.setAdapter(selectedImage)
//
//        }
//
//    }


//}