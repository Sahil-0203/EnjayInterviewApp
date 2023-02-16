package com.example.enjayinterviewapp

import android.annotation.SuppressLint
import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.widget.Button
import android.widget.RadioButton
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.enjayinterviewapp.databinding.ActivityCquestionBinding
import java.util.*
import java.util.concurrent.TimeUnit

class CQuestion : AppCompatActivity()
{
    private lateinit var activityCQuestion: ActivityCquestionBinding

    private var countDownTimer: CountDownTimer? = null
    private val countDownInMilliSecond: Long = 30000
    private val countDownInterval: Long = 1000
    private var timeLeftMilliSeconds: Long = 0
    private var defaultColor: ColorStateList? = null
    private var score = 0
    private var correct = 0
    private var wrong = 0
    private var skip = 0
    private var qIndex = 0
    private var updateQueNo = 1

    private var backPressedTime: Long = 0
    private var backToast: Toast? = null

    //swift language

    private var questions = arrayOf(
        "Q.1. What are the features of Swift Programming?",
        "Q.2. What does the question mark (?) indicate A particular property is",
        "Q.3. What are the common execution states in iOS?",
        "Q.4. What are the advantages of swift?",
        "Q.5. What statement can be used to stop the execution of a loop, if, or switch statement?",
        "Q.6. What are the collection types available in Swift?",
        "Q.7. How many types of integers are in Swift?",
        "Q.8. What are the disadvantages of Swift?",
        "Q.9. Which of the following is not a literals in swift?",
        "Q.10. In Swift programming language, single-line comments are started with ___________?",
        "Q.11. How many types of classes in Inheritance in Swift?",
        "Q.12. How many varieties of collection types in Swift?",
        "Q.13.  Which Of The Following Is Incorrect Data Type In SWIFT ?",
        "Q.14. Which Of The Following Is Incorrect Value Type Of The Swift?",
        "Q.15. How do you declare an Iboutlet property?",
        "Q.16. What are the advantages of Swift?",
        "Q.17. What type of integer is denoted by \"Int8\"?",
        "Q.18. Swift is which type of language?",
        "Q.19. Who is introduced Swift?",
        "Q.20. Which Of The Following Framework Is Not Used In IOS?",
        "Q.21. What is the meaning of question mark \"?\" in Swift?",
        "Q.22. What does the question mark (?) indicate A particular property is",
        "Q.23. What are the collection types in Swift?",
        "Q.24. Numbers with decimal values or fractional components are called?",
        "Q.25. Which of the following is not a literals in swift?,"
    )

    private var answer = arrayOf(
        "All of these",            //1
        "Optional",
        "All of the above",
        "All of the above",          //4
        "Break",
        "Both A and B",    //6
        "3",
        "Both A and B",
        "Quad Literals",        //9
        "double slashes",
        "2",
        "2",  //12
        "Char",
        "Class",//14
        "None of the Above",
        "All of the above",
        "Signed",     //17
        "All of the Above",
        "Chris Lattner",
        "AppKit Framework",//20
        "used in property declaration",
        "Optional",
        "Dictionary and array",//23
        "floating numbers",
        "Quad Literals"     //25
    )

    private var options = arrayOf(
        "Arrays and integers are checked for overflow",
        "Switch function can be used instead of using “if” statement",
        "It eliminates the classes that are in an unsafe mode",
        "All of these",              //1
        "Optional",
        "Changed",
        "Missing",
        "Necessary",         //2
        "Not running",
        "Inactive",
        "Active",
        "All of the above",          //3
        "Much faster than other languages",
        "Supports pattern matching",
        "Type-safe language",
        "All of the above",              //4
        "Execute",
        "Break",
        "Damper",
        "Stop",                //5
        "Array",
        "Dictionary",
        "Both A and B",
        "None of the Above",     //6
        "1",
        "2",
        "3",
        "4",      //7
        "Poor interoperability with third-party tools and IDEs",
        "Lack of support for earlier iOS versions",
        "Both A and B",
        "None of the Above",           //8
        "Quad Literals",
        "Hexadecimal Literals",
        "Decimal Literals",
        "Binary Literals",        //9
        "double slashes",
        "single slashes",
        "asterisk",
        "hash",      //10
        "1",
        "2",
        "3",
        "4",        //11
        "1",
        "2",
        "3",
        "4",        //12
        "UInt",
        "Double",
        "Char",
        "Optional",        //13
        "Character",
        "Double",
        "Enum",
        "Class",      //14
        "IBOutlet var button: UIButton",
        "var button: UIButton(IBOutlet)",
        "var button: UIButtonoutlet",
        "None of the Above",            //15
        "Swift is Safe",
        "Swift is Fast",
        "Swift is Open Source",
        "All of the above",                    //16
        "Open",
        "Unsigned",
        "Signed",
        "Close",          //17
        "scripting language",
        "object-oriented programming language",
        "type-safe language",
        "All of the Above",        //18
        "Chris Lattner",
        "Vikram Adve",
        "Steve Jobs",
        "Tanya Lattner",         //19
        "AppKit Framework",
        "UIKit Framework",
        "Foundation Framework",
        "All of the Above",     //20
        "used in loop declaration",
        "used in property declaration",
        "used in function declaration",
        "used in parameter declaration",        //21
        "Optional",
        "Necessary",
        "Changed",
        "Missing",                 //22
        "Array and library",
        "Dictionary and array",
        "Dictionary and library",
        "None of the Above",        //23
        "integer number",
        "floating numbers",
        "decimal number",
        "None of the Above",        //24
        "third Literals",
        "second Literals",
        "first Literals",
        "Quad Literals"           //25
    )

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        activityCQuestion= ActivityCquestionBinding.inflate(layoutInflater)
        setContentView(activityCQuestion.root)

        initViews()
    }

    //    @SuppressLint("SetTextI18n")
    private fun showNextQuestion()
    {

        checkAnswer()

        activityCQuestion.apply {

            if (updateQueNo < 25)
            {
                tvNoOfQues.text = "${updateQueNo + 1}/25"
                updateQueNo++
            }
            if (qIndex <= questions.size - 1)
            {
                tvQuestion.text = questions[qIndex]
                radioButton1.text = options[qIndex * 4] // 2*4=8
                radioButton2.text = options[qIndex * 4 + 1] //  2*4+1=9
                radioButton3.text = options[qIndex * 4 + 2] //  2*4+2=10
                radioButton4.text = options[qIndex * 4 + 3] //  2*4+3=11
            }
            else
            {
                score = correct
                val intent = Intent(this@CQuestion, JavaQuizResult::class.java)
                intent.putExtra("correct", correct)
                intent.putExtra("wrong", wrong)
                intent.putExtra("skip", skip)
                startActivity(intent)
                finish()
            }
            radiogrp.clearCheck()
        }
    }
    //    @SuppressLint("SetTextI18n")
    private fun checkAnswer()
    {

        activityCQuestion.apply {

            if (radiogrp.checkedRadioButtonId == -1)
            {
                skip++
                timeOverAlertDialog()
            }
            else
            {
                val checkRadioButton = findViewById<RadioButton>(radiogrp.checkedRadioButtonId)
                val checkAnswer = checkRadioButton.text.toString()

                if (checkAnswer == answer[qIndex])
                {
                    correct++
                    txtPlayScore.text = "Score : $correct"
                    correctAlertDialog()
                    countDownTimer?.cancel()
                }
                else
                {
                    wrong++
                    wrongAlertDialog()
                    countDownTimer?.cancel()
                }
            }
            qIndex++
        }
    }

    //    @SuppressLint("SetTextI18n")
    private fun initViews()
    {

        activityCQuestion.apply {

            tvQuestion.text = questions[qIndex]
            radioButton1.text = options[0]
            radioButton2.text = options[1]
            radioButton3.text = options[2]
            radioButton4.text = options[3]

            nextQuestionBtn.setOnClickListener {
                if (radiogrp.checkedRadioButtonId == -1)
                {
                    Toast.makeText(this@CQuestion,
                        "Please select an options",
                        Toast.LENGTH_SHORT)
                        .show()
                }
                else
                {
                    showNextQuestion()
                }
            }

            tvNoOfQues.text = "$updateQueNo/25"
            tvQuestion.text = questions[qIndex]

            defaultColor = quizTimer.textColors


            timeLeftMilliSeconds = countDownInMilliSecond

            statCountDownTimer()
        }
    }

    private fun statCountDownTimer()
    {
        countDownTimer = object : CountDownTimer(timeLeftMilliSeconds, countDownInterval)
        {
            override fun onTick(millisUntilFinished: Long)
            {

                activityCQuestion.apply {

                    timeLeftMilliSeconds = millisUntilFinished
                    val second = TimeUnit.MILLISECONDS.toSeconds(timeLeftMilliSeconds).toInt()

                    val timer = String.format(Locale.getDefault(), "Time: %02d", second)
                    quizTimer.text = timer

                    if (timeLeftMilliSeconds < 10000)
                    {
                        quizTimer.setTextColor(Color.RED)
                    }
                    else
                    {
                        quizTimer.setTextColor(defaultColor)
                    }
                }
            }

            override fun onFinish()
            {
                showNextQuestion()
            }
        }.start()
    }

    @SuppressLint("SetTextI18n")
    private fun correctAlertDialog()
    {
        val builder = AlertDialog.Builder(this@CQuestion)
        val view = LayoutInflater.from(this@CQuestion).inflate(R.layout.correctansdialog, null)
        builder.setView(view)

        val tvScore = view.findViewById<TextView>(R.id.tvDialog_score)
        val correctOkBtn = view.findViewById<Button>(R.id.correct_ok)

        tvScore.text = "Score : $correct"

        var alertDialog = builder.create()

        correctOkBtn.setOnClickListener {
            timeLeftMilliSeconds = countDownInMilliSecond
            statCountDownTimer()
            alertDialog.dismiss()
        }
        alertDialog.show()
    }

    @SuppressLint("SetTextI18n")
    private fun wrongAlertDialog()
    {
        val builder = AlertDialog.Builder(this@CQuestion)
        val view = LayoutInflater.from(this@CQuestion).inflate(R.layout.wrongansdialog, null)

        builder.setView(view)

        val tvWrongDialogCorrectAns = view.findViewById<TextView>(R.id.tv_wrongDialog_correctAns)
        val wrongOk = view.findViewById<Button>(R.id.wrong_ok)

        tvWrongDialogCorrectAns.text = "Correct Answer : " + answer[qIndex]

        var alertDialog = builder.create()

        wrongOk.setOnClickListener {
            timeLeftMilliSeconds =
                countDownInMilliSecond
            statCountDownTimer()
            alertDialog.dismiss()
        }

        alertDialog.show()
    }

    @SuppressLint("SetTextI18n")
    private fun timeOverAlertDialog()
    {
        val builder = AlertDialog.Builder(this@CQuestion)
        val view = LayoutInflater.from(this@CQuestion).inflate(R.layout.timerdialog, null)

        builder.setView(view)

        val timeOverOk = view.findViewById<Button>(R.id.timeOver_ok)
        var alertDialog = builder.create()

        timeOverOk.setOnClickListener {
            timeLeftMilliSeconds = countDownInMilliSecond

            statCountDownTimer()
            alertDialog.dismiss()
        }
        alertDialog.show()
    }
    override fun onBackPressed() {

        if (backPressedTime + 2000 > System.currentTimeMillis())
        {
            backToast?.cancel()
            finish()
        }
        else
        {
            backToast = Toast.makeText(baseContext, "DOUBLE PRESS TO QUIT Quiz", Toast.LENGTH_SHORT)
            backToast?.show()

        }
        backPressedTime = System.currentTimeMillis()
    }
}