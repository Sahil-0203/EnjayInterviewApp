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
import com.example.enjayinterviewapp.databinding.ActivityPhpQuestionBinding
import java.util.*
import java.util.concurrent.TimeUnit

class PhpQuestion : AppCompatActivity()
{
    private lateinit var activityPhpQuestion: ActivityPhpQuestionBinding

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
        "Q.1. What does PHP stand for?",
        "Q.2. Who is the father of PHP?",
        "Q.3. Which of the following is the correct syntax to write a PHP code?",
        "Q.4. Which of the following is the correct way to add a comment in PHP code?",
        "Q.5. Which of the following is the default file extension of PHP files?",
        "Q.6. How to define a function in PHP?",
        "Q.7. Which of the following PHP functions can be used for generating unique ids?",
        "Q.8. A function in PHP which starts with __ (double underscore) is known as __________",
        "Q.9. How many functions does PHP offer for searching and modifying strings using Perl-compatible regular expressions.",
        "Q.10. Which of the following web servers are required to run the PHP script?",
        "Q.11. Which of the following PHP functions can be used to get the current memory usage?",
        "Q.12. Which one of the following PHP function is used to determine a file’s last access time?",
        "Q.13. PHP recognizes constructors by the name _________",
        "Q.14. The developers of PHP deprecated the safe mode feature as of which PHP version?",
        "Q.15. What does PDO stand for?",
        "Q.16. Which version of PHP introduced the advanced concepts of OOP?",
        "Q.17. Which one of the following is the default PHP session name?",
        "Q.18. Which PHP function displays the web page’s most recent modification date?",
        "Q.19. Which one of the following property scopes is not supported by PHP?",
        "Q.20. PHP’s numerically indexed array begin with position ___________",
        "Q.21. Which of the following PHP function will return true if a variable is an array or false if it is not an array?",
        "Q.22. Which of the following function is used to get the value of the previous element in an array?",
        "Q.23. Which function returns an array consisting of associative key/value pairs?",
        "Q.24. If one intends to create a model that will be assumed by a number of closely related objects, which class must be used?",
        "Q.25. Which method is used to tweak an object’s cloning behavior?,"
    )

    private var answer = arrayOf(
        "PHP:Hypertext Preprocessor",            //1
        "Rasmus Lerdorf",//2
        "<?php ?>",
        "All of the Above",          //4
        ".php",
        "function functionName(parameters) {function body}",    //6
        "uniqueid()",
        "Magic Function",
        "8",        //9
        "All of the Above",
        "memory_get_usage()",
        "fileatime()",  //12
        "function __construct()",
        "PHP 5.3.0",//14
        "PHP Data Object",
        "PHP 5",
        "PHPSESSID",     //17
        "getlastmod()",
        "friendly",
        "0",//20
        "is_array()",
        "prev()",
        "array_count_values()",//23
        "Abstract class",
        "__clone()"     //25
    )

    private var options = arrayOf(
        "PHP:Hypertext Preprocessor",
        "Personal Hypertext Processor",
        "Private Home Page",
        "None of the Above",              //1
        "Rasmus Lerdorf",
        "Willam Makepiece",
        "List Barely",
        "Drek Kolkevi",         //2
        "<?php ?>",
        "< php >",
        "< ? php ?>",
        "<? ?>",          //3
        "#",
        "//",
        "/**/",
        "All of the Above",              //4
        ".php",
        ".ph",
        ".xml",
        ".xml",                //5
        "functionName(parameters) {function body}",
        "function {function body}",
        "function functionName(parameters) {function body}",
        "function",                      //6
        "uniqueid()",
        "md5()",
        "id()",
        "mdid()",      //7
        "Default Function",
        "User Defined Function",
        "Inbuilt Function",
        "Magic Function",           //8
        "8",
        "10",
        "12",
        "9",        //9
        "Apache",
        "IIS",
        "XAMPP",
        "All of the Above",      //10
        "get_peak_usage()",
        "memory_get_peak_usage()",
        "get_usage()",
        "memory_get_usage()",        //11
        "fileatime()",
        "fileltime()",
        "filectime()",
        "filetime()",        //12
        "function __construct()",
        "function _construct()",
        "classname()",
        "_construct()",        //13
        "PHP 5.3.0",
        "PHP 5.3.1",
        "PHP 5.1.0",
        "PHP 5.2.0",      //14
        "PHP Database Orientation",
        "PHP Data Orientation",
        "PHP Data Object",
        "PHP Database Object",            //15
        "PHP 4",
        "PHP 5",
        "PHP 6",
        "PHP 7",                    //16
        "PHPSESSIONID",
        "PHPIDSESS",
        "PHPSESID",
        "PHPSESSID",          //17
        "get_last_mod()",
        "last_mod()",
        "getlastmod()",
        "lastmod()",        //18
        "friendly",
        "final",
        "public",
        "static",         //19
        "-1",
        "0",
        "1",
        "2",     //20
        "is_array()",
        "do_array()",
        "in_array()",
        "this_array()",        //21
        "previous()",
        "last()",
        "prev()",
        "before()",                 //22
        "array_count_values()",
        "array_values()",
        "count_values()",
        "count()",        //23
        "Interface",
        "Abstract class",
        "Static class",
        "Normal class",        //24
        "clone()",
        "_clone",
        "object_clone()",
        "__clone()"           //25
    )

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        activityPhpQuestion=ActivityPhpQuestionBinding.inflate(layoutInflater)
        setContentView(activityPhpQuestion.root)

        initViews()
    }

    //    @SuppressLint("SetTextI18n")
    private fun showNextQuestion()
    {

        checkAnswer()

        activityPhpQuestion.apply {

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
                val intent = Intent(this@PhpQuestion, JavaQuizResult::class.java)
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

        activityPhpQuestion.apply {

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

        activityPhpQuestion.apply {

            tvQuestion.text = questions[qIndex]
            radioButton1.text = options[0]
            radioButton2.text = options[1]
            radioButton3.text = options[2]
            radioButton4.text = options[3]

            nextQuestionBtn.setOnClickListener {
                if (radiogrp.checkedRadioButtonId == -1)
                {
                    Toast.makeText(this@PhpQuestion,
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

                activityPhpQuestion.apply {

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
        val builder = AlertDialog.Builder(this@PhpQuestion)
        val view = LayoutInflater.from(this@PhpQuestion).inflate(R.layout.correctansdialog, null)
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
        val builder = AlertDialog.Builder(this@PhpQuestion)
        val view = LayoutInflater.from(this@PhpQuestion).inflate(R.layout.wrongansdialog, null)

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
        val builder = AlertDialog.Builder(this@PhpQuestion)
        val view = LayoutInflater.from(this@PhpQuestion).inflate(R.layout.timerdialog, null)

        builder.setView(view)

        val timeOverOk = view.findViewById<Button>(R.id.timeOver_ok)
        val alertDialog = builder.create()

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