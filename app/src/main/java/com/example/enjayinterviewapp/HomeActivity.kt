package com.example.enjayinterviewapp

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.enjayinterviewapp.databinding.ActivityHomeBinding
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

private val homeFragment=HomeFragment()


class HomeActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var binding: ActivityHomeBinding
    private lateinit var toggle: ActionBarDrawerToggle
    private lateinit var auth: FirebaseAuth
    lateinit var database: DatabaseReference
    lateinit var drawerLayout: DrawerLayout
    private lateinit var builder:AlertDialog.Builder
    private lateinit var user: FirebaseUser
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
//-----------------------value retrieve from firebase-----------------------------------------------


      
        auth=FirebaseAuth.getInstance()
        var user=auth.currentUser?.uid

        if (user != null){
            readData(user)

        }

//--------------------------------------------------------------------------------------------------

        builder=AlertDialog.Builder(this)
        drawerLayout=findViewById(R.id.mainDrawer)
        val toolbar=findViewById<Toolbar>(R.id.toolbar)
        val navigationView=findViewById<NavigationView>(R.id.navigationView)


        setSupportActionBar(toolbar)


        toggle = ActionBarDrawerToggle(this, drawerLayout, R.string.Open, R.string.Close)
        drawerLayout.addDrawerListener(toggle)
        toggle.isDrawerIndicatorEnabled=true

        toggle.syncState()


        navigationView.setNavigationItemSelectedListener(this)

        supportActionBar!!.setHomeButtonEnabled(true)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayUseLogoEnabled(true)

        replaceFragment(homeFragment)

        drawerLayout.addDrawerListener(toggle)





        binding.bottomNavigation.setOnItemSelectedListener{ item ->

            when (item.itemId)
            {
                R.id.home -> replaceFragment(HomeFragment())
                R.id.quiz -> replaceFragment(QuizFragment())
                R.id.profile -> replaceFragment(ProfileFragment())
            }
            true
        }
    }

    private fun readData(user: String) {
        database= FirebaseDatabase.getInstance().getReference("Users")

        database.child(user).get().addOnSuccessListener {

            Log.e( "readData: ", it.toString())
            val firstname=it.child("fname").value
            val Phone=it.child("mobile").value


            Toast.makeText(this,"SuccessFul",Toast.LENGTH_SHORT).show()

            val nameTextView=findViewById<TextView>(R.id.name)
            val contactTextview=findViewById<TextView>(R.id.contact)


            nameTextView?.text=firstname.toString()
            contactTextview?.text=Phone.toString()

            if (it.exists()){



            }else {
                Toast.makeText(this,"User Doesn't Exists",Toast.LENGTH_SHORT).show()
            }
        }.addOnFailureListener {
            Toast.makeText(this,"Failed",Toast.LENGTH_SHORT).show()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean
    {
        if (toggle.onOptionsItemSelected(item))
        {
            return true
        }
        return super.onOptionsItemSelected(item)
    }


    private fun replaceFragment(fragment: Fragment)
    {
        val fragmentManager: FragmentManager = supportFragmentManager
        val fragmentTransition: FragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransition.replace(R.id.framelayout, fragment)
        fragmentTransition.commit()

    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId)
        {
            R.id.applynow ->
            {

                val uri=Uri.parse("https://www.enjayworld.com/contact/?")
                val apply=Intent(Intent.ACTION_VIEW,uri)
                startActivity(apply)
                Toast.makeText(this, "Redirecting...", Toast.LENGTH_SHORT).show()
            }
            R.id.contactus ->
            {
                val call=Intent(Intent.ACTION_DIAL)
                call.setData(Uri.parse("tel:+916353413260"))
                startActivity(call)
            }
            R.id.aboutus ->
            {
                startActivity(Intent(this,AboutUS::class.java))
                finish()
            }
            R.id.rateus ->
            {
                Toast.makeText(this, "Rateus clicked", Toast.LENGTH_SHORT).show()
            }
            R.id.share ->
            {
                val shareBody="Download nextQuiz on Play Store:"
                val sharehub="nextQuiz : make brain powerful"
                val shareIntent=Intent(Intent.ACTION_SEND)
                shareIntent.type="text/plain"
                shareIntent.putExtra(Intent.EXTRA_SUBJECT,sharehub)
                shareIntent.putExtra(Intent.EXTRA_TEXT,shareBody)
                startActivity(shareIntent)
            }
            R.id.logout ->

            {
                builder.setTitle("Interview App")
                    .setMessage("Do You Want To Logout ?")
                    .setCancelable(false)
                    .setPositiveButton("Yes"){dialogInterface,it ->
                        auth.signOut()
                        intent= Intent(applicationContext,login::class.java)
                        startActivity(intent)
                        finish()
                    }
                    .setNegativeButton("No"){dialogInterface,it ->
                        dialogInterface.cancel()
                    }
                val alertDialog = builder.create()
                // Show the Alert Dialog box
                alertDialog.show()

            }
        }
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    override fun onBackPressed() {


        builder.setTitle("Interview App")
            .setMessage("Do You Want To Exit ? ")
            .setCancelable(false)
            .setPositiveButton("Yes"){dialogInterface,it ->
                finish()
            }
            .setNegativeButton("No"){dialogInterface,it ->
                dialogInterface.cancel()
            }
        val alertDialog = builder.create()
        // Show the Alert Dialog box
        alertDialog.show()
    }
}

