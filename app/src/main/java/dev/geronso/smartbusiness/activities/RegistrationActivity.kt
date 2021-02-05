package dev.geronso.smartbusiness.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import androidx.activity.viewModels
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import dev.geronso.smartbusiness.Profile
import dev.geronso.smartbusiness.R
import dev.geronso.smartbusiness.ViewModel
import kotlinx.android.synthetic.main.activity_registration.*
import kotlinx.android.synthetic.main.activity_registration.btn_register
import kotlinx.android.synthetic.main.activity_registration.new_post_contact

class RegistrationActivity : AppCompatActivity() {

    private val viewModel: ViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
        actionBar?.hide()
        val emailEditText = new_post_title
        val phoneEditText = new_post_tags
        val loginEditText = new_post_contact
        val passwordEditText = findViewById<EditText>(R.id.new_pos_role)
        sendProfileId()
        btn_register.setOnClickListener {
            saveProfileData(
                emailEditText.text.toString(),
                phoneEditText.text.toString(),
                loginEditText.text.toString(),
                passwordEditText.text.toString()
            )
            openPINActivity()
        }
        btn_no_account.setOnClickListener {
            openMenuActivity()
        }
    }

    private fun sendProfileId() {
        val database = Firebase.database.reference
        var lastId: Long?
        viewModel.manager.currentProfile = Profile()
        val listener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if(viewModel.manager.currentProfile?.id == null) {
                    lastId = dataSnapshot.value as Long
                    viewModel.manager.currentProfile?.id = lastId
                    database.child("lastId").setValue(lastId?.plus(1))
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                Log.w("TAG", "getLastId:onCancelled", databaseError.toException())
            }
        }
        database.child("lastId").addListenerForSingleValueEvent(listener)
    }

    private fun sendProfile() {
        val database = Firebase.database.reference
        database.child("users").child(viewModel.manager.currentProfile?.id.toString()).setValue(viewModel.manager.currentProfile)
    }

    private fun saveProfileData(email: String, phone: String, login: String, password: String) {
        val profile = Profile(email, phone, login, password)
        val id = viewModel.manager.currentProfile?.id
        viewModel.manager.currentProfile = profile
        viewModel.manager.currentProfile?.id = id
        sendProfile()
    }

    private fun openPINActivity() {
        val intent = Intent(this, CreatePINActivity::class.java)
        startActivity(intent)
    }

    private fun openMenuActivity() {
        val intent = Intent(this, MenuActivity::class.java)
        startActivity(intent)
    }

}