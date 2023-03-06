package credentials


import ModelClass.User
import android.annotation.SuppressLint
import android.app.AlertDialog
import android.app.DatePickerDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Patterns
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.*
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.content.getSystemService
import com.example.enjayinterviewapp.HomeActivity
import com.example.enjayinterviewapp.R
import com.example.enjayinterviewapp.databinding.ActivityLoginBinding
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import java.text.SimpleDateFormat
import java.util.*

class login : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var database: DatabaseReference
    private lateinit var auth: FirebaseAuth
//    private lateinit var googleSignInClient:GoogleSignInClient
    var callbackManager: CallbackManager? = null

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
//        night mode--------------------------------------------------------------------------------

                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

//        ------------------------------------------------------------------------------------------
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        callbackManager = CallbackManager.Factory.create()



//      ---------------------------login & SignUP Swapping------------------------------------------

        val sign=findViewById<TextView>(R.id.signUp)
        val log=findViewById<TextView>(R.id.login)
        val signlayout=findViewById<RelativeLayout>(R.id.signUpLayout)
        val loginLaout=findViewById<RelativeLayout>(R.id.loginLayout)
        sign.setOnClickListener {
            sign.background=resources.getDrawable(R.drawable.switch_trcks,null)
            sign.setTextColor(resources.getColor(R.color.white,null))
            log.background=null
            signlayout.visibility= View.VISIBLE
            loginLaout.visibility= View.GONE
            log.setTextColor(resources.getColor(R.color.black,null))

        }

        log.setOnClickListener {

            sign.background= null
            sign.setTextColor(resources.getColor(R.color.black,null))
            log.background=resources.getDrawable(R.drawable.switch_trcks,null)
            signlayout.visibility= View.GONE
            loginLaout.visibility= View.VISIBLE
            log.setTextColor(resources.getColor(R.color.white,null))

        }
//      --------------------------------------------------------------------------------------------
//        progressBar.visibility=View.GONE
//        ------------------------------------------------------------------------------------------

//        LoginManager.getInstance().registerCallback(callbackManager,
//            object : FacebookCallback<LoginResult> {
//                override fun onSuccess(loginResult: LoginResult) {
//                    //app code
//                    startActivity(Intent(applicationContext, HomeActivity::class.java))
//                    finish()
//                }
//
//                override fun onCancel() {
//                     app code
//                }
//
//                override fun onError(e: FacebookException) {}
//            })
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





        binding.logMain.setOnClickListener {

            val fName=findViewById<TextInputEditText>(R.id.UserEditText)
            val pass=findViewById<TextInputEditText>(R.id.passwordEdittxt)

            val e=fName.text.toString().trim()
            val p=pass.text.toString().trim()
            if (e.isEmpty()){
                fName.requestFocus()
                val abc: InputMethodManager =
                    getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                abc.showSoftInput(fName, InputMethodManager.SHOW_IMPLICIT)
            }
            else if (p.isEmpty())
            {
                pass.requestFocus()
                val abcd: InputMethodManager=
                    getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                abcd.showSoftInput(pass,InputMethodManager.SHOW_IMPLICIT)
            }
            else
            {
                val builder=AlertDialog.Builder(this@login)

                val progressBar=ProgressBar(this@login)
                progressBar.isIndeterminate=true
                builder.setView(progressBar)


                val dialog=builder.create()
                dialog.show()


                Handler().postDelayed({ // Hide the progress bar
                    progressBar.visibility = View.GONE
                    Handler().postDelayed({ dialog.dismiss() }, 1000)
                }, 3000)
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


//  -------------------------------Registration page------------------------------------------------


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

            RegisterSubmitForm()
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

// ------------------------------------Finish-------------------------------------------------------

    }

    private fun RegisterSubmitForm() {

        binding.fnameContainer.helperText=validFname()
        binding.MobileContainer.helperText=validMobile()
        binding.EmailContainer.helperText=validEmail()
        binding.PassContainer.helperText=validRpass()
        binding.CPassContainer.helperText=validCpass()

        val validname=binding.fnameContainer.helperText==null
        val validmobile=binding.MobileContainer.helperText==null
        val validemail=binding.EmailContainer.helperText==null
        val validRpass=binding.PassContainer.helperText==null
        val validCpass=binding.CPassContainer.helperText==null

        if (validname && validmobile && validemail && validRpass && validCpass)
        {
//            firebase value stored code-------------------------------------------------------------
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

                            Toast.makeText(this,"SuccessFully Saved", Toast.LENGTH_SHORT).show()
                        }.addOnFailureListener{
                            Toast.makeText(this,"Failed", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }


            intent= Intent(applicationContext, login::class.java)
            startActivity(intent)
            finish()

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

    //    ------------------------------confirm pass----------------------------------------------------
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
//---------------------------------------password---------------------------------------------------
    private fun passFocusListener() {
        binding.PassEdittxt.setOnFocusChangeListener { _, focused ->
            if (!focused)
            {
                binding.PassContainer.helperText=validRpass()
            }
        }
    }

    private fun validRpass(): String? {
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
//---------------------------------Email------------------------------------------------------------
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
//----------------------------MObile----------------------------------------------------------------
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
//-----------------------------------first name-----------------------------------------------------
    private fun FnameFocusListner() {
        binding.fnameEdittxt.setOnFocusChangeListener{_,focused ->
            if (!focused)
            {
                binding.fnameContainer.helperText=validFname()
            }
        }
    }

    private fun validFname(): String? {
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

// ----------------------------Date Picker----------------------------------------------------------


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

    //    ----------------------------------------------------------------------------------------------
    private fun forgotPassword(username:EditText) {
        if (username.text.toString().trim().isEmpty()){
            username.error="Please Enter Email ID"
            username.requestFocus()
            return
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(username.text.toString().trim()).matches()){
            return
        }
        auth.sendPasswordResetEmail(username.text.toString().trim())
            .addOnCompleteListener { task ->
                if (task.isSuccessful){
                    Toast.makeText(this,"Email Sent", Toast.LENGTH_SHORT).show()
                }else{
                    Toast.makeText(this,"Failed to send Email", Toast.LENGTH_SHORT).show()
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

            val email=LoginEmail.text.toString().trim()
            val password=Loginpassword.text.toString().trim()

            auth.signInWithEmailAndPassword(email,password).addOnCompleteListener{


                if (it.isSuccessful){
                    intent= Intent(applicationContext, HomeActivity::class.java)
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
            return "Invalid Password*"
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