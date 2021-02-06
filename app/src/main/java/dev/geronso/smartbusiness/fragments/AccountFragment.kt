package dev.geronso.smartbusiness.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import dev.geronso.smartbusiness.FavouriteAdapter
import dev.geronso.smartbusiness.R
import dev.geronso.smartbusiness.ViewModel
import kotlinx.android.synthetic.main.fragment_account.*

class AccountFragment : Fragment() {

    private val viewModel: ViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val favouriteLayoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        favorite_recycler.apply {
            layoutManager = favouriteLayoutManager
            adapter = FavouriteAdapter(viewModel.manager)
        }
        val rarLayoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        rar_recycler.apply {
            layoutManager = rarLayoutManager
            adapter = FavouriteAdapter(viewModel.manager)
        }
        exit_btn.setOnClickListener {
            viewModel.manager.openLoginActivity()
        }
        login_field.text = viewModel.manager.currentProfile?.login
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_account, container, false)
    }
}