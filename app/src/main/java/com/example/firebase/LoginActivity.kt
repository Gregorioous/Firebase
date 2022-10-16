package com.example.firebase

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.firebase.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {

    private var binding: ActivityLoginBinding? =  null

    var auth: FirebaseAuth? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)

        auth = FirebaseAuth.getInstance()

        binding?.btnLogin?.setOnClickListener(View.OnClickListener {

            loginUser()

        })

        binding?.tvRegisterHere?.setOnClickListener(View.OnClickListener {
            startActivity(Intent(this@LoginActivity, RegisterActivity::class.java))
        })

    }

    private fun loginUser() {
        val email: String = binding?.etLoginEmail?.getText().toString()
        val password: String = binding?.etLoginPass?.getText().toString()
        if (TextUtils.isEmpty(email)) {
            binding?.etLoginEmail?.setError("Email cannot be empty")
            binding?.etLoginEmail?.requestFocus()
        } else if (TextUtils.isEmpty(password)) {
            binding?.etLoginPass?.setError("Password cannot be empty")
            binding?.etLoginPass?.requestFocus()
        } else {
            auth!!.signInWithEmailAndPassword(email, password).addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Toast.makeText(
                        this@LoginActivity,
                        "User logged in successfully",
                        Toast.LENGTH_SHORT
                    ).show()
                    startActivity(Intent(this@LoginActivity, MainActivity::class.java))
                } else {
                    Toast.makeText(
                        this@LoginActivity,
                        "Log in Error: " + task.exception?.message,
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }
}