package dev.geronso.smartbusiness.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import dev.geronso.smartbusiness.R
import dev.geronso.smartbusiness.SliderAdapter
import dev.geronso.smartbusiness.ViewModel
import kotlinx.android.synthetic.main.fragment_post.*

class PostFragment : Fragment() {

    private val viewModel: ViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_post, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        imageSlider.setSliderAdapter(SliderAdapter())
        name_text.text = viewModel.manager.allPostList[0].title
        tags.text = viewModel.manager.allPostList[0].tags
        contacts.text = viewModel.manager.allPostList[0].contacts

    }
}