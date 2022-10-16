 package com.example.firebase

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import com.example.firebase.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth

 class MainActivity : AppCompatActivity() {

    private var binding: ActivityMainBinding? =  null

    var auth: FirebaseAuth? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        auth = FirebaseAuth.getInstance()

        binding?.btnLogout?.setOnClickListener(View.OnClickListener {
            auth!!.signOut()
            startActivity(Intent(this@MainActivity, LoginActivity::class.java))
        })

    }

    override fun onStart() {
        super.onStart()
        val user = auth!!.currentUser
        if (user == null) {
            startActivity(Intent(this@MainActivity, LoginActivity::class.java))
        }
    }
}