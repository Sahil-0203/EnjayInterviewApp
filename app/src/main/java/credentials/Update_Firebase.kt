package credentials

import InternetConnection.NetworkChangedListener
import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import com.example.enjayinterviewapp.HomeActivity
import com.example.enjayinterviewapp.R
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import java.text.SimpleDateFormat
import java.util.*

class Update_Firebase : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var database:DatabaseReference

    var networkChangedListener=NetworkChangedListener()
    @SuppressLint("MissingInflatedId", "SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_firebase)


        // Date Picker--------------------------------------------------------------------------------------
      val DateEdittxt=findViewById<EditText>(R.id.dobText)
        DateEdittxt.setOnFocusChangeListener { _, focused ->

            if (focused)
            {
                DatePickerDialog(this,datePicker,myCalendar.get(Calendar.YEAR),myCalendar.get(
                    Calendar.MONTH),
                    myCalendar.get(Calendar.DAY_OF_MONTH)).show()
            }
        }

// end----------------------------------------------------------------------------------------------


        auth=FirebaseAuth.getInstance()
        var user=auth.currentUser?.uid

        if (user != null){
            readData(user)

        }

        val btn=findViewById<Button>(R.id.updateFirebase)
        btn.setOnClickListener {

            val nameText=findViewById<EditText>(R.id.nameText).text.toString()
            val contactText=findViewById<EditText>(R.id.contactText).text.toString()
            val emailText=findViewById<EditText>(R.id.emailText).text.toString()
            val dobText=findViewById<EditText>(R.id.dobText).text.toString()


            updateData(nameText,contactText,emailText,dobText)

        }


    }


// date picker--------------------------------------------------------------------------------------


    val myCalendar=Calendar.getInstance()

    val datePicker=DatePickerDialog.OnDateSetListener{view, year, month, dayOfMonth ->
        myCalendar.set(Calendar.YEAR,year)
        myCalendar.set(Calendar.MONTH,month)
        myCalendar.set(Calendar.DAY_OF_MONTH,dayOfMonth)
        updateLable(myCalendar)
    }


    private fun updateLable(myCalendar: Calendar) {

        val myFormat="dd-MM-yyyy"
        val sdf= SimpleDateFormat(myFormat, Locale.UK)
        val DateEdittxt=findViewById<EditText>(R.id.dobText)
        DateEdittxt.setText(sdf.format(myCalendar.time))
    }

    private fun updateData(nameText: String, contactText: String, emailText: String, dobText: String) {
        val uid= auth.currentUser?.uid

        val parentLayout=findViewById<View>(android.R.id.content)
        database=FirebaseDatabase.getInstance().getReference("Users")
        val user= mapOf<String,String>(
            "fname" to nameText,
            "mobile" to contactText,
            "email" to emailText,
            "date" to dobText
        )
        if (uid != null) {
            database.child(uid).updateChildren(user).addOnSuccessListener {
                startActivity(Intent(applicationContext, HomeActivity::class.java))
                finish()
            }.addOnFailureListener {
                Snackbar.make(parentLayout,"Server Down !!",Snackbar.LENGTH_SHORT).show()

//                Toast.makeText(applicationContext,"Failed",Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun readData(user: String) {
        val parentLayout=findViewById<View>(android.R.id.content)
        database= FirebaseDatabase.getInstance().getReference("Users")

        val progressBar=findViewById<ProgressBar>(R.id.progressbar)

        progressBar.visibility=View.VISIBLE

        database.child(user).get().addOnSuccessListener {

            progressBar.visibility=View.GONE
            Log.e( "readData: ", it.toString())
            val firstname=it.child("fname").value
            val Phone=it.child("mobile").value
            val Emailid=it.child("email").value
            val Dob=it.child("date").value



            val nameText=findViewById<TextView>(R.id.nameText)
            val contactText=findViewById<TextView>(R.id.contactText)
            val emailText=findViewById<TextView>(R.id.emailText)
            val dobText=findViewById<TextView>(R.id.dobText)

            nameText?.text=firstname.toString()
            contactText?.text=Phone.toString()
            emailText?.text=Emailid.toString()
            dobText?.text=Dob.toString()


        }.addOnFailureListener {
            progressBar.visibility=View.GONE
            Snackbar.make(parentLayout,"Server Down !!",Snackbar.LENGTH_SHORT).show()

//            Toast.makeText(this,"Failed", Toast.LENGTH_SHORT).show()
        }
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