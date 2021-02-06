package dev.geronso.smartbusiness

import android.R
import android.content.res.Resources
import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.smarteist.autoimageslider.SliderViewAdapter
import kotlinx.android.synthetic.main.slider_card.view.*


class SliderAdapter : SliderViewAdapter<SliderAdapter.ViewHolder>() {


    inner class ViewHolder (val view: View) : SliderViewAdapter.ViewHolder(view) {
        val imageView: ImageView = view.image!!
    }

    lateinit var resources: Resources

    override fun onCreateViewHolder(parent: ViewGroup?): ViewHolder {
        resources = parent?.resources!!
        return ViewHolder(LayoutInflater.from(parent?.context).inflate(dev.geronso.smartbusiness.R.layout.slider_card, parent, false))
    }

    override fun getCount(): Int = 3

    override fun onBindViewHolder(viewHolder: ViewHolder?, position: Int) {
        viewHolder?.imageView?.setImageBitmap(BitmapFactory.decodeResource(resources, dev.geronso.smartbusiness.R.drawable.city))
    }
}