package dev.geronso.smartbusiness.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import dev.geronso.smartbusiness.Manager
import dev.geronso.smartbusiness.Profile
import dev.geronso.smartbusiness.R
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_registration.*
import kotlinx.android.synthetic.main.activity_registration.btn_register
import kotlinx.android.synthetic.main.activity_registration.et_login

class RegistrationActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)
        val emailEditText = et_email
        val phoneEditText = et_phone
        val loginEditText = et_login
        val passwordEditText = findViewById<EditText>(R.id.et_password)
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

    private fun saveProfileData(email: String, phone: String, login: String, password: String) {
        val profile = Profile(email, phone, login, password)
        Manager.currentProfile = profile
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