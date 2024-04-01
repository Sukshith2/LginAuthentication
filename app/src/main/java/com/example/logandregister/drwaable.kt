package com.example.logandregister

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class drwaable : AppCompatActivity() {
    lateinit var auth:FirebaseAuth
    lateinit var user:FirebaseUser
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.nav_header)

        auth=FirebaseAuth.getInstance()
        // val userEmail=intent.getStringExtra("email")
        user= auth.currentUser!!
            val user_email = findViewById<TextView>(R.id.user_email)
        val userEmail=intent.getStringExtra("email")
        //val userName = intent.getStringExtra("name")
if (user==null){
    val intent = Intent(this, Login::class.java)
    startActivity(intent)
    finish()

}else{
    user_email.text=userEmail
}



    }
}