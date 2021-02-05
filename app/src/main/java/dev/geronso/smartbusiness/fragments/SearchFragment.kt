package dev.geronso.smartbusiness.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import dev.geronso.smartbusiness.Post
import dev.geronso.smartbusiness.R
import dev.geronso.smartbusiness.SearchAdapter
import kotlinx.android.synthetic.main.fragment_search.*


class SearchFragment : Fragment() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val list = mutableListOf(
            Post (
                null,
                "РосАтом",
                mutableListOf("tag1", "tag2")
            ),
            Post (
                null,
                "РосАтом",
                mutableListOf("tag1", "tag2")
            ),
            Post (
                null,
                "РосАтом",
                mutableListOf("tag1", "tag2")
            ),
            Post (
                null,
                "РосАтом",
                mutableListOf("tag1", "tag2")
            )
        )
        val searchLayoutManager = GridLayoutManager(context, 2, LinearLayoutManager.VERTICAL, false)
        rv_search.apply {
            layoutManager = searchLayoutManager
            adapter = SearchAdapter(list)
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