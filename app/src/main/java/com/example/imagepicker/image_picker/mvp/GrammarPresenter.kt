package com.learn.grammarenglish.activity.grammar_dashboard.mvp

import com.learn.grammarenglish.activity.grammar_dashboard.dto.GrammarData
import com.learn.grammarenglish.databasemanager.ApplicationDatabase
import com.learn.grammarenglish.databasemanager.GrammarDbModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver
import timber.log.Timber

class GrammarPresenter(
    private val grammarView: GrammarView,
    private val grammarModel: GrammarModel,
    private val database: ApplicationDatabase
) {

    private val compositeDisposable = CompositeDisposable()
    var grammarData = ArrayList<GrammarData>()
     var tableId: Int? = null
    var gramId: Int? = null
    var gramName: String = ""
    var gramInfo: String = ""
    var isSelected:Boolean = false

    fun onCreateView() {
        clickFavGrammarItems()
        setGrammarList()
        setAdapter()
        onClickCard()
        onClick()
        clickFavDb()
    }

    fun getBackOnClick() {
        grammarModel.finished()
    }

    fun onClick() {
        grammarView.getBackObserable().doOnNext { grammarModel.finished() }.subscribe()
    }


    private fun getAdsRequest() {
        when {
            grammarView.checkNetwork() -> {
                grammarView.setAdsView()

            }

            else -> grammarView.disableAdsView()
        }
    }


    private fun setAdapter() {
        grammarView.showListItems(grammarData, true)
    }

    private fun setGrammarList() {
        grammarData = ArrayList()
        grammarData.add(GrammarData(1, "Subject Verb Agreement", "(Singular/Plural Subjects)"))
        grammarData.add(GrammarData(2, "Article", "(A, An,The and Exceptions)"))
        grammarData.add(GrammarData(3, "Causative Verbs", "(Have,Get,Make..)"))
        grammarData.add(GrammarData(4, "Question Tag", "(Is,Am,Are,Let,Seldom,Has,Have to,..)"))
        grammarData.add(GrammarData(5, "Sentences Transformation", "(Positive,Negative)"))
        grammarData.add(GrammarData(6, "Tense", "Present,Past and Future"))
        grammarData.add(GrammarData(7, "Voice Change", "(Active and Passive)"))
        grammarData.add(GrammarData(8, "Speech", "(Direct and Indirect)"))
        grammarData.add(GrammarData(9, "Preposition", "(In,On,At,.....)"))
        grammarData.add(GrammarData(10, "Connectives", "(Because,Since,As,Because of,....)"))
        grammarData.add(GrammarData(11, "If (Conditional Sentences)", "(1st,2nd and 3rd)"))

    }


    private fun onClickCard() {
        val disposableObserver = getBackgroundImageListItemClickObserver()
        grammarView.getNewsViewClickedObservable().subscribe(disposableObserver)
        compositeDisposable.add(disposableObserver)
    }

    private fun getBackgroundImageListItemClickObserver(): DisposableObserver<GrammarData> {
        return object : DisposableObserver<GrammarData>() {
            override fun onNext(grammarData: GrammarData) {
                grammarModel.getGrammarContents(
                    grammarData.getGrammarId(),
                    grammarData.getGrammarName(), grammarData.getGrammarInfo()
                )
            }

            override fun onError(e: Throwable) {
                System.err.println("Error" + e)
            }

            override fun onComplete() {
                Timber.e("Clicked")
            }
        }
    }

    private fun clickFavGrammarItems() {
        val disposableObserver = getFavItemClickObserver()
        grammarView.getFavouriteViewClickedObservable().subscribe(disposableObserver)
        compositeDisposable.add(disposableObserver)
    }

    private fun getFavItemClickObserver(): DisposableObserver<GrammarData> {
        return object : DisposableObserver<GrammarData>() {
            override fun onNext(grammarData: GrammarData) {
                isSelected = grammarData.isSelected
                gramId = grammarData.grammarId
                gramName = grammarData.getGrammarName()
                gramInfo = grammarData.getGrammarInfo()
            }

            override fun onError(e: Throwable) {
                System.err.println("Error" + e)
            }

            override fun onComplete() {
                Timber.e("Clicked")
            }
        }
    }

    fun removeFavDb() {
        gramId?.let { database.favoriteDao().deleteFav(it) }
    }

 fun clickFavDb() {
     grammarView.getFavouriteViewClickedObservable().doOnNext {
         checkFavItemsInDb()
     }.subscribe()
 }

    fun checkFavItemsInDb() {
        val count = gramId?.let { database.favoriteDao().checkIfExist(it) }
        if (count != null) {
            when{
                count<1->insertFavDB()
                else->  grammarView.getSavedFavouriteDialogMessage(grammarView.getSaveFavouriteItemsOperationErrorMessage())
            }
        }
    }

    fun insertFavDB() {
        database.favoriteDao().insertSingleCard(
            GrammarDbModel(
                grammarId = gramId!!,
                grammarName = gramName,
                grammarInfo = gramInfo,
                isSelected = isSelected
            )
        )
        grammarView.getSavedFavouriteDialogMessage(grammarView.getSaveFavouriteItemsOperationMessage())
    }
}