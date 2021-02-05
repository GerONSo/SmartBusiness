package dev.geronso.smartbusiness

import androidx.lifecycle.MutableLiveData

object Manager {
    var currentProfile: Profile? = null
    var currentProfile_: MutableLiveData<Profile> = MutableLiveData()
}