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
import com.example.enjayinterviewapp.databinding.ActivityKotlinQuestionBinding
import java.util.*
import java.util.concurrent.TimeUnit

class KotlinQuestion : AppCompatActivity()
{
    private lateinit var activityKotlinQuestion: ActivityKotlinQuestionBinding

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
        "Q.1. Kotlin is developed by?",
        "Q.2. Which of following is used to handle null exceptions in Kotlin?",
        "Q.3. Which file extension is used to save Kotlin files.",
        "Q.4. All classes in Kotlin classes are by default?",
        "Q.5. What is correct way to create an arraylist in Kotlin?",
        "Q.6. What is an immutable variable?",
        "Q.7. Which of following targets currently not supported by Kotlin?",
        "Q.8. How to make a multi lined comment in Kotlin?",
        "Q.9. How do you get the length of a string in Kotlin?",
        "Q.10. Which of the followings constructors are available in Kotlin?",
        "Q.11. Which of the following extension methods are used in Kotlin?",
        "Q.12. Which of the following is not the basic data types in Kotlin?",
        "Q.13. Kotlin is interoperable with Java because it uses JVM bytecode.",
        "Q.14. How can you declare a variable in Kotlin?",
        "Q.15. How many types of constructors available in Kotlin?",
        "Q.16. Which of the following is Use for reading contents of file to ByteArray?",
        "Q.17. What is the use of data class in Kotlin?",
        "Q.18. Is there any Ternary Conditional Operator in Kotlin like in Java?",
        "Q.19. Which of th following is used to compare two strings in Kotlin?",
        "Q.20. ____________ helps to iterate through a range.",
        "Q.21. Which file extension is used to save Kotlin files?",
        "Q.22. Which keyword is used to declare a function?",
        "Q.23. Which operator is used to add together two values?",
        "Q.24. What is the output of the following code: println(5 > 3 && 5 < 10)",
        "Q.25. Which symbol is used for string templates/interpolation?,"
    )

    private var answer = arrayOf(
        "JetBrains",            //1
        "Elvis Operator",
        ".kt or .kts",
        "final",          //4
        "val list = arrayListOf(1, 2, 3)",
        "A variable that cannot change, read-only",    //6
        ".NET CLR",
        "/* */",
        "str.length",        //9
        "Both A and B",
        "All of the Above",
        "Lists",  //12
        "Yes",
        "value my_var: Char",//14
        "2",
        "readBytes()",
        "holds the basic data types",     //17
        "TRUE",
        "Both A and B",
        "Ranges operator",//20
        ".kt",
        "fun",
        "The + sign ",//23
        "true",
        "$"     //25
    )

    private var options = arrayOf(
        "JetBrains",
        "Google",
        "Microsoft",
        "Adobe",              //1
        "Lambda function",
        "Sealed Class",
        "Range",
        "Elvis Operator",         //2
        ".kt or .kts",
        ".android",
        ".kot",
        ".java",          //3
        "sealed",
        "abstract",
        "public",
        "final",                                //4
        "val list = arrayListOf(1, 2, 3)",
        "val set = hashSetOf(1, 2, 3)",
        "val map = hashMapOf(1 to \"one\", 2 to \"two\", 3 to \"three\")",
        "enum class Color {RED, GREEN, BLUE}",                                  //5
        "A variable that can be changed",
        "A variable used for string interpolation",
        "A variable that cannot change, read-only",
        "None of the Above",     //6
        ".NET CLR",
        "LLVM",
        "JavaScript",
        "None of the Above",      //7
        "//",
        "/* */",
        "/ multi line comment /",
        "None of the Above",           //8
        "str.length",
        "str.lengthOf",
        "length(str)",
        "lengthOf()",        //9
        "Primary constructor",
        "Secondary constructor",
        "Both A and B",
        "None of the Above",      //10
        "Read texts () & Headlines ()",
        "Buffer reader ()",
        "Read each line ()",
        "All of the Above",        //11
        "Lists",
        "Arrays",
        "Numbers",
        "Strings",        //12
        "Yes",
        "No",
        "Can be yes or no",
        "Can not say",        //13
        "value my_var: Char",
        "value my_var: Char",
        "value Char : my_var",
        "my_var: Char",      //14
        "1",
        "2",
        "3",
        "4",            //15
        "readText()",
        "readLines()",
        "readBytes()",
        "bufferedReader()",                    //16
        "present the basic data types",
        "delete the basic data types",
        "extract the basic data types",
        "holds the basic data types",          //17
        "TRUE",
        "FALSE",
        "Can be true or false",
        "Can not say",        //18
        "Using == operator",
        "Using compareTo() extension function",
        "Both A and B",
        "None of the Above",         //19
        "OR operator",
        "And operator",
        "Ranges operator",
        "Conditional operator",     //20
        ".java",
        ".kt",
        ".kotlin",
        ".kot",        //21
        "fun",
        "function",
        "define",
        "decl",                 //22
        "The + sign ",
        "The * sign ",
        "The & sign ",
        "The ADD keyword",        //23
        "true",
        "false",
        "5",
        "2",        //24
        "*",
        "$",
        "&",
        "."           //25
    )

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        activityKotlinQuestion= ActivityKotlinQuestionBinding.inflate(layoutInflater)
        setContentView(activityKotlinQuestion.root)

        initViews()
    }

    //    @SuppressLint("SetTextI18n")
    private fun showNextQuestion()
    {

        checkAnswer()

        activityKotlinQuestion.apply {

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
                val intent = Intent(this@KotlinQuestion, JavaQuizResult::class.java)
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

        activityKotlinQuestion.apply {

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

        activityKotlinQuestion.apply {

            tvQuestion.text = questions[qIndex]
            radioButton1.text = options[0]
            radioButton2.text = options[1]
            radioButton3.text = options[2]
            radioButton4.text = options[3]

            nextQuestionBtn.setOnClickListener {
                if (radiogrp.checkedRadioButtonId == -1)
                {
                    Toast.makeText(this@KotlinQuestion,
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

                activityKotlinQuestion.apply {

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
        val builder = AlertDialog.Builder(this@KotlinQuestion)
        val view = LayoutInflater.from(this@KotlinQuestion).inflate(R.layout.correctansdialog, null)
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
        val builder = AlertDialog.Builder(this@KotlinQuestion)
        val view = LayoutInflater.from(this@KotlinQuestion).inflate(R.layout.wrongansdialog, null)

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
        val builder = AlertDialog.Builder(this@KotlinQuestion)
        val view = LayoutInflater.from(this@KotlinQuestion).inflate(R.layout.timerdialog, null)

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