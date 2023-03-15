package com.learn.grammarenglish.activity.grammar_dashboard.mvp
import androidx.appcompat.app.AppCompatActivity
import com.learn.grammarenglish.activity.grammar_details.GrammarDetailsActivity
import com.learn.grammarenglish.apputlis.AppUtils

class GrammarModel(
    private val appCompatActivity: AppCompatActivity) {

    fun getGrammarContents(grammarId: Int, grammarName: String,grammarInfo:String){
        GrammarDetailsActivity.start(appCompatActivity,grammarId,grammarName,grammarInfo)

    }

    fun finished(){
//        AppUtils.setRewardVideoAds(appCompatActivity)
        AppUtils.setIntersellarAds(appCompatActivity)
    }

}

