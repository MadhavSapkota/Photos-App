package com.learn.grammarenglish.activity.grammar_dashboard.adapters

import android.widget.CheckBox
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.learn.grammarenglish.databinding.GrammarDataBinding

class GrammarHolder(val binding: GrammarDataBinding) : RecyclerView.ViewHolder(binding.root) {
    var tvProvienceName: TextView = binding!!.tvTitleName
    var rlTitle: RelativeLayout = binding!!.rlTitle
    var cardName: CardView = binding!!.cVGrammar
    var tvGrammarDetails = binding!!.grammarInfo
//    var rlFavourite: RelativeLayout = binding!!.rlButtonFav
    var favImage: CheckBox = binding!!.favButton
//    var favImage: ImageView = binding!!.favButton
}







