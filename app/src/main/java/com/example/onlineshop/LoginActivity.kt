package com.example.onlineshop

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels

class LoginActivity : AppCompatActivity() {



    private val loginViewModel: LoginViewModel by viewModels()

    private lateinit var sharedPreferencesManagerBoolean: SharedPreferencesManager<Boolean>

    private lateinit var sharedPreferencesManagerString: SharedPreferencesManager<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        this.sharedPreferencesManagerBoolean = SharedPreferencesManager<Boolean>(this)
        this.sharedPreferencesManagerString = SharedPreferencesManager<String>(this)

        val button: Button = findViewById(R.id.loginButton)
        val username: EditText = findViewById(R.id.username)
        val password: EditText = findViewById(R.id.password)

        val dontHaveAccount: TextView = findViewById(R.id.dontHaveAccount)
        dontHaveAccount.setOnClickListener{
            startActivity(Intent(this, RegisterActivity::class.java))
            finish()
        }




        button.setOnClickListener {
            loginViewModel.login(username.text, password.text)
            loginViewModel.navigationEvent.observe(this) { event ->
                event.content.let { value ->
                    when (value) {
                        is LoginViewModel.NavigationResult.Successfully -> showSuccess(value.message)
                        is LoginViewModel.NavigationResult.Error -> showError(value.message)
                    }
                }
            }
        }

    }

    private fun showSuccess(message: String) {
        this.sharedPreferencesManagerBoolean.add( "IS_LOGIN_PREF", true)
        if(this.sharedPreferencesManagerBoolean.get("IS_LOGIN_PREF", false)) {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
        //Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }
    private fun showError(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }
}