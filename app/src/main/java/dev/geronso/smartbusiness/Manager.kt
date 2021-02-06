package dev.geronso.smartbusiness


import androidx.fragment.app.FragmentManager
import androidx.lifecycle.MutableLiveData

object Manager {
    var currentProfile: Profile? = null
    var currentProfile_: MutableLiveData<Profile> = MutableLiveData()
    var openFilterActivity: () -> Unit = {}
    var openPostActivity: () -> Unit = {}
    var openSearchFragment: () -> Unit = {}
    lateinit var getFragmentManager: () -> FragmentManager
    var popBackFragmentStack: () -> Unit = {}
    var filter = Filter("", mutableListOf(), this)
    var filter_: MutableLiveData<Filter> = MutableLiveData(filter)
    var postList: MutableList<BigPost> = mutableListOf(
        BigPost(

        )
    )
    var filteredPostList: MutableList<BigPost> = mutableListOf()
    var allPostList: MutableList<BigPost> = mutableListOf()
}