package dev.geronso.smartbusiness

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.card.view.*

class SearchAdapter(
    val postList: MutableList<Post>
) : RecyclerView.Adapter<SearchAdapter.ViewHolder>() {

    inner class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val image = view.card_image!!
        val name = view.card_name!!
        val tags = view.card_tags!!
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.card, parent, false))
    }

    override fun getItemCount(): Int = postList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        postList[position].image?.let {
            holder.image.setImageBitmap(postList[position].image)
        }
        holder.name.text = postList[position].name
        var allTags = ""
        for (tag in postList[position].tags) {
            allTags += "#$tag"
        }
        holder.tags.text = allTags
        // TODO callback to open post
    }
}