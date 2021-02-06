package dev.geronso.smartbusiness.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase
import dev.geronso.smartbusiness.BigPost
import dev.geronso.smartbusiness.R
import dev.geronso.smartbusiness.TagAdapter
import dev.geronso.smartbusiness.ViewModel
import kotlinx.android.synthetic.main.fragment_tags_filter.*

class TagsFilterFragment : Fragment() {

    private val viewModel: ViewModel by viewModels()
    lateinit var tagAdapter: TagAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tags_filter, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel.manager.tagList.clear()
        tagAdapter = TagAdapter(viewModel.manager)
        val tagsFilterLayoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        tags_recycler.apply {
            layoutManager = tagsFilterLayoutManager
            adapter = tagAdapter
        }
        getTags()
        exit_btn_tags.setOnClickListener {
            viewModel.manager.popBackFragmentStack()
        }
    }

    fun getTags() {
        val database = Firebase.database.reference
        val listener = object : ChildEventListener {
            override fun onCancelled(databaseError: DatabaseError) {
                Log.w("TAG", "listener:onCancelled", databaseError.toException())
            }

            override fun onChildMoved(snapshot: DataSnapshot, previousChildName: String?) {}

            override fun onChildChanged(snapshot: DataSnapshot, previousChildName: String?) {}

            override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {
                val tag = snapshot.getValue<String>()!!
                val tmp = mutableListOf<String>()
                tmp.addAll(viewModel.manager.tagList)
                tmp.add(tag)
                viewModel.manager.tagList = tmp
                if(viewModel.manager.isCheckedTag[tag] == null) {
                    viewModel.manager.isCheckedTag[tag] = true
                }
                tagAdapter.notifyDataSetChanged()
            }

            override fun onChildRemoved(snapshot: DataSnapshot) {}
        }
        database.child("tags").addChildEventListener(listener)
    }
}