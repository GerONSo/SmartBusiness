package dev.geronso.smartbusiness

class Filter (
    var searchRequest: String,
    var tags: MutableList<String>,
    val manager: Manager
) {
    fun getFilteredPosts() : MutableList<Post> {
        val result = mutableListOf<Post>()
        for(post in manager.allPostList) {
            if(searchRequest.toLowerCase() in post.name.toLowerCase()) {
                result.add(post)
            }
        }
        return result
    }

}