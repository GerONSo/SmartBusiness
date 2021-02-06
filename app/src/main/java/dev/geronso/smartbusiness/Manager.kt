package dev.geronso.smartbusiness

import androidx.lifecycle.MutableLiveData

object Manager {
    var currentProfile: Profile? = null
    var currentProfile_: MutableLiveData<Profile> = MutableLiveData()
    var openFilterActivity: () -> Unit = {}
    var filter = Filter("", mutableListOf(), this)
    var filter_: MutableLiveData<Filter> = MutableLiveData(filter)
    var postList: MutableList<Post> = mutableListOf()
    var allPostList: MutableList<Post> = mutableListOf()
}