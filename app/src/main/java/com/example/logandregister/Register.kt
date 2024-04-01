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
import com.google.firebase.database.FirebaseDatabase

class Register : AppCompatActivity() {

    lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_register)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        val signform = findViewById<TextView>(R.id.signForm)
        val signname = findViewById<TextInputEditText>(R.id.signname)
        val signemail =findViewById<TextInputEditText>(R.id.signemail)
        val signnumber =findViewById<TextInputEditText>(R.id.signnumber)
        val btnsign =findViewById<Button>(R.id.btnsign)
        val log = findViewById<TextView>(R.id.sign)
        val signpass= findViewById<TextInputEditText>(R.id.signpass)

        auth = Firebase.auth
        log.setOnClickListener {
            val intent = Intent(this, Login::class.java)
            startActivity(intent)

        }




        btnsign.setOnClickListener {
            val name = signname.text.toString()
            val number = signnumber.text.toString()
            val pass = signpass.text.toString()

            val email = signemail.text.toString()
            if(name.isNotEmpty() && email.isNotEmpty() && number.isNotEmpty() && pass.isNotEmpty()){
                userData(name, email, pass, number)
            }else{
                Toast.makeText(this, "fill all the fileds", Toast.LENGTH_SHORT).show()
            }
        }

 }

    private fun userData(name: String, email: String, pass: String, number: String) {
        auth.createUserWithEmailAndPassword(email, pass)
            .addOnCompleteListener(this){task->
                if(task.isSuccessful){
                    val user = auth.currentUser
                    val uid = user?.uid

                    if(uid!=null){
                        Userauth(name, pass, email, number)

                    }
                    startActivity(Intent(this, Login::class.java))
                    finish()
                } else {
                    Toast.makeText(
                        this,
                        "Registration failed: ${task.exception?.message}",
                        Toast.LENGTH_SHORT
                    ).show()

                }



                }

            }



    private fun Userauth(name: String, email: String, pass: String, number: String) {
        val database = FirebaseDatabase.getInstance()
        val userRef = database.getReference("values")
        val userInfo = User(name,email,pass,number)

        userRef.child(name).setValue(userInfo)

    }
}