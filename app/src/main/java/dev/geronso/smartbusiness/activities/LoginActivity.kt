package dev.geronso.smartbusiness.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import dev.geronso.smartbusiness.R
import kotlinx.android.synthetic.main.activity_login.*


class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
        actionBar?.hide()
        val regButton = findViewById<TextView>(R.id.btn_register)
        val loginEditText = findViewById<EditText>(R.id.new_post_contact)
        val passwordEditText = findViewById<EditText>(R.id.new_pos_role)
        regButton.setOnClickListener {
            val intent = Intent(this, RegistrationActivity::class.java)
            startActivity(intent)
        }
        btn_login.setOnClickListener {
            if(isCorrectLoginPassword(loginEditText.text.toString(), passwordEditText.text.toString())) {
                startMenuActivity()
            }
        }
    }

    private fun isCorrectLoginPassword(login: String, password: String): Boolean {
        return true
    }

    private fun startMenuActivity() {
        val intent = Intent(this, MenuActivity::class.java)
        startActivity(intent)
    }
}

