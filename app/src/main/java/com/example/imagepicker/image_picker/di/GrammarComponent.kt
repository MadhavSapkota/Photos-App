package com.learn.grammarenglish.activity.grammar_dashboard.di
import com.learn.grammarenglish.activity.grammar_dashboard.GrammarActivity
import com.learn.grammarenglish.app.AppActivity
import com.learn.grammarenglish.app.AppComponent
import dagger.Component

@AppActivity
@Component(modules=[(GrammarModule::class)], dependencies=[(AppComponent::class)])
interface GrammarComponent {
    abstract fun inject(dashboardActivity: GrammarActivity)
}