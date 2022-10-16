package com.example.firebase

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.firebase.databinding.ActivityRegisterBinding
import com.google.firebase.auth.FirebaseAuth

class RegisterActivity : AppCompatActivity() {

    private var binding:ActivityRegisterBinding? =  null

    var auth: FirebaseAuth? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_register)

        auth = FirebaseAuth.getInstance()

        binding?.btnRegister?.setOnClickListener(View.OnClickListener {

            createUser()

        })

        binding?.tvLoginHere?.setOnClickListener(View.OnClickListener {
            startActivity(Intent(this@RegisterActivity, LoginActivity::class.java))
        })
    }

    private fun createUser() {
        val email: String = binding?.etRegEmail?.getText().toString()
        val password: String = binding?.etRegPass?.getText().toString()
        if (TextUtils.isEmpty(email)) {
            binding?.etRegEmail?.setError("Email cannot be empty")
            binding?.etRegEmail?.requestFocus()
        } else if (TextUtils.isEmpty(password)) {
            binding?.etRegPass?.setError("Password cannot be empty")
            binding?.etRegPass?.requestFocus()
        } else {
            auth!!.createUserWithEmailAndPassword(email, password).addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Toast.makeText(
                        this@RegisterActivity,
                        "User registered successfully",
                        Toast.LENGTH_SHORT
                    ).show()
                    startActivity(Intent(this@RegisterActivity, LoginActivity::class.java))
                } else {
                    Toast.makeText(
                        this@RegisterActivity,
                        "Registration Error: " + task.exception?.message,
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }



}