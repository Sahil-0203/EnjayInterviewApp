package credentials

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.enjayinterviewapp.HomeActivity
import com.example.enjayinterviewapp.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class Update_Firebase : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var database:DatabaseReference

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_firebase)

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

    private fun updateData(nameText: String, contactText: String, emailText: String, dobText: String) {
        val uid= auth.currentUser?.uid

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
                Toast.makeText(applicationContext,"Failed",Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun readData(user: String) {

        database= FirebaseDatabase.getInstance().getReference("Users")

        database.child(user).get().addOnSuccessListener {

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
            Toast.makeText(this,"Failed", Toast.LENGTH_SHORT).show()
        }

    }
}