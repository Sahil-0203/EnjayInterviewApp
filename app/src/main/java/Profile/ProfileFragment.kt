package Profile

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.enjayinterviewapp.R
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import credentials.Update_Firebase
import credentials.login

class ProfileFragment : Fragment() {
    private lateinit var auth: FirebaseAuth
     lateinit var database: DatabaseReference
    private lateinit var builder:AlertDialog.Builder


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    @SuppressLint("MissingInflatedId", "ResourceType")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {

        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.fragment_profile, container, false)

        val update=view?.findViewById<Button>(R.id.update)
        update?.setOnClickListener {
            startActivity(Intent(activity, Update_Firebase::class.java))

        }


        builder=AlertDialog.Builder(requireContext())
        val Deactivate=view?.findViewById<Button>(R.id.DeActivateAccount)

//        ------------------------------------------------------------------------------------------


//        ------------------------------------------------------------------------------------------

        Deactivate?.setOnClickListener {

            builder.setTitle("Interview App")
                .setMessage("Are you sure you want to Delete Your Account?")
                .setCancelable(false)
                .setPositiveButton("Yes"){dialogInterface,it ->

                    FirebaseDatabase
                        .getInstance()
                        .getReference()
                        .child("Users")
                        .child(FirebaseAuth.getInstance().currentUser!!.uid)
                        .setValue(null)
                        .addOnSuccessListener {
                            FirebaseAuth.getInstance().currentUser!!.delete()
                                .addOnCompleteListener {task ->
                                    if (task.isSuccessful){
                                        deleteData()
                                        startActivity(Intent(activity, login::class.java))
                                    }
                                }.addOnFailureListener {
                                    Toast.makeText(activity,"Failed to Deactivate",Toast.LENGTH_SHORT).show()
                                }
                        }

                }
                .setNegativeButton("No"){dialogInterface,it ->
                    dialogInterface.cancel()
                }
            val alertDialog = builder.create()
            // Show the Alert Dialog box
            alertDialog.show()

        }


        auth=FirebaseAuth.getInstance()
        var user=auth.currentUser?.uid

        if (user != null){
            readData(user)

        }


        return view
    }

    private fun deleteData() {
        var user=auth.currentUser?.uid
        database =FirebaseDatabase.getInstance().getReference("Users")
        if (user != null) {
            database.child(user).removeValue()
        }
    }

    @SuppressLint("SuspiciousIndentation")
    private fun readData(user: String?): View? {

        val parentLayout=view?.findViewById<View>(android.R.id.content)


        database=FirebaseDatabase.getInstance().getReference("Users")

            database.child(user.toString()).get().addOnSuccessListener {

                val progressbar=view?.findViewById<ProgressBar>(R.id.progressbar)


                progressbar?.visibility=View.VISIBLE

                Log.e( "readData: ", it.toString())
                val firstname=it.child("fname").value
                val Phone=it.child("mobile").value
                val Emailid=it.child("email").value
                val Dob=it.child("date").value


                val nameTextView=view?.findViewById<TextView>(R.id.nameTextview)
                val contactTextview=view?.findViewById<TextView>(R.id.contactTextview)
                val emailTextview=view?.findViewById<TextView>(R.id.emailTextview)
                val dobTextview=view?.findViewById<TextView>(R.id.dobTextview)

                nameTextView?.text=firstname.toString()
                contactTextview?.text=Phone.toString()
                emailTextview?.text=Emailid.toString()
                dobTextview?.text=Dob.toString()
                if (it.exists()){

                    progressbar?.visibility=View.GONE
                }else {
                    Toast.makeText(activity,"User Doesn't Exists",Toast.LENGTH_SHORT).show()
                }
            }.addOnFailureListener {
                val prog=view?.findViewById<ProgressBar>(R.id.progressbar)
                prog?.visibility=View.GONE
                try {
                    Toast.makeText(activity,"Server Down",Toast.LENGTH_SHORT).show()
                }
                catch (e:Exception)
                {

                    Log.e("profileeee", "readData.>>>>: $e" )
                }


            }

        return view
    }

}