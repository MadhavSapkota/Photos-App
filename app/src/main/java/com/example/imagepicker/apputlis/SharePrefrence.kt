package com.learn.grammarenglish.apputlis
import com.chibatching.kotpref.KotprefModel

/*this is sharedpreferences file where values related to the sharedpreferences
of this project is stored here.*/

object UserInfo : KotprefModel() {
    var token by stringPref()
    var loginStatus by booleanPref(default = false)
}

object RewardAdsInfo : KotprefModel() {
    var rewardStatus by booleanPref(default = false)
}
