package com.example.enjayinterviewapp

import android.annotation.SuppressLint
import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatDelegate
import com.example.enjayinterviewapp.databinding.ActivityLoginBinding
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.tasks.Task
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.database.DatabaseReference
import com.google.firebase.ktx.Firebase
import java.util.*

class login : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var database:DatabaseReference
    private lateinit var auth: FirebaseAuth
    private lateinit var googleSignInClient:GoogleSignInClient
    var callbackManager: CallbackManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
//        night mode--------------------------------------------------------------------------------

                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

//        ------------------------------------------------------------------------------------------
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        callbackManager = CallbackManager.Factory.create()

//        ------------------------------------------------------------------------------------------

        LoginManager.getInstance().registerCallback(callbackManager,
            object : FacebookCallback<LoginResult> {
                override fun onSuccess(loginResult: LoginResult) {
                    //app code
                    startActivity(Intent(applicationContext,HomeActivity::class.java))
                    finish()
                }

                override fun onCancel() {
//                      app code
                }

                override fun onError(e: FacebookException) {}
            })
//        binding.facebookimg.setOnClickListener(View.OnClickListener {
//            //                login to facebook--------------------------
//            LoginManager.getInstance()
//                .logInWithReadPermissions(this@login, Arrays.asList("public_Prfile"))
//        })


//        ------------------------------------------------------------------------------------------
        userFocusListner()
        passwordFocusListner()
        auth = FirebaseAuth.getInstance()


//        login with Facebook-----------------------------------------------------------------------




//        login with google-------------------------------------------------------------------------

//        val gso= GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
//            .requestIdToken(getString(R.string.client_ID))
//            .requestEmail()
//            .build()
//
//        googleSignInClient= GoogleSignIn.getClient(this,gso)
//
//        binding.googleimg.setOnClickListener {
//            signInGoogle()
//        }



        binding.txt2.setOnClickListener {
            intent = Intent(applicationContext, Register_page::class.java)
            startActivity(intent)
        }

        binding.logMain.setOnClickListener {


            val fName=findViewById<TextInputEditText>(R.id.UserEditText)

            val e=fName.text.toString()
            if (e.isEmpty()){
                fName.requestFocus()
                val abc: InputMethodManager =
                    getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                abc.showSoftInput(fName, InputMethodManager.SHOW_IMPLICIT)
            }

            submitForm()
        }
//  forget password --------------------------------------------------------------------------------
        binding.forgetPass.setOnClickListener {
            val builder: AlertDialog.Builder= AlertDialog.Builder(this)
            builder.setTitle("Forgot Password")
            val view =layoutInflater.inflate(R.layout.dialog_forgot_password,null)
            val username: EditText =view.findViewById(R.id.Resetemail)
            builder.setView(view)
            builder.setPositiveButton("Reset", DialogInterface.OnClickListener{ _, _ ->

                forgotPassword(username)
            })
            builder.setNegativeButton("Close", DialogInterface.OnClickListener{ _, _ ->})
            builder.show()
        }

//finish--------------------------------------------------------------------------------------------
    }
    private fun forgotPassword(username:EditText) {
        if (username.text.toString().isEmpty()){
            username.error="Please Enter Email ID"
            username.requestFocus()
            return
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(username.text.toString()).matches()){
            return
        }
        auth.sendPasswordResetEmail(username.text.toString())
            .addOnCompleteListener { task ->
                if (task.isSuccessful){
                    Toast.makeText(this,"Email Sent", Toast.LENGTH_SHORT).show()
                }
            }
    }

    //signin google-------------------------------------------------------------------------------------
//    private fun signInGoogle() {
//        val signInIntent=googleSignInClient.signInIntent
//        launcher.launch(signInIntent)
//
//    }
//    @SuppressLint("SuspiciousIndentation")
//    private val launcher=registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
//            result ->
//        if (result.resultCode== Activity.RESULT_OK)
//        {
//            val  task=GoogleSignIn.getSignedInAccountFromIntent(result.data)
//            handleResults(task)
//        }
//    }
//
//    private fun handleResults(task: Task<GoogleSignInAccount>) {
//
//        if (task.isSuccessful){
//
//            val account: GoogleSignInAccount?=task.result
//            if (account!=null){
//                updateUI(account)
//            }
//        }
//        else{
//            Toast.makeText(this,task.exception.toString(), Toast.LENGTH_SHORT).show()
//        }
//    }

//    private fun updateUI(account: GoogleSignInAccount) {
//
//        val credential= GoogleAuthProvider.getCredential(account.idToken,null)
//        auth.signInWithCredential(credential).addOnCompleteListener{
//            if (it.isSuccessful){
//                Toast.makeText(this,"Login Successful", Toast.LENGTH_SHORT).show()
//                intent=Intent(applicationContext,HomeActivity::class.java)
//                startActivity(intent)
//            }else
//            {
//                Toast.makeText(this,it.exception.toString(), Toast.LENGTH_SHORT).show()
//            }
//        }
//    }


    //--------------------------------------------------------------------------------------------------
    private fun submitForm() {

        binding.UserContainer.helperText=validUser()
        binding.passwordContainer.helperText=validpass()
        val validname=binding.UserContainer.helperText==null
        val validpassword=binding.passwordContainer.helperText==null
        if (validname && validpassword){
//            firebase realtime database check value------------------------------------------------

            val LoginEmail=findViewById<TextInputEditText>(R.id.UserEditText)
            val Loginpassword=findViewById<TextInputEditText>(R.id.passwordEdittxt)

            val email=LoginEmail.text.toString()
            val password=Loginpassword.text.toString()


            auth.signInWithEmailAndPassword(email,password).addOnCompleteListener{
                if (it.isSuccessful){
                    intent= Intent(applicationContext,HomeActivity::class.java)
                    startActivity(intent)
                }else {
                    Toast.makeText(this,"Wrong Password or Email ID", Toast.LENGTH_SHORT).show()
                }
            }






        }
        else
        {
            invalidform()
        }
    }

    private fun invalidform() {
        var message =" "
        if(binding.UserContainer.helperText!=null)
            message +="\n\nname: " +binding.UserContainer.helperText
        if (binding.passwordContainer.helperText!=null)
            message +="\n\nMobile: " +binding.passwordContainer.helperText
    }

    //    Password validation---------------------------------------------------------------------------
    private fun passwordFocusListner() {

        binding.passwordEdittxt.setOnFocusChangeListener { _, focused ->
            if (!focused)
            {
                binding.passwordContainer.helperText=validpass()
            }
        }
    }

    private fun validpass(): String? {
        val Password=binding.passwordEdittxt.text.toString()
        if (Password.isEmpty()){
            return "Invalid Password"
        }
        return null
    }

    //User validation-----------------------------------------------------------------------------------
    private fun userFocusListner() {
        binding.UserEditText.setOnFocusChangeListener { _, focused ->
            if (!focused) {
                binding.UserContainer.helperText = validUser()
            }
        }
    }

    private fun validUser(): String? {
        val Email = binding.UserEditText.text.toString()

        if (!Patterns.EMAIL_ADDRESS.matcher(Email).matches()) {
            return "Invalid Email Id*"
        }
        return null
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        callbackManager!!.onActivityResult(requestCode, resultCode, data)
        super.onActivityResult(requestCode, resultCode, data)
    }

}