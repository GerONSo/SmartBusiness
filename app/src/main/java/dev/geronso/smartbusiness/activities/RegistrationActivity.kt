package dev.geronso.smartbusiness.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.WindowManager
import android.widget.EditText
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import com.google.firebase.auth.ktx.auth
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
import kotlinx.android.synthetic.main.activity_registration.et_login

class RegistrationActivity : AppCompatActivity() {

    private val viewModel: ViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = ContextCompat.getColor(this, R.color.colorAccent)
        val emailEditText = et_email
        val phoneEditText = et_phone_numer
        val loginEditText = et_login
        val passwordEditText = findViewById<EditText>(R.id.et_passwd)
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
                    btn_register.isClickable = true
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
        val profile = Profile(email = email, phone = phone, login = login, password = password)
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