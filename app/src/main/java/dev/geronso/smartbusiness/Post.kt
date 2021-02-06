package dev.geronso.smartbusiness

import android.graphics.Bitmap

data class Post (
    var image: Bitmap?,
    var title: String,
    var tags: MutableList<String>
)

data class BigPost (
    var title: String? = null,
    var tags: String? = null,
    var description: String? = null,
    var contacts: String? = null,
    var isCustomer: Boolean? = null,
    var openingHours: String? = null
)