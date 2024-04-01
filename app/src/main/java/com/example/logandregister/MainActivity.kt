package com.example.logandregister

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class MainActivity : AppCompatActivity() {

    lateinit var auth:FirebaseAuth
    lateinit var user:FirebaseUser
    lateinit var toggle: ActionBarDrawerToggle
    lateinit var drawerLayout: DrawerLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContentView(R.layout.activity_main)





        // Replace ic_menu with your icon


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        //val useremail = findViewById<TextView>(R.id.Useemail)
        //val logout = findViewById<TextView>(R.id.logout)
        auth=FirebaseAuth.getInstance()
       // val userEmail=intent.getStringExtra("email")
        user= auth.currentUser!!
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        supportActionBar?.show()
        supportActionBar?.setDisplayOptions(ActionBar.DISPLAY_SHOW_TITLE)


        // val user_name = findViewById<TextView>(R.id.user_name)
       // val user_email = findViewById<TextView>(R.id.user_email)

       // val userEmail=intent.getStringExtra("email")
        //val userName = intent.getStringExtra("name")

        drawerLayout= findViewById(R.id.main)
        val navView : NavigationView = findViewById(R.id.nav_view)

        toggle = ActionBarDrawerToggle(this,drawerLayout,R.string.open, R.string.close)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()


        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        navView.setNavigationItemSelectedListener {
            it.isChecked=true

            when(it.itemId){
                R.id.nav_home-> replacefragment(homeFrag(),it.title.toString())
                    R.id.nav_call-> replacefragment(callfrag(),it.title.toString())
                R.id.nav_delete-> Toast.makeText(this,"Clicked Delete", Toast.LENGTH_SHORT).show()
                R.id.nav_logout-> Toast.makeText(this,"Clicked Logout", Toast.LENGTH_SHORT).show()
                R.id.nav_msg-> replacefragment(msgfrag(),it.title.toString())
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



    }

    private fun replacefragment(fragment: Fragment, titile:String){
        val fragmentmagneger= supportFragmentManager
        val fragmentTransaction = fragmentmagneger.beginTransaction()
        fragmentTransaction.replace(R.id.fragment,fragment)
        fragmentTransaction.commit()
        drawerLayout.close()
        setTitle(titile)

    }



    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(toggle.onOptionsItemSelected(item)){
            return true
        }

        return super.onOptionsItemSelected(item)
    }
}