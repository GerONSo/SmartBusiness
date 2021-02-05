package dev.geronso.smartbusiness.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import dev.geronso.smartbusiness.R
import kotlinx.android.synthetic.main.activity_login.*


class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        val regButton = findViewById<TextView>(R.id.btn_register)
        val loginEditText = findViewById<EditText>(R.id.et_login)
        val passwordEditText = findViewById<EditText>(R.id.et_password)
        regButton.setOnClickListener {
            val database = Firebase.database
            val myRef = database.getReference("message")
            myRef.setValue("Hello, World!")
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

