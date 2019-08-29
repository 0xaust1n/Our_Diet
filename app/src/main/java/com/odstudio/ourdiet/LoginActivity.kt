package com.odstudio.ourdiet

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*


class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        //Click to Register Act
        val alreadyreg: TextView = findViewById(R.id.gotaccount)
        alreadyreg.setOnClickListener {
            val registerIntent = Intent(this, RegisterActivity::class.java)
            startActivity(registerIntent)
        }

        val sendbtn: Button = findViewById(R.id.send_btn)
        sendbtn.setOnClickListener {
            //Starting Firebase Auth Function
            val email = et_email.text.toString()
            val password = et_password.text.toString()
            Log.d("login", "嘗試登陸 $email/***")
            FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
                .addOnCompleteListener {
                    if (it.isSuccessful) return@addOnCompleteListener
                    val homeIntent = Intent(this, Home::class.java)
                    startActivity(homeIntent)
                }
                .addOnFailureListener {
                    if (email.isEmpty() || password.isEmpty()) {
                        Toast.makeText(this, "請輸入你的賬號/密碼", Toast.LENGTH_SHORT).show()
                    }
                }
          }

    }

}
