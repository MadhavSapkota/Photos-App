package com.learn.grammarenglish.activity.grammar_dashboard.di
import androidx.appcompat.app.AppCompatActivity
import com.learn.grammarenglish.activity.grammar_dashboard.adapters.GrammarAdapter
import com.learn.grammarenglish.activity.grammar_dashboard.mvp.GrammarModel
import com.learn.grammarenglish.activity.grammar_dashboard.mvp.GrammarPresenter
import com.learn.grammarenglish.activity.grammar_dashboard.mvp.GrammarView
import com.learn.grammarenglish.databasemanager.ApplicationDatabase

import com.learn.grammarenglish.app.AppActivity
import dagger.Module
import dagger.Provides

@Module
class GrammarModule(private val appCompatActivity: AppCompatActivity) {

    @AppActivity
    @Provides
    fun getGrammarView(grammarAdapter: GrammarAdapter): GrammarView {
        return GrammarView(appCompatActivity,grammarAdapter)
    }

    @Provides
    fun getGrammarModel(): GrammarModel {
        return GrammarModel(appCompatActivity)
    }

    @Provides
    fun getGrammarPresenter(
        grammarView: GrammarView,
        grammarModel: GrammarModel,
        database: ApplicationDatabase
    ): GrammarPresenter {
        return GrammarPresenter(grammarView,grammarModel,database)
    }

    @Provides
    fun getGrammarAdapter(): GrammarAdapter {
        return GrammarAdapter(appCompatActivity)
    }


}