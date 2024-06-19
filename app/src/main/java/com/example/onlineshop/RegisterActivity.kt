package com.example.onlineshop

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels

class RegisterActivity : AppCompatActivity() {

    private val registerViewModel: RegisterViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val haveAccount: TextView = findViewById(R.id.alreadyHaveAccount)
        haveAccount.setOnClickListener{
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }

        val button: Button = findViewById(R.id.registerButton)
        val username: EditText = findViewById(R.id.username)
        val password: EditText = findViewById(R.id.password)


        button.setOnClickListener {
            registerViewModel.register(username.text, password.text)
            registerViewModel.navigationEvent.observe(this) { event ->
                event.content.let { value ->
                    when (value) {
                        is RegisterViewModel.NavigationResult.Successfully -> showSuccess(value.message)
                        is RegisterViewModel.NavigationResult.Error -> showError(value.message)
                    }
                }
            }

        }
    }

    private fun showSuccess(message: String) {
        startActivity(Intent(this, LoginActivity::class.java))
        finish()
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }
    private fun showError(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }
}