package dev.geronso.smartbusiness.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.fragment.app.add
import androidx.fragment.app.commit
import dev.geronso.smartbusiness.R
import dev.geronso.smartbusiness.ViewModel
import dev.geronso.smartbusiness.fragments.*
import kotlinx.android.synthetic.main.activity_menu.*

class MenuActivity : AppCompatActivity() {

    lateinit var searchFragment: SearchFragment
    lateinit var newPostFragment: NewPostFragment
    lateinit var messengerFragment: MessengerFragment
    lateinit var accountFragment: AccountFragment
    lateinit var filterFragment: FilterFragment

    private val viewModel: ViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)
        initFragments()
        openFragment(searchFragment)
        bottom_bar.setOnNavigationItemSelectedListener { item ->
            when(item.itemId) {
                R.id.search_item -> {
                    openFragment(searchFragment)
                    true
                }

                R.id.new_post_item -> {
                    openFragment(newPostFragment)
                    true
                }

                R.id.message_item -> {
                    openFragment(messengerFragment)
                    true
                }

                R.id.account_item -> {
                    openFragment(accountFragment)
                    true
                }
                else -> {
                    false
                }
            }
        }
    }

    override fun onBackPressed() {
        if(viewModel.manager.currentProfile?.password == null) {
            super.onBackPressed()
        }
    }

    private fun openFragment(fragment: Fragment) {
        supportFragmentManager.commit {
            replace(R.id.menu_container_layout, fragment)
        }
    }

    private fun initFragments() {
        searchFragment = SearchFragment()
        newPostFragment = NewPostFragment()
        messengerFragment = MessengerFragment()
        accountFragment = AccountFragment()
        filterFragment = FilterFragment()
    }
}