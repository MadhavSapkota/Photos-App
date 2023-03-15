package com.learn.grammarenglish.activity.grammar_dashboard.mvp
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.learn.grammarenglish.activity.grammar_dashboard.adapters.GrammarAdapter
import com.learn.grammarenglish.apputlis.NetworkUtil
import com.learn.grammarenglish.databinding.ActivityGrammarLayoutBinding
import com.jakewharton.rxbinding2.view.RxView
import com.learn.grammarenglish.R
import com.learn.grammarenglish.activity.grammar_dashboard.dto.GrammarData
import com.learn.grammarenglish.dialog.FavouriteItemsOperationsDialog
import io.reactivex.Observable
import java.util.ArrayList

class GrammarView(
    val appCompatActivity: AppCompatActivity,
    private val grammarAdapter: GrammarAdapter)

{
    var binding: ActivityGrammarLayoutBinding?=null

    fun start(binding_dashboard: ActivityGrammarLayoutBinding) {
        binding=binding_dashboard
        setGrammarAdapter()
    }

    fun checkNetwork(): Boolean {
        return NetworkUtil.checkInternetConnection(appCompatActivity)
    }

    fun setAdsView() {
        binding!!.myTemplate.visibility = View.VISIBLE
    }
    fun disableAdsView() {
        binding!!.myTemplate.visibility = View.GONE
    }

    fun getSavedFavouriteDialogMessage(message: String) {
        appCompatActivity.runOnUiThread(Runnable {
            FavouriteItemsOperationsDialog(appCompatActivity, message!!)
        })
    }


    fun getSaveFavouriteItemsOperationMessage(): String {
        return appCompatActivity.resources.getString(R.string.favourite_items_clicked_information)
    }

    fun getSaveFavouriteItemsOperationErrorMessage(): String {
        return appCompatActivity.resources.getString(R.string.favourite_items_clicked_errors_info)
    }

    fun showListItems(grammarData: ArrayList<GrammarData>, aboolean: Boolean) {
        grammarAdapter.showGrammarItem(grammarData, aboolean)
    }

    fun getBackObserable(): Observable<Any> {
        return RxView.clicks(binding!!.ivBack)
    }

    fun setGrammarAdapter() {
        var layoutmanager: LinearLayoutManager? = LinearLayoutManager(
            appCompatActivity, LinearLayoutManager.VERTICAL, false)
        binding!!.includesGrammarList.dashboardRecyclerView.setHasFixedSize(true)
        binding!!.includesGrammarList.dashboardRecyclerView.layoutManager = layoutmanager
        binding!!.includesGrammarList.dashboardRecyclerView.adapter = grammarAdapter
    }


    //news list clickable
    fun getNewsViewClickedObservable(): Observable<GrammarData> {
        return grammarAdapter.clickSubject1
    }



    fun getFavouriteViewClickedObservable(): Observable<GrammarData> {
        return grammarAdapter.clickSubject2
    }
}

