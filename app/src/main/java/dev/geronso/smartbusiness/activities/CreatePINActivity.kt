package dev.geronso.smartbusiness.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.fraggjkee.smsconfirmationview.SmsConfirmationView
import dev.geronso.smartbusiness.Manager
import dev.geronso.smartbusiness.PINChecker
import dev.geronso.smartbusiness.PINStatus
import dev.geronso.smartbusiness.R
import kotlinx.android.synthetic.main.activity_create_pin.*

class CreatePINActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_pin)
        val pinView = findViewById<SmsConfirmationView>(R.id.pin_view)
        when(PINChecker.status) {
            PINStatus.UNSELECTED -> {
                pinView.onChangeListener = object : SmsConfirmationView.OnChangeListener {
                    override fun onCodeChange(code: String, isComplete: Boolean) {
                        if(code.length == 4) {
                            Manager.currentProfile?.pin = code
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
                        if(code.length == 4) {
                            if(code == Manager.currentProfile?.pin) {
                                PINChecker.status = PINStatus.CONFIRMED
                                openMenuActivity()
                            }
                            else {
                                pinView.enteredCode = ""
                            }
                        }
                    }
                }
                tv_enter_pin.text = "Подтвердите пароль"
            }

            else -> {}
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

    fun correctPIN(code: String): Boolean {
        return true
    }
}