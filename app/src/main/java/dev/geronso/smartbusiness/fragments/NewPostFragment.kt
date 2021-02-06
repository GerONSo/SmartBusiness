package dev.geronso.smartbusiness.fragments

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.fragment.app.viewModels
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import dev.geronso.smartbusiness.BigPost
import dev.geronso.smartbusiness.Post
import dev.geronso.smartbusiness.R
import dev.geronso.smartbusiness.ViewModel
import kotlinx.android.synthetic.main.fragment_new_post.*

class NewPostFragment : Fragment() {

    val images: MutableList<Bitmap> = mutableListOf()
    private val viewModel: ViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        btn_next.setOnClickListener {
            fst_const.visibility = View.GONE
            second_const.visibility = View.VISIBLE
        }
        images.add(BitmapFactory.decodeResource(resources, R.drawable.city))
        btn_public.setOnClickListener {
            sendPost()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_new_post, container, false)
    }


    private fun sendPost() {
        val post = BigPost(
            new_post_title.text.toString(),
            new_post_tags.text.toString(),
            new_post_text.text.toString(),
            new_post_contacts.text.toString(),
            (new_post_role.text.toString() == "Заказчик"),
            new_post_time_start.text.toString() + new_post_time_end.text.toString()
        )
        val database = Firebase.database.reference
        database.child("posts").child(viewModel.manager.currentProfile?.id.toString())
            .setValue(post)
    }

}