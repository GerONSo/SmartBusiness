package dev.geronso.smartbusiness

import android.graphics.Bitmap

data class Post (
    var image: Bitmap?,
    var name: String,
    var tags: MutableList<String>
)