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
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase
import dev.geronso.smartbusiness.*
import kotlinx.android.synthetic.main.activity_registration.*
import kotlinx.android.synthetic.main.fragment_new_post.*
import kotlinx.android.synthetic.main.fragment_search.*


class SearchFragment : Fragment() {

    private val viewModel: ViewModel by viewModels()
    lateinit var searchAdapter: SearchAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel.manager.postList.clear()
        viewModel.manager.allPostList.clear()
        viewModel.manager.filteredPostList.clear()
        getPosts()
        val searchLayoutManager = GridLayoutManager(context, 2, LinearLayoutManager.VERTICAL, false)
        searchAdapter = SearchAdapter(viewModel.manager)
        rv_search.apply {
            layoutManager = searchLayoutManager
            adapter = searchAdapter
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
                et_search.setText("")
                viewModel.manager.openFilterActivity()
                true
            }
            false
        }
    }

    fun getPosts() {
        val database = Firebase.database.reference
        val listener = object : ChildEventListener {
            override fun onCancelled(databaseError: DatabaseError) {
                Log.w("TAG", "listener:onCancelled", databaseError.toException())
            }

            override fun onChildMoved(snapshot: DataSnapshot, previousChildName: String?) {}

            override fun onChildChanged(snapshot: DataSnapshot, previousChildName: String?) {}

            override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {
                val post = snapshot.getValue<BigPost>() as BigPost
                viewModel.manager.allPostList.add(post)
                viewModel.manager.postList = viewModel.manager.allPostList
                searchAdapter.notifyDataSetChanged()
            }

            override fun onChildRemoved(snapshot: DataSnapshot) {}
        }
        database.child("posts").addChildEventListener(listener)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search, container, false)
    }
}