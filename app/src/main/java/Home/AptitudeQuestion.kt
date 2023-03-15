package Home

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import ConstantsQuestion.Constants
import InternetConnection.NetworkChangedListener
import com.example.enjayinterviewapp.HomeActivity
import ModelClass.QuestionModel
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.util.Log
import com.example.enjayinterviewapp.R
import com.example.enjayinterviewapp.databinding.ActivityAptitudeQuestionBinding
import com.google.android.material.snackbar.Snackbar

class AptitudeQuestion : AppCompatActivity(), View.OnClickListener {
   private lateinit var binding: ActivityAptitudeQuestionBinding
    private var mSelectedOption:Int=0
    private var itemvalue=1
    private var id=0


    private var backPressedTime: Long = 0
    private var backToast: Toast? = null

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityAptitudeQuestionBinding.inflate(layoutInflater)
        setContentView(binding.root)



        showQuestion()

        val op1=findViewById<TextView>(R.id.tv_option1)
        val op2=findViewById<TextView>(R.id.tv_option2)
        val op3=findViewById<TextView>(R.id.tv_option3)
        val op4=findViewById<TextView>(R.id.tv_option4)
        val btn_nextQuestion=findViewById<Button>(R.id.btn_Aptitude)

        op1.setOnClickListener(this)
        op2.setOnClickListener(this)
        op3.setOnClickListener(this)
        op4.setOnClickListener(this)
        btn_nextQuestion.setOnClickListener(this)

//  --------------------------get last id--------------------------------------------------
        val helper=SQLiteHelper(applicationContext)
        val db=helper.readableDatabase
        val query = "SELECT * FROM tblQuestion"
        val cursor2 = db?.rawQuery(query, null)
        if (cursor2!!.moveToLast()) {
            id=cursor2.getInt(0)
        }
//  ------------------------------------------------------------------------------------------------
    }

    @SuppressLint("Range")
    private fun showQuestion() {
        defaultOptionView()
//        -----------------------------------------------------------------------------------
        val btn_nextQuestion=findViewById<Button>(R.id.btn_Aptitude)

        if (mSelectedOption==0){
            btn_nextQuestion.text="Confirm"
        }else if (mSelectedOption != 0)
        {
            btn_nextQuestion.text="Go To Next"
        }

//--------------------------------------------------------------------------------------------------
        val helper=SQLiteHelper(applicationContext)
        val db=helper.readableDatabase
        val query = "SELECT * FROM tblQuestion where Id ==$itemvalue"
        val cursor1 = db?.rawQuery(query, null)

        while (cursor1?.moveToNext() == true) {
            val queTextView=findViewById<TextView>(R.id.Aptitudequestion)
            val tv_op1=findViewById<TextView>(R.id.tv_option1)
            val tv_op2=findViewById<TextView>(R.id.tv_option2)
            val tv_op3=findViewById<TextView>(R.id.tv_option3)
            val tv_op4=findViewById<TextView>(R.id.tv_option4)
            val name =cursor1.getString(cursor1.getColumnIndex("question"))
            val op1=cursor1.getString(cursor1.getColumnIndex("option1"))
            val op2=cursor1.getString(cursor1.getColumnIndex("option2"))
            val op3=cursor1.getString(cursor1.getColumnIndex("option3"))
            val op4=cursor1.getString(cursor1.getColumnIndex("option4"))

            queTextView.text=name.toString()
            tv_op1.text=op1.toString()
            tv_op2.text=op2.toString()
            tv_op3.text=op3.toString()
            tv_op4.text=op4.toString()
        }
    }


    private fun defaultOptionView(){


        val op1=findViewById<TextView>(R.id.tv_option1)
        val op2=findViewById<TextView>(R.id.tv_option2)
        val op3=findViewById<TextView>(R.id.tv_option3)
        val op4=findViewById<TextView>(R.id.tv_option4)

        val options=ArrayList<TextView>()
        options.add(0,op1)
        options.add(1,op2)
        options.add(2,op3)
        options.add(3,op4)

        for (option in options){
            option.setTextColor(Color.parseColor("#ffffff"))
            option.typeface=Typeface.DEFAULT
            option.background= ContextCompat.getDrawable(
                this,R.drawable.questionbackground
            )
        }
    }

    @SuppressLint("Range")
    override fun onClick(v: View?) {
        val op1=findViewById<TextView>(R.id.tv_option1)
        val op2=findViewById<TextView>(R.id.tv_option2)
        val op3=findViewById<TextView>(R.id.tv_option3)
        val op4=findViewById<TextView>(R.id.tv_option4)
        val btn_nextQuestion=findViewById<Button>(R.id.btn_Aptitude)


        when(v?.id) {
            R.id.tv_option1 ->
            {
                SelectedOptionView(op1,1)
            }
            R.id.tv_option2 ->
            {
                SelectedOptionView(op2,2)
            }
            R.id.tv_option3 ->
            {
                SelectedOptionView(op3,3)
            }
            R.id.tv_option4 ->
            {
                SelectedOptionView(op4,4)
            }
            R.id.btn_Aptitude ->
            {
                disableAllOption()

                if (mSelectedOption==0){

                    itemvalue++
                    when{
                        itemvalue <=id ->{
                            showQuestion()
                        }
                        else ->{
                            intent=Intent(applicationContext,HomeActivity::class.java)
                            startActivity(intent)
                            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
                            finish()
                        }
                    }


                }

                else
                {
//                -----------------------------------------
                    val helper = SQLiteHelper(applicationContext)
                    val db = helper.readableDatabase
                    val query = "SELECT * FROM tblQuestion where Id ==$itemvalue"
                    val cursor1 = db?.rawQuery(query, null)

                    while (cursor1?.moveToNext() == true) {

                        val ans = cursor1.getInt(cursor1.getColumnIndex("answer"))
//                --------------------------------------------------------


                        if (ans != mSelectedOption) {
                            answerView(mSelectedOption, R.drawable.wrong_bg)
                            val snackbar= Snackbar.make(binding.root," Option $ans Is Correct",
                                Snackbar.LENGTH_LONG)
                            snackbar.setBackgroundTint(Color.GREEN)
                            snackbar.setTextColor(Color.BLACK)
                            snackbar.show()
                        }
                        else{
                            answerView(mSelectedOption,R.drawable.right_bg)
                        }
                        if (itemvalue ==id){
                            btn_nextQuestion.text="Finish"
                        }else
                        {
                            btn_nextQuestion.text="Go to Next"
                        }

                        mSelectedOption = 0

                    }
                }
            }

        }

    }

    private fun disableAllOption() {
        val op1 = findViewById<TextView>(R.id.tv_option1)
        val op2 = findViewById<TextView>(R.id.tv_option2)
        val op3 = findViewById<TextView>(R.id.tv_option3)
        val op4 = findViewById<TextView>(R.id.tv_option4)
        if (mSelectedOption==0){
            op1.isEnabled=true
            op2.isEnabled=true
            op3.isEnabled=true
            op4.isEnabled=true
        }
        if (mSelectedOption!=0){
            op1.isEnabled=false
            op2.isEnabled=false
            op3.isEnabled=false
            op4.isEnabled=false
        }
    }

    private fun answerView(answer: Int,drawableView:Int){
        val op1=findViewById<TextView>(R.id.tv_option1)
        val op2=findViewById<TextView>(R.id.tv_option2)
        val op3=findViewById<TextView>(R.id.tv_option3)
        val op4=findViewById<TextView>(R.id.tv_option4)
        when(answer){
            1->
            {
                op1.background = ContextCompat.getDrawable(
                    this,drawableView
                    )
            }
            2->
            {
                op2.background = ContextCompat.getDrawable(this,drawableView)
            }
            3->
            {
                op3.background = ContextCompat.getDrawable(this,drawableView)
            }
            4->
            {
                op4.background = ContextCompat.getDrawable(this,drawableView)
            }
        }

    }

    private fun SelectedOptionView(tv:TextView,selectedOption:Int){

        defaultOptionView()
        mSelectedOption =selectedOption

        tv.setTextColor(Color.parseColor("#FFFFFF"))
        tv.setTypeface(tv.typeface,Typeface.BOLD)
        tv.background=ContextCompat.getDrawable(
            this, R.drawable.selected_option_border
        )
    }

    override fun onBackPressed() {

        if (backPressedTime + 2000 > System.currentTimeMillis())
        {
            backToast?.cancel()
            finish()

        }

        else
        {
            backToast = Toast.makeText(baseContext, "DOUBLE PRESS TO QUIT", Toast.LENGTH_SHORT)
            backToast?.show()

        }
        backPressedTime = System.currentTimeMillis()
    }
}