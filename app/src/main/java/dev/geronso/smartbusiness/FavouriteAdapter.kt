package dev.geronso.smartbusiness

import android.content.res.Resources
import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.card.view.*

class FavouriteAdapter(open val manager: Manager) : RecyclerView.Adapter<FavouriteAdapter.ViewHolder>(){
    inner class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val image = view.card_image!!
        val name = view.card_name!!
        val tags = view.card_tags!!
    }

    lateinit var resources: Resources

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        resources = parent.resources
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.card_account, parent, false))
    }

    override fun getItemCount(): Int = 3

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//        manager.postList[position].image?.let {
        holder.image.setImageBitmap(BitmapFactory.decodeResource(resources, R.drawable.city))
//        }
        holder.name.text = manager.allPostList[0].title
        var allTags = manager.allPostList[0].tags
        holder.tags.text = allTags
        holder.view.setOnClickListener {
            manager.openPostActivity()
        }
        // TODO callback to open post
    }
}