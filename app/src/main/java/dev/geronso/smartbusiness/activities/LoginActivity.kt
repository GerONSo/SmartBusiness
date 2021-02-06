package dev.geronso.smartbusiness.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Window
import android.view.WindowManager
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase
import dev.geronso.smartbusiness.BigPost
import dev.geronso.smartbusiness.Profile
import dev.geronso.smartbusiness.R
import dev.geronso.smartbusiness.ViewModel
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.fragment_new_post.*


class LoginActivity : AppCompatActivity() {

    val listeners: MutableList<ValueEventListener> = mutableListOf()
    private val viewModel: ViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = ContextCompat.getColor(this, R.color.colorAccent)

        val regButton = findViewById<TextView>(R.id.btn_register)
        val loginEditText = findViewById<EditText>(R.id.et_login)
        val passwordEditText = findViewById<EditText>(R.id.et_passwd)
        val profileObserver = Observer<Profile> { startMenuActivity() }
        regButton.setOnClickListener {
            viewModel.manager.currentProfile_.removeObserver(profileObserver)
            val intent = Intent(this, RegistrationActivity::class.java)
            startActivity(intent)
        }
        btn_login.setOnClickListener {
            isCorrectLoginPassword(loginEditText.text.toString(), passwordEditText.text.toString())
        }
        viewModel.manager.currentProfile_.observe(this, profileObserver)
    }

    private fun isCorrectLoginPassword(login: String, password: String): Boolean {

        val database = Firebase.database.reference.child("users")
        for(i in 0 until 100) {
            val listener = object : ChildEventListener {
                override fun onCancelled(databaseError: DatabaseError) {
                    Log.w("TAG", "listener:onCancelled", databaseError.toException())
                }

                override fun onChildMoved(snapshot: DataSnapshot, previousChildName: String?) {}

                override fun onChildChanged(snapshot: DataSnapshot, previousChildName: String?) {}

                override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {
                    val profile = snapshot.getValue<Profile>() as Profile
                    if(login == profile.login && password == profile.password) {
                        viewModel.manager.currentProfile_.value = profile
                        viewModel.manager.currentProfile = profile
                    }
                }

                override fun onChildRemoved(snapshot: DataSnapshot) {}
            }
            database.addChildEventListener(listener)
        }
        return true
    }


    private fun startMenuActivity() {
        val intent = Intent(this, MenuActivity::class.java)
        startActivity(intent)
    }
}

