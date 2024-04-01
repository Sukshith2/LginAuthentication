package com.example.logandregister

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class MainActivity : AppCompatActivity() {

    lateinit var auth:FirebaseAuth
    lateinit var user:FirebaseUser
    lateinit var toggle: ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
         // Replace ic_menu with your icon


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        //val useremail = findViewById<TextView>(R.id.Useemail)
        //val logout = findViewById<TextView>(R.id.logout)
        auth=FirebaseAuth.getInstance()
        val userEmail=intent.getStringExtra("email")
        user= auth.currentUser!!
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)


        val drawerLayout : DrawerLayout = findViewById(R.id.main)
        val navView : NavigationView = findViewById(R.id.nav_view)

        toggle = ActionBarDrawerToggle(this,drawerLayout,R.string.open, R.string.close)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()


        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        navView.setNavigationItemSelectedListener {
            when(it.itemId){
                R.id.nav_home-> Toast.makeText(this,"Clicked Home", Toast.LENGTH_SHORT).show()
                R.id.nav_call-> Toast.makeText(this,"Clicked call", Toast.LENGTH_SHORT).show()
                R.id.nav_delete-> Toast.makeText(this,"Clicked Delete", Toast.LENGTH_SHORT).show()
                R.id.nav_logout-> Toast.makeText(this,"Clicked Logout", Toast.LENGTH_SHORT).show()
                R.id.nav_msg-> Toast.makeText(this,"Clicked Message", Toast.LENGTH_SHORT).show()

            }
            true
        }



//        logout.setOnClickListener {
//            val intent = Intent(this,Register::class.java)
//            Toast.makeText(this, "Logout User Succesfully", Toast.LENGTH_SHORT).show()
//            startActivity(intent)
//            finish()
//
//        }

        if(user==null){
            val intent =Intent(this, Login::class.java)
            startActivity(intent)
            finish()
        }else{
            //useremail.text=userEmail
        }

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(toggle.onOptionsItemSelected(item)){
            return true
        }

        return super.onOptionsItemSelected(item)
    }
}