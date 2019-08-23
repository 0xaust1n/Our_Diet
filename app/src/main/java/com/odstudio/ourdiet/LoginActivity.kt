package com.odstudio.ourdiet

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.widget.Button
import android.widget.TextView


class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val alreadyreg: TextView = findViewById(R.id.gotaccount)
        alreadyreg.setOnClickListener {
            val registerIntent = Intent(this, RegisterActivity::class.java)
            startActivity(registerIntent)
        }

        val sendbtn: Button = findViewById(R.id.send_btn)
        sendbtn.setOnClickListener {
            val homeIntent = Intent(this, Home::class.java)
            startActivity(homeIntent)
        }

    }

}
