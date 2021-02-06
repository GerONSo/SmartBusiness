package dev.geronso.smartbusiness

class Filter (
    var searchRequest: String,
    var tags: MutableList<String>,
    val manager: Manager
) {
    fun getFilteredPosts() : MutableList<BigPost> {
        val result = mutableListOf<BigPost>()
        for(post in manager.allPostList) {
            if(post.title?.toLowerCase()?.contains(searchRequest.toLowerCase())!!) {
                val tags = post.tags?.split(" ")?.toMutableList()!!
                for(tag in tags) {
                    if(true !in manager.isCheckedTag.values || manager.isCheckedTag[tag] != null && manager.isCheckedTag[tag]!!
                        || tags.size == 1 && tags[0] == "") {
                        result.add(post)
                        break
                    }
                }
            }
        }
        return result
    }

}