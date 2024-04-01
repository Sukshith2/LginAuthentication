package com.example.logandregister

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth

class Login : AppCompatActivity() {

    lateinit var auth : FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val loginform =findViewById<TextView>(R.id.loginForm)
        val logemail = findViewById<TextInputEditText>(R.id.logemail)
        val logpass = findViewById<TextInputEditText>(R.id.logpass)
        val sign = findViewById<TextView>(R.id.sign)
        val btnlog =findViewById<Button>(R.id.btnlog)


        sign.setOnClickListener {
            val intent = Intent(this,Register::class.java)
            startActivity(intent)

        }

        auth=Firebase.auth

        btnlog.setOnClickListener {
            val email = logemail.text.toString()
            val pass = logpass.text.toString()
            if (email.isNotEmpty() && pass.isNotEmpty()){
                loginUser(email,pass)
            }else{
                Toast.makeText(this, "Enter the email and pasword", Toast.LENGTH_SHORT).show()
            }
        }








    }

    private fun loginUser(email: String, pass: String) {
        auth.signInWithEmailAndPassword(email, pass)
            .addOnCompleteListener(this){task->
                if(task.isSuccessful){
                    val user = auth.currentUser
                    val Textemail = user?.email
                    Toast.makeText(this, "Login Sucessfull", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, MainActivity::class.java)
                    intent.putExtra("email",Textemail)
                    startActivity(intent)
                    finish()

                }else{

                    Toast.makeText(this, "Loign Failed", Toast.LENGTH_SHORT).show()
                }


            }


    }
}