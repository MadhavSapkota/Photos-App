package com.learn.grammarenglish.activity.grammar_dashboard.adapters
import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.jakewharton.rxbinding2.view.RxView
import com.learn.grammarenglish.R
import com.learn.grammarenglish.activity.grammar_dashboard.dto.GrammarData
import com.learn.grammarenglish.activity.grammar_dashboard.mvp.GrammarModel
import com.learn.grammarenglish.activity.grammar_dashboard.mvp.GrammarPresenter
import com.learn.grammarenglish.activity.grammar_dashboard.mvp.GrammarView
import com.learn.grammarenglish.databasemanager.ApplicationDatabase
import com.learn.grammarenglish.databinding.GrammarDataBinding
import io.reactivex.subjects.PublishSubject


class GrammarAdapter(var context: Context) : RecyclerView.Adapter<GrammarHolder>() {
    private var grammarList = ArrayList<GrammarData>()
    var clickSubject1 = PublishSubject.create<GrammarData>()
    var clickSubject2 = PublishSubject.create<GrammarData>()
    var position: Int? = null
    val database: ApplicationDatabase? = null
    val grammarView = GrammarView(context as AppCompatActivity,this)
    val grammarModel = GrammarModel(context as AppCompatActivity)
    val grammarPresenter = database?.let { GrammarPresenter(grammarView, grammarModel, it) }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GrammarHolder {
        val inflator = LayoutInflater.from(parent.context)
        val view = GrammarDataBinding.inflate(inflator, parent, false)
        val viewHolder = GrammarHolder(view)
        performCardListener(viewHolder, parent)
        saveFavItemsListener(viewHolder, parent)
        return viewHolder
    }

    //action listner when a user clicks news headings
    @SuppressLint("NotifyDataSetChanged")
    private fun performCardListener(
        viewHolder: GrammarHolder,
        parent: ViewGroup
    ) {
        RxView.clicks(viewHolder.rlTitle as View)
            .takeUntil(RxView.detaches(parent))
            .map { grammarList[viewHolder.adapterPosition] }
            .doOnEach {
                val position: Int = viewHolder.adapterPosition
                notifyItemChanged(position)
//                notifyDataSetChanged()

            }
            .subscribe(clickSubject1)
    }


    @SuppressLint("NotifyDataSetChanged", "CheckResult")
    private fun saveFavItemsListener(
        viewHolder: GrammarHolder,
        parent: ViewGroup
    ) {
        RxView.clicks(viewHolder.favImage as View)
            .takeUntil(RxView.detaches(parent))
            .map { grammarList[viewHolder.adapterPosition] }
            .doOnEach {
                val position: Int = viewHolder.adapterPosition
                notifyItemChanged(position)
            }
            .subscribe(clickSubject2)
    }


    @SuppressLint("ResourceAsColor")
    override fun onBindViewHolder(holder: GrammarHolder, position: Int) {
        val result = grammarList[position]
        holder.tvProvienceName.text = result.getGrammarName()
        holder.tvGrammarDetails.text = result.getGrammarInfo()
        holder.favImage.isChecked = result.isSelected
        holder.favImage.setOnCheckedChangeListener { _, isChecked ->

            result.isSelected = isChecked
            when (isChecked) {
                true -> grammarPresenter?.checkFavItemsInDb()
                else -> {
                    result.isSelected = false
                    grammarPresenter?.removeFavDb()
                }
            }
        }


        when {
            position % 2 == 0 -> {
                //cardView normally doesnot show the backgroundColor, so we have to use ContextCompat with context
                holder.cardName.setCardBackgroundColor(
                    ContextCompat.getColor(
                        context,
                        R.color.linkColor1
                    )
                )
            }
            else -> {
                holder.cardName.setCardBackgroundColor(
                    ContextCompat.getColor(
                        context,
                        R.color.colorSecond
                    )
                )
            }
        }

    }


    //show news list
    @SuppressLint("NotifyDataSetChanged")
    fun showGrammarItem(grammarData: ArrayList<GrammarData>, aboolean: Boolean) {
        when {
            aboolean -> grammarList.clear()
        }
        if (grammarData != null)
            this.grammarList.addAll((grammarData))
        notifyDataSetChanged()
    }


    override fun getItemCount(): Int {
        return grammarList.size
    }
}