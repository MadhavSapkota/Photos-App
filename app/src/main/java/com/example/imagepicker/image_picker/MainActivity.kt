package com.example.imagepicker.image_picker
import android.Manifest
import android.Manifest.permission.READ_MEDIA_IMAGES
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
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
                val uriList = ArrayList<Uri>()
                selectedImages?.let {
                    uriList.addAll(it)
                }

                if (selectedImages != null && selectedImages.size > 1) {
                        mainPresenter.setAdapter(uriList)
                    }
                } else {
                Toast.makeText(this, "Please select two images", Toast.LENGTH_SHORT).show()
            }
            }
        }
    }
