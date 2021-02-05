package dev.geronso.smartbusiness.activities

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.fraggjkee.smsconfirmationview.SmsConfirmationView
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import dev.geronso.smartbusiness.PINChecker
import dev.geronso.smartbusiness.PINStatus
import dev.geronso.smartbusiness.R
import dev.geronso.smartbusiness.ViewModel
import kotlinx.android.synthetic.main.activity_create_pin.*


class CreatePINActivity : AppCompatActivity() {

    private val viewModel: ViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_pin)
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
        actionBar?.hide()
        val pinView = findViewById<SmsConfirmationView>(R.id.pin_view)
        when (PINChecker.status) {
            PINStatus.UNSELECTED -> {
                pinView.onChangeListener = object : SmsConfirmationView.OnChangeListener {
                    override fun onCodeChange(code: String, isComplete: Boolean) {
                        if (code.length == 4) {
                            viewModel.manager.currentProfile?.pin = code
                            PINChecker.status = PINStatus.SELECTED
                            openConfirmPINActivity()
                        }
                    }
                }
                tv_enter_pin.text = "Введите 4-значный пароль"
            }

            PINStatus.SELECTED -> {
                pinView.onChangeListener = object : SmsConfirmationView.OnChangeListener {
                    override fun onCodeChange(code: String, isComplete: Boolean) {
                        if (code.length == 4) {
                            if (code == viewModel.manager.currentProfile?.pin) {
                                PINChecker.status = PINStatus.CONFIRMED
                                sendPin()
                                openMenuActivity()
                            } else {
                                pinView.enteredCode = ""
                            }
                        }
                    }
                }
                tv_enter_pin.text = "Подтвердите пароль"
            }

            else -> {
            }
        }
    }

    private fun openMenuActivity() {
        val intent = Intent(this, MenuActivity::class.java)
        startActivity(intent)
    }

    private fun openConfirmPINActivity() {
        val intent = Intent(this, CreatePINActivity::class.java)
        startActivity(intent)
    }

    private fun sendPin() {
        val database = Firebase.database.reference
        database.child("users").child(viewModel.manager.currentProfile?.id.toString()).child("pin")
            .setValue(viewModel.manager.currentProfile?.pin)
    }

    override fun onBackPressed() {
        if(PINChecker.status == PINStatus.CONFIRMED) {
            PINChecker.status = PINStatus.SELECTED
        }
        if(PINChecker.status == PINStatus.SELECTED) {
            PINChecker.status = PINStatus.UNSELECTED
        }
        super.onBackPressed()
    }
}