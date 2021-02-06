package dev.geronso.smartbusiness.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import dev.geronso.smartbusiness.*
import kotlinx.android.synthetic.main.fragment_filter.*
import kotlinx.android.synthetic.main.fragment_filter.et_search
import kotlinx.android.synthetic.main.fragment_search.*

class FilterFragment : Fragment() {

    private val viewModel: ViewModel by viewModels()
    lateinit var filterAdapter: FilterAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_filter, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel.manager.postList = viewModel.manager.filter.getFilteredPosts()
        et_search.setText(viewModel.manager.filter.searchRequest)
        et_search.setOnEditorActionListener { textView, actionId, keyEvent ->
            if(actionId == EditorInfo.IME_ACTION_SEARCH) {
                viewModel.manager.filter.searchRequest = et_search.text.toString()
                viewModel.manager.postList = viewModel.manager.filter.getFilteredPosts()
                filterAdapter.notifyDataSetChanged()
                et_search.onEditorAction(EditorInfo.IME_ACTION_DONE)
                true
            }
            false
        }

        val filterLayoutManager = GridLayoutManager(context, 2, LinearLayoutManager.VERTICAL, false)
        filterAdapter = FilterAdapter(viewModel.manager)
        rv_filter.apply {
            layoutManager = filterLayoutManager
            adapter = filterAdapter
        }
    }
}