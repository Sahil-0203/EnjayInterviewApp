package com.example.enjayinterviewapp

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class ProfileFragment : Fragment() {
    private lateinit var auth: FirebaseAuth
     lateinit var database: DatabaseReference


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.fragment_profile, container, false)

        val update=view?.findViewById<Button>(R.id.update)
        update?.setOnClickListener {
            startActivity(Intent(activity,Update_Firebase::class.java))
        }



        auth=FirebaseAuth.getInstance()
        var user=auth.currentUser?.uid

        if (user != null){
            readData(user)

        }


        return view
    }

    @SuppressLint("SuspiciousIndentation")
    private fun readData(user: String?): View? {

        database=FirebaseDatabase.getInstance().getReference("Users")

            database.child(user.toString()).get().addOnSuccessListener {

                Log.e( "readData: ", it.toString())
                val firstname=it.child("fname").value
                val Phone=it.child("mobile").value
                val Emailid=it.child("email").value
                val Dob=it.child("date").value

                Toast.makeText(activity,"SuccessFul",Toast.LENGTH_SHORT).show()

                val nameTextView=view?.findViewById<TextView>(R.id.nameTextview)
                val contactTextview=view?.findViewById<TextView>(R.id.contactTextview)
                val emailTextview=view?.findViewById<TextView>(R.id.emailTextview)
                val dobTextview=view?.findViewById<TextView>(R.id.dobTextview)

                nameTextView?.text=firstname.toString()
                contactTextview?.text=Phone.toString()
                emailTextview?.text=Emailid.toString()
                dobTextview?.text=Dob.toString()
                if (it.exists()){



                }else {
                    Toast.makeText(activity,"User Doesn't Exists",Toast.LENGTH_SHORT).show()
                }
            }.addOnFailureListener {
                Toast.makeText(activity,"Failed",Toast.LENGTH_SHORT).show()
            }



        return view
    }

}