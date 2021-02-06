package dev.geronso.smartbusiness.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.activity.viewModels
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import dev.geronso.smartbusiness.Post
import dev.geronso.smartbusiness.R
import dev.geronso.smartbusiness.SearchAdapter
import dev.geronso.smartbusiness.ViewModel
import kotlinx.android.synthetic.main.fragment_search.*


class SearchFragment : Fragment() {

    private val viewModel: ViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel.manager.allPostList = mutableListOf(
            Post(null, "РосАтом", mutableListOf("tag1", "tag2"))
        )
        val searchLayoutManager = GridLayoutManager(context, 2, LinearLayoutManager.VERTICAL, false)
        rv_search.apply {
            layoutManager = searchLayoutManager
            adapter = SearchAdapter(viewModel.manager)
        }
        et_search.setOnFocusChangeListener { view, isFocused ->
            if(isFocused) {
                Log.d("TAG", "focused")
            }
            else {
                Log.d("TAG", "not focused")
            }
        }
        et_search.setOnEditorActionListener { textView, actionId, keyEvent ->
            if(actionId == EditorInfo.IME_ACTION_SEARCH) {
                viewModel.manager.filter.searchRequest = et_search.text.toString()
                viewModel.manager.openFilterActivity()
                true
            }
            false
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search, container, false)
    }
}