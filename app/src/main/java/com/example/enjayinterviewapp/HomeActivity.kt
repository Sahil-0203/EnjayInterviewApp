package com.example.enjayinterviewapp

import AboutUS.AboutUS
import Home.HomeFragment
import Home.SQLiteHelper
import InternetConnection.NetworkChangedListener
import Profile.ProfileFragment
import Quiz.QuizFragment
import android.content.ContentValues
import android.content.DialogInterface
import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import com.example.enjayinterviewapp.databinding.ActivityHomeBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import credentials.login



class HomeActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var binding: ActivityHomeBinding
    private lateinit var toggle: ActionBarDrawerToggle
    private lateinit var auth: FirebaseAuth
    private lateinit var database: DatabaseReference
    private lateinit var database2: DatabaseReference
    private lateinit var database3: DatabaseReference
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var builder:AlertDialog.Builder
    private lateinit var user: FirebaseUser

    var networkChangedListener=NetworkChangedListener()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

              AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)



        replaceFragment(HomeFragment())
//  ------------------------------question get from api to sqlite-----------------------------------

        getQuestion()

//        -------------------------------finish get question----------------------------------------

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


        drawerLayout.addDrawerListener(toggle)


        binding.bottomNavigation.setOnItemSelectedListener { item ->

            when (item.itemId)
            {
                R.id.home -> replaceFragment(HomeFragment())
                R.id.quiz -> replaceFragment(QuizFragment())
                R.id.profile -> replaceFragment(ProfileFragment())
            }
            true
        }

    }


    private fun replaceFragment(fragment: Fragment) {

        val FragmentManager=supportFragmentManager
        val FragmentTransaction=FragmentManager.beginTransaction()
        FragmentTransaction.replace(R.id.framelayout,fragment)
        FragmentTransaction.commit()
    }

    private fun getQuestion() {

        database= FirebaseDatabase.getInstance().getReference("AptitudeQuestion")
        database2=FirebaseDatabase.getInstance().getReference("LogicalQuestion")
        database3=FirebaseDatabase.getInstance().getReference("CommonQuestion")

        database.get().addOnSuccessListener {
            for (DataSnap in it.children ){


                val questionReal=DataSnap.child("question").value.toString()
                val option1Real=DataSnap.child("option1").value.toString()
                val option2Real=DataSnap.child("option2").value.toString()
                val option3Real=DataSnap.child("option3").value.toString()
                val option4Real=DataSnap.child("option4").value.toString()
                val answerReal=DataSnap.child("answer").value.toString().toInt()

//                Log.e("asd", "getQuestion: ${answerReal}", )


//      store value in sqlite-----------------------------------------------------------------------

                val helper=SQLiteHelper(applicationContext)
                val db=helper.readableDatabase
                var cursor=db.rawQuery("SELECT * FROM tblQuestion WHERE question =?", arrayOf(questionReal.toString()))
                val exists=cursor.moveToNext()
                cursor.close()

                if (exists){
//----------------------------value existing-----------------------
                }else {
                    var cv = ContentValues()
                    cv.put("question", questionReal)
                    cv.put("option1", option1Real)
                    cv.put("option2", option2Real)
                    cv.put("option3", option3Real)
                    cv.put("option4", option4Real)
                    cv.put("answer",answerReal)
                    db.insert("tblQuestion", null, cv)

                }
//  -----------------------finish  sqlite store-----------------------------------------------------

            }
            }
//        ----------------------- Start logical Question---------------------------------------------------
        database2.get().addOnSuccessListener {
            for (DataSnap in it.children ){

//                Log.e("shhhhhhhh", "---------->>$it ", )
                val questionReal2=DataSnap.child("question").value.toString()
                val option1Real2=DataSnap.child("option1").value.toString()
                val option2Real2=DataSnap.child("option2").value.toString()
                val option3Real2=DataSnap.child("option3").value.toString()
                val option4Real2=DataSnap.child("option4").value.toString()
                val answerReal2=DataSnap.child("answer").value.toString().toInt()


//                --------------------------logical question---------------------------------------


//      store value in sqlite-----------------------------------------------------------------------

                val helper2=SQLiteHelper(applicationContext)
                val db2=helper2.readableDatabase
//                Log.e("1234", "=====>$db2", )

                var cursor2=db2.rawQuery("SELECT * FROM logQuestion WHERE question =?", arrayOf(questionReal2.toString()))
                val exists2=cursor2.moveToNext()
                cursor2.close()

                if (exists2){
//----------------------------value existing-----------------------
                }else {
                    var cv2 = ContentValues()
                    cv2.put("question", questionReal2)
                    cv2.put("option1", option1Real2)
                    cv2.put("option2", option2Real2)
                    cv2.put("option3", option3Real2)
                    cv2.put("option4", option4Real2)
                    cv2.put("answer",answerReal2)
                    db2.insert("logQuestion", null, cv2)

                }
//  -----------------------finish sqlite store-----------------------------------------------------
            }
        }
//        -----------------------finish logical-----------------------------------------------------

//        ------------------------start commonQuestion----------------------------------------------
        database3.get().addOnSuccessListener {
            for (DataSnap in it.children ){

//                Log.e("shhhhhhhh", "---------->>$it ", )
                val questionReal3=DataSnap.child("question").value.toString()
                val answerReal3=DataSnap.child("answer").value.toString()




//      store value in sqlite-----------------------------------------------------------------------

                val helper3=SQLiteHelper(applicationContext)
                val db3=helper3.readableDatabase
//                Log.e("1234", "=====>$db2", )

                var cursor3=db3.rawQuery("SELECT * FROM cmnQuestion WHERE question =?", arrayOf(questionReal3.toString()))
                val exists3=cursor3.moveToNext()
                cursor3.close()

                if (exists3){
//----------------------------value existing-----------------------
                }else {
                    var cv3 = ContentValues()
                    cv3.put("question", questionReal3)
                    cv3.put("answer",answerReal3)
                    db3.insert("cmnQuestion", null, cv3)

                }
//  -----------------------finish sqlite store-----------------------------------------------------
            }
        }

//        -------------------------end CommonQuestion-----------------------------------------------
    }

    private fun readData(user: String) {
        database= FirebaseDatabase.getInstance().getReference("Users")

        database.child(user).get().addOnSuccessListener {

//            Log.e( "readData: ", it.toString())
            val firstname=it.child("fname").value
            val Phone=it.child("mobile").value

//            Toast.makeText(this,"SuccessFul",Toast.LENGTH_SHORT).show()

            val nameTextView=findViewById<TextView>(R.id.name)
            val contactTextview=findViewById<TextView>(R.id.contact)


            if (it.exists()){

                nameTextView?.text=firstname.toString()
                contactTextview?.text=Phone.toString()

            }else {
                Toast.makeText(this,"User Doesn't Exists",Toast.LENGTH_SHORT).show()
            }
        }.addOnFailureListener {
            Snackbar.make(binding.root,"Server Down",Snackbar.LENGTH_SHORT).show()
//            Toast.makeText(this,"Server Down",Toast.LENGTH_SHORT).show()
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




    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId)
        {
            R.id.applynow ->
            {

                val uri=Uri.parse("https://www.enjayworld.com/careers/")
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
                startActivity(Intent(this, AboutUS::class.java))
                finish()
            }
            R.id.rateus ->
            {
                Toast.makeText(this, "Rateus clicked", Toast.LENGTH_SHORT).show()
            }
            R.id.share ->
            {
                val shareBody="Download EnajyInterviewApp on Play Store:"
                val sharehub="EnjayInterviewApp : make brain powerful"
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
                        intent= Intent(applicationContext, login::class.java)
                        startActivity(intent)
                        finish()
                    }
                    .setNegativeButton("No"){dialogInterface,it ->
                        dialogInterface.cancel()
                    }
                val alertDialog = builder.create()
    //-------------------- Show the Alert Dialog box------------------------------------------------
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
                super@HomeActivity.onBackPressed()
                finish()
            }
            .setNegativeButton("No"){dialogInterface,it ->
                dialogInterface.cancel()
            }
        val alertDialog = builder.create()
        // Show the Alert Dialog box
        alertDialog.show()

    }

    override fun onStart()
    {
        var intentFilter=IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION)
        registerReceiver(networkChangedListener,intentFilter)
        super.onStart()
    }

    override fun onStop()
    {
        unregisterReceiver(networkChangedListener)
        super.onStop()
    }
}

