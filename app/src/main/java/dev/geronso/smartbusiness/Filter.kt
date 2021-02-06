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
                result.add(post)
            }
        }
        return result
    }

}