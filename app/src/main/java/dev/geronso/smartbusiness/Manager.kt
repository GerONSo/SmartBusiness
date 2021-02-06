package dev.geronso.smartbusiness

import androidx.lifecycle.MutableLiveData

object Manager {
    var currentProfile: Profile? = null
    var currentProfile_: MutableLiveData<Profile> = MutableLiveData()
    var openFilterActivity: () -> Unit = {}
    var openPostActivity: () -> Unit = {}
    var openSearchFragment: () -> Unit = {}
    var openTagsFilterFragment: () -> Unit = {}
    var popBackFragmentStack: () -> Unit = {}
    var openLoginActivity: () -> Unit = {}
    var filter = Filter("", mutableListOf(), this)
    var filter_: MutableLiveData<Filter> = MutableLiveData(filter)
    var postList: MutableList<BigPost> = mutableListOf(
        BigPost(

        )
    )
    var filteredPostList: MutableList<BigPost> = mutableListOf()
    var allPostList: MutableList<BigPost> = mutableListOf()
    var tagList: MutableList<String> = mutableListOf()
    var isCheckedTag: HashMap<String, Boolean> = hashMapOf()
}