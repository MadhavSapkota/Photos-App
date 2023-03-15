package com.learn.grammarenglish.activity.grammar_dashboard.dto

class GrammarData(
    var grammarId: Int,
    var grammarName: String,
    var grammarInfo:String,
    var isSelected:Boolean=false
) {
    @JvmName("getGrammarId1")
    fun getGrammarId(): Int {
        return grammarId
    }

    @JvmName("getGrammarName1")
    fun getGrammarName(): String {
        return grammarName
    }

    @JvmName("getGrammarInfo1")
    fun getGrammarInfo(): String {
        return grammarInfo
    }

    @JvmName("getSelected")
    fun getSelected(): Boolean {
        return isSelected
    }

}