package com.learn.grammarenglish.activity.grammar_dashboard
import android.content.Context
import android.content.Intent
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.learn.grammarenglish.app.AppApplication
import com.learn.grammarenglish.apputlis.ApiConstants
import com.learn.grammarenglish.databinding.ActivityGrammarLayoutBinding
import com.google.android.ads.nativetemplates.NativeTemplateStyle
import com.google.android.ads.nativetemplates.TemplateView
import com.google.android.gms.ads.*
import com.learn.grammarenglish.activity.grammar_dashboard.di.DaggerGrammarComponent
import com.learn.grammarenglish.activity.grammar_dashboard.di.GrammarModule
import com.learn.grammarenglish.activity.grammar_dashboard.mvp.GrammarPresenter
import com.learn.grammarenglish.activity.grammar_dashboard.mvp.GrammarView
import javax.inject.Inject


class GrammarActivity : AppCompatActivity() {
    @Inject
    lateinit var grammarView: GrammarView

    @Inject
    lateinit var grammarPresenter: GrammarPresenter


    private lateinit var binding: ActivityGrammarLayoutBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val mvpKotlinApplication = AppApplication()
        DaggerGrammarComponent.builder()
            .appComponent(mvpKotlinApplication.get(this).appComponent)
            .grammarModule(GrammarModule(this))
            .build()
            .inject(this)
        binding = ActivityGrammarLayoutBinding.inflate(layoutInflater)
        MobileAds.initialize(this){
            loadNativeAd(binding)
        }
        val view = binding.root
        setContentView(view)
        grammarView.start(binding)
        grammarPresenter.onCreateView()
    }

    override fun onResume() {
        super.onResume()
        loadNativeAd(binding)
    }

    //For the banner ads
    private fun loadNativeAd(binding: ActivityGrammarLayoutBinding){
        val adLoader: AdLoader = AdLoader.Builder(this, ApiConstants.NATIVEADS)
            .forNativeAd { nativeAd ->
                val styles =
                    NativeTemplateStyle.Builder().withMainBackgroundColor(ColorDrawable()).build()
                val template: TemplateView = binding!!.myTemplate
                binding!!.myTemplate.visibility = View.VISIBLE
                template.setStyles(styles)
                template.setNativeAd(nativeAd)
            }
            .build()

        adLoader.loadAd(AdRequest.Builder().build())
    }


    //Splash Activity is started.
    companion object {
        fun start(context: Context)
        {
            context.startActivity(Intent(context, GrammarActivity::class.java))
        }
    }

    override fun onBackPressed() {
        grammarPresenter.getBackOnClick()
    }

}