package dev.geronso.smartbusiness.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
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
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = ContextCompat.getColor(this, R.color.background)
        initFragments()
        openFragment(searchFragment)
        viewModel.manager.openFilterActivity = {
            openFragment(filterFragment)
        }
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

    }

    private fun openFragment(fragment: Fragment) {
        supportFragmentManager.commit {
            replace(R.id.menu_container_layout, fragment)
            addToBackStack(null)
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