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
import com.example.enjayinterviewapp.databinding.ActivityPythonQuestionBinding
import java.util.*
import java.util.concurrent.TimeUnit

class PythonQuestion : AppCompatActivity()
{
    private lateinit var activityPythonQuestion: ActivityPythonQuestionBinding

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
        "Q.1. What is a correct syntax in Python?",
        "Q.2. What is the data type of print(type(0xFF))",
        "Q.3. What is the data type of print(type(10))",
        "Q.4. What is the output of the following code\n" +
                "x = 6\n" +
                "y = 2\n" +
                "print(x ** y)",
        "Q.5. What is the output of print(2%6)",
        "Q.6. Which of the following operators has the highest precedence?\n"+
                "Hint: Python operators precedence",
        "Q.7. Which of the following is incorrect file handling mode in Python",
        "Q.8. What is true for file mode x\n" +
                "Hint: Create File in Python",
        "Q.9. In Python 3, which functions are used to accept input from the user",
        "Q.10. Which of the following is incorrect file handling mode in Python.",
        "Q.11. Select which is true for Python function",
        "Q.12. What is the value of xx = 0\n" +
                "while (x < 100):\n" +
                "  x+=2\n" +
                "print(x)",
        "Q.13. What is the type of the following variable\n" +
                "x = -5j",
        "Q.14. Choose the correct function to get the ASCII code of a character",
        "Q.15. What is the output of the following\n" +
                "l = [None] * 10\n" +
                "print(len(l))",
        "Q.16. What is the output of the following\n" +
                "aList = [5, 10, 15, 25]\n" +
                "print(aList[::-2])",
        "Q.17. What is the output of the following code\n" +
                "my_list = [\"Hello\", \"Python\"]\n" +
                "print(\"-\".join(my_list))",
        "Q.18. Select all correct ways to copy a dictionary in Python",
        "Q.19. Select correct ways to create an empty dictionary",
        "Q.20. I want to generate a random secure hex token of 32 bytes to reset the password, which method should I use",
        "Q.21. What is the correct file extension for Python files?",
        "Q.22. What is the correct syntax to output the type of a variable or object in Python?",
        "Q.23. Which method can be used to remove any whitespace from both the beginning and the end of a string?",
        "Q.24. Which method can be used to return a string in upper case letters?",
        "Q.25. Which method can be used to replace parts of a string?,"
    )

    private var answer = arrayOf(
        "print(\"Hello, World!\")",            //1
        "int",
        "int",
        "36",          //4
        "2",        //5
        "*",    //6
        "xr",
        "Create a file, returns an error if the file exists",
        "input()",        //9
        "t+",          //10
        "Both A and B",
        "100",  //12
        "complex",           //13
        "ord('char')",//14
        "10",
        "[25, 10]",
        "Hello-Python",     //17
        "Both A and B",
        "Both A and B",
        "secrets.token_hex(32)",//20
        ".py",
        "print(type(x))",
        "strip()",//23
        "upper()",
        "replace()"     //25
    )

    private var options = arrayOf(
        "print(\"Hello, World!\")",
        "echo \"Hello World!\"",
        "System.out.print \"Hello World!\"",
        "fun main()",                          //1
        "number",
        "hex",
        "hexint",
        "int",         //2
        "float",
        "int",
        "Integer",
        "number",          //3
        "12",
        "36",
        "48",
        "24",              //4
        "0.33",
        "ValueError",
        "2",
        "none",                //5
        "+",
        "*",
        "not",
        "&",     //6
        "xr",
        "ab",
        "ab+",
        "wb+",      //7
        "Create a file, returns an error if the file exists",
        "Create a file if it doesn’t exists else Truncate the existed file",
        "create a file if the specified file does not exist",
        "none",           //8
        "string()",
        "input()",
        "raw_input()",
        "rawinput()",        //9
        "t+",
        "a",
        "b",
        "r",      //10
        "A function can take an unlimited number of arguments.",
        "A Python function can return multiple values",
        "Both A and B",
        "None of the Above",        //11
        "100",
        "101",
        "99",
        "None of the Above",        //12
        "int",
        "real",
        "complex",
        "imaginary",        //13
        "char('char')",
        "ord('char')",
        "ascii('char')",
        "None of the Above",      //14
        "10",
        "0",
        "Syntax Error",
        "100",            //15
        "[10, 5]",
        "[5, 10]",
        "[10, 25]",
        "[25, 10]",                    //16
        "Hello-Python",
        "HelloPython-",
        "Hello,Python",
        "-HelloPython",          //17
        "dict2 = dict1.copy()",
        "dict2 = dict(dict1)",
        "Both A and B",
        "None of the Above",        //18
        "sampleDict = {}",
        "sampleDict = dict()",
        "Both A and B",
        "None of the Above",         //19
        "secrets.tokenH_hex(64)",
        "secrets.tokenHex(64)",
        "secrets.tokenHex(32)",
        "secrets.token_hex(32)",     //20
        ".pyth",
        ".py",
        ".pt",
        ".pyt",        //21
        "print(type(x))",
        "print(typeofx)",
        "print(typeof(x))",
        "print(typeOf(x))",                 //22
        "trim()",
        "strip()",
        "ptrim()",
        "len()",        //23
        "uppercase()",
        "upper()",
        "upperCase()",
        "toUpperCase()",        //24
        "replace()",
        "repl()",
        "switch()",
        "replaceString()"           //25
    )

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        activityPythonQuestion=ActivityPythonQuestionBinding.inflate(layoutInflater)
        setContentView(activityPythonQuestion.root)

        initViews()
    }

    //    @SuppressLint("SetTextI18n")
    private fun showNextQuestion()
    {

        checkAnswer()

        activityPythonQuestion.apply {

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
                val intent = Intent(this@PythonQuestion, JavaQuizResult::class.java)
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

        activityPythonQuestion.apply {

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

        activityPythonQuestion.apply {

            tvQuestion.text = questions[qIndex]
            radioButton1.text = options[0]
            radioButton2.text = options[1]
            radioButton3.text = options[2]
            radioButton4.text = options[3]

            nextQuestionBtn.setOnClickListener {
                if (radiogrp.checkedRadioButtonId == -1)
                {
                    Toast.makeText(this@PythonQuestion,
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

                activityPythonQuestion.apply {

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
        val builder = AlertDialog.Builder(this@PythonQuestion)
        val view = LayoutInflater.from(this@PythonQuestion).inflate(R.layout.correctansdialog, null)
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
        val builder = AlertDialog.Builder(this@PythonQuestion)
        val view = LayoutInflater.from(this@PythonQuestion).inflate(R.layout.wrongansdialog, null)

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
        val builder = AlertDialog.Builder(this@PythonQuestion)
        val view = LayoutInflater.from(this@PythonQuestion).inflate(R.layout.timerdialog, null)

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