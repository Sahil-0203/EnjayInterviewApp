package QuizQuestion

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
import Result.JavaQuizResult
import com.example.enjayinterviewapp.R
import com.example.enjayinterviewapp.databinding.ActivityCplusQuestionBinding
import java.util.*
import java.util.concurrent.TimeUnit

class CplusQuestion : AppCompatActivity()
{
    private lateinit var activityCplusQuestion: ActivityCplusQuestionBinding

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

    private var questions = arrayOf(
        "Q.1. What is a correct syntax to output \"Hello World\" in C++?",
        "Q.2. How do you insert COMMENTS in C++ code?",
        "Q.3. Which data type is used to create a variable that should store text?",
        "Q.4. How do you create a variable with the numeric value 5?",
        "Q.5. How do you create a variable with the floating number 2.8?",
        "Q.6. Which method can be used to find the length of a string?",
        "Q.7. Which operator is used to add together two values?",
        "Q.8. Which header file lets us work with input and output objects?",
        "Q.9. Which operator can be used to compare two values?",
        "Q.10. To declare an array in C++, define the variable type with:",
        "Q.11. How do you create a function in C++?",
        "Q.12. How do you call a function in C++?",
        "Q.13. Which keyword is used to create a class in C++?",
        "Q.14. What is the correct way to create an object called myObj of MyClass?",
        "Q.15. Which method can be used to find the highest value of x and y?",
        "Q.16. Which operator is used to multiply numbers?",
        "Q.17. How do you create a reference variable of an existing variable?",
        "Q.18. How do you start writing an if statement in C++?",
        "Q.19. How do you start writing a while loop in C++?",
        "Q.20. Which keyword is used to return a value inside a method?",
        "Q.21. Which statement is used to stop a loop?",
        "Q.22. Who invented C++?",
        "Q.23. Which of the following user-defined header file extension used in c++?",
        "Q.24. Which of the following is a correct identifier in C++?",
        "Q.25. Which of the following approach is used by C++?,"
    )

    private var answer = arrayOf(
        "cout << \"Hello World\";  ",            //1
        "//",
        "string",
        "int x = 5;",          //4
        "double x = 2.8;",
        "length()",    //6
        "The + sign ",
        "#include <inputstr>",
        "==",        //9
        "[]",
        "functionName()",
        "functionName();",  //12
        "class",
        "MyClass myObj;",//14
        "max(x,y)",
        "*",
        "With the & operator",     //17
        "if (x > y)",
        "while (x > y)",
        "return",//20
        "break",
        "Bjarne Stroustrup",
        "h",//23
        "VAR_1234",
        "Bottom-up"     //25
    )

    private var options = arrayOf(
        "print (\"Hello World\");  ",
        "System.out.println(\"Hello World\");",
        "cout << \"Hello World\";  ",
        "Console.WriteLine(\"Hello World\");",              //1
        "#",
        "//",
        "/*",
        "*/",         //2
        "string",
        "String",
        "myString",
        "txt",          //3
        "x=5;",
        "int x = 5;",
        "num x = 5",
        "double x = 5;",              //4
        "byte x = 2.8",
        "int x = 2.8;",
        "x = 2.8;",
        "double x = 2.8;",                //5
        "len()",
        "length()",
        "getLength()",
        "getSize()",     //6
        "The ++ sign ",
        "The & sign ",
        "The * sign ",
        "The + sign ",      //7
        "#include <inputstr>",
        "#include <stream>",
        "#include <iostream>",
        "#include <iostring>",           //8
        "++",
        "==",
        "><",
        "<>",        //9
        "[]",
        "()",
        "{}",
        "<>",      //10
        "functionName()",
        "functionName[]",
        "functionName.",
        "(functionName)",        //11
        "functionName;",
        "functionName();",
        "(functionName)",
        "None of the Above",        //12
        "class()",
        "MyClass",
        "className",
        "class",        //13
        "new myObj = MyClass();",
        "MyClass myObj;",
        "class MyClass = new myObj();",
        "class myObj = new MyClass();",      //14
        "maximum(x,y)",
        "largest(x,y)",
        "max(x,y)",
        "max(x,y)",            //15
        "-",
        "/",
        "+",
        "*",                    //16
        "With the * operator",
        "With the & operator",
        "With the + operator",
        "With the / operator",          //17
        "if x > y:",
        "if x > y",
        "if (x > y)",
        "if x > y then:",        //18
        "while (x > y)",
        "while x > y {",
        "x > y while {",
        "while x > y:",         //19
        "get",
        "break",
        "void",
        "return",     //20
        "return",
        "exit",
        "void",
        "break",        //21
        "Dennis Ritchie",
        "Bjarne Stroustrup",
        "Brian Kernighan",
        "Ken Thompson",                 //22
        "h",
        "hc",
        "c",
        "hf",        //23
        "7VARNAME",
        "VAR_1234",
        "7var_name",
        "None of the Above",        //24
        "left-Right",
        "Right-left",
        "Top-down",
        "Bottom-up"           //25
    )

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        activityCplusQuestion= ActivityCplusQuestionBinding.inflate(layoutInflater)
        setContentView(activityCplusQuestion.root)

        initViews()
    }

    //    @SuppressLint("SetTextI18n")
    private fun showNextQuestion()
    {

        checkAnswer()

        activityCplusQuestion.apply {

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
                val intent = Intent(this@CplusQuestion, JavaQuizResult::class.java)
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

        activityCplusQuestion.apply {

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

        activityCplusQuestion.apply {

            tvQuestion.text = questions[qIndex]
            radioButton1.text = options[0]
            radioButton2.text = options[1]
            radioButton3.text = options[2]
            radioButton4.text = options[3]

            nextQuestionBtn.setOnClickListener {
                if (radiogrp.checkedRadioButtonId == -1)
                {
                    Toast.makeText(this@CplusQuestion,
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

                activityCplusQuestion.apply {

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
        val builder = AlertDialog.Builder(this@CplusQuestion)
        val view = LayoutInflater.from(this@CplusQuestion).inflate(R.layout.correctansdialog, null)
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
        val builder = AlertDialog.Builder(this@CplusQuestion)
        val view = LayoutInflater.from(this@CplusQuestion).inflate(R.layout.wrongansdialog, null)

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
        val builder = AlertDialog.Builder(this@CplusQuestion)
        val view = LayoutInflater.from(this@CplusQuestion).inflate(R.layout.timerdialog, null)

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
            backToast = Toast.makeText(baseContext, "DOUBLE PRESS TO QUIT", Toast.LENGTH_SHORT)
            backToast?.show()

        }
        backPressedTime = System.currentTimeMillis()
    }
}