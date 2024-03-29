package credentials

import InternetConnection.NetworkChangedListener
import android.app.DatePickerDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.enjayinterviewapp.R
import ModelClass.User
import android.content.IntentFilter
import android.net.ConnectivityManager
import com.example.enjayinterviewapp.databinding.ActivityRegisterPageBinding
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import java.text.SimpleDateFormat
import java.util.*

class Register_page : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var database: DatabaseReference

    private lateinit var binding: ActivityRegisterPageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityRegisterPageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialize Firebase Auth-----------------------------------------------------------------

        auth = FirebaseAuth.getInstance()


        binding.Login.setOnClickListener {
            intent= Intent(applicationContext, login::class.java)
            startActivity(intent)
            finish()
        }

// Date Picker--------------------------------------------------------------------------------------
        binding.DateEdittxt.setOnFocusChangeListener { _, focused ->

            if (focused)
            {
                DatePickerDialog(this,datePicker,myCalendar.get(Calendar.YEAR),myCalendar.get(
                    Calendar.MONTH),
                    myCalendar.get(Calendar.DAY_OF_MONTH)).show()
            }
        }

// end----------------------------------------------------------------------------------------------
        val fname=findViewById<TextInputEditText>(R.id.fnameEdittxt)



        binding.Registerbtn.setOnClickListener  {

            submitForm()
            val e=fname.text.toString()
            if (e.isEmpty())
            {
                fname.requestFocus()

                val imm: InputMethodManager =
                    getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.showSoftInput(fname, InputMethodManager.SHOW_IMPLICIT)
            }

        }



// validation start --------------------------------------------------------------------------------
        FnameFocusListner()
        MobileFocusListener()
        EmailFocusListener()
        passFocusListener()
        CpassFocusListner()


    }

    private fun CpassFocusListner() {
        binding.CPassEdittxt.setOnFocusChangeListener { _, focused ->
            if (!focused)
            {
                binding.CPassContainer.helperText=validCpass()
            }
        }
    }

    private fun validCpass(): String? {

        val cpassText=binding.CPassEdittxt.text.toString()
        val passText=binding.PassEdittxt.text.toString()
        if (cpassText.isEmpty())
        {
            return "Enter Password*"
        }
        if (cpassText !=passText ) {
            return "Password Didn't match*"
        }

        return null

    }

    private fun submitForm() {

        binding.fnameContainer.helperText=validFname()
        binding.MobileContainer.helperText=validMobile()
        binding.EmailContainer.helperText=validEmail()
        binding.PassContainer.helperText=validpass()
        binding.CPassContainer.helperText=validCpass()

        val validname=binding.fnameContainer.helperText==null
        val validmobile=binding.MobileContainer.helperText==null
        val validemail=binding.EmailContainer.helperText==null
        val validpass=binding.PassContainer.helperText==null
        val validCpass=binding.CPassContainer.helperText==null

        if (validname && validmobile && validemail && validpass && validCpass)
        {
//            firebase value stored code------------------------------------------------------------
            val FName=binding.fnameEdittxt.text.toString().trim()
            val Mobile=binding.MobileEdittxt.text.toString().trim()
            val Email=binding.EMailEdittxt.text.toString().trim()
            val Date=binding.DateEdittxt.text.toString().trim()
            val Password=binding.PassEdittxt.text.toString().trim()

            auth.createUserWithEmailAndPassword(Email,Password).addOnCompleteListener{
                if (it.isSuccessful){
                    val uid= auth.currentUser?.uid

                    database= FirebaseDatabase.getInstance().getReference("Users")
                    val User= User(FName,Mobile,Email,Date)
                    if (uid != null) {
                        database.child(uid).setValue(User).addOnSuccessListener {
                            intent= Intent(applicationContext, login::class.java)
                            startActivity(intent)
                            Toast.makeText(this,"SuccessFully Saved", Toast.LENGTH_SHORT).show()
                        }.addOnFailureListener{
                            Toast.makeText(this,"Failed", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
                else
                {
                    Toast.makeText(this,"Email Already Exists!!",Toast.LENGTH_SHORT).show()
                }
            }




        }

        else{
            invalidForm()

        }
    }

    private fun invalidForm() {
        var message =" "
        if(binding.fnameContainer.helperText!=null)
            message +="\n\nname: " +binding.fnameContainer.helperText
        if (binding.MobileContainer.helperText!=null)
            message +="\n\nMobile: " +binding.MobileContainer.helperText
        if (binding.EmailContainer.helperText!=null)
            message +="\n\nEmail: " +binding.EmailContainer.helperText
        if (binding.PassContainer.helperText!=null)
            message +="\n\nPass: " +binding.PassContainer.helperText

    }

    // Pass validation----------------------------------------------------------------------------------
    private fun passFocusListener() {

        binding.PassEdittxt.setOnFocusChangeListener { _, focused ->
            if (!focused)
            {
                binding.PassContainer.helperText=validpass()
            }
        }

    }

    private fun validpass(): String? {

        val passText=binding.PassEdittxt.text.toString()
        if (passText.isEmpty())
        {
            return "Enter Password*"
        }
        if (passText.length<8)
        {
            return "Minimum 8 character required*"
        }
        if (!passText.matches(".*[A-Z].*".toRegex()))
        {
            return "Must Contain 1 Upper-Case Character*"
        }
        if (!passText.matches(".*[a-z].*".toRegex()))
        {
            return "Must Contain 1 Lower-Case Character*"
        }
        if (!passText.matches(".*[@#\$%^&+=].*".toRegex()))
        {
            return "Must Contain 1 Special Character (@#\$%^&+=)"
        }

        return null
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
        binding.DateEdittxt.setText(sdf.format(myCalendar.time))
    }


    // Email validation-----------------------------------------------------------------------------
    private fun EmailFocusListener() {

        binding.EMailEdittxt.setOnFocusChangeListener { _, focused ->
            if (!focused)
            {
                binding.EmailContainer.helperText=validEmail()
            }

        }

    }

    private fun validEmail(): String? {

        val EmailText=binding.EMailEdittxt.text.toString()

        if (EmailText.isEmpty())
        {
            return "Enter Email ID*"
        }
        if (!EmailText.matches("[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+".toRegex()))
        {
            return "Enter valid Email ID*"
        }
        if (EmailText.length<7){
            return "Enter Valid Email*"
        }
        return null
    }

    //Mobile validation-----------------------------------------------------------------------------
    private fun MobileFocusListener() {

        binding.MobileEdittxt.setOnFocusChangeListener { _, focused ->

            if (!focused)
            {
                binding.MobileContainer.helperText=validMobile()
            }
        }

    }

    private fun validMobile(): String? {
        val MobileText=binding.MobileEdittxt.text.toString()

        if (MobileText.isEmpty()){
            return "Enter Mobile Number*"
        }
        if (MobileText.length<10){
            return "Enter Valid Number*"
        }

        return null

    }

    //    Fullname validation-----------------------------------------------------------------------
    private fun FnameFocusListner() {

        binding.fnameEdittxt.setOnFocusChangeListener{_,focused ->
            if (!focused)
            {
                binding.fnameContainer.helperText=validFname()
            }
        }
    }

    private fun validFname():String?
    {
        val Fnametext=binding.fnameEdittxt.text.toString()
        if (Fnametext.isEmpty()){
            return "Fullname can't be empty*"
        }
        if (Fnametext.length<8)
        {
            return "Enter valid Fullname*"
        }
        return null
    }
}