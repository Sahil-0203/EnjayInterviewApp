package QuizQuestion

import Home.SQLiteHelper
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
import com.example.enjayinterviewapp.databinding.ActivityJavaQuestionBinding
import java.util.*
import java.util.concurrent.TimeUnit

class JavaQuestion : AppCompatActivity()            //error
{
    private lateinit var activityJavaQuestion: ActivityJavaQuestionBinding

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
        "Q.1. Number of primitive data types in Java?",
        "Q.2. What is the size of float and double in Java?",
        "Q.3. Automatic type conversion is possible in which of the possible case",
        "Q.4. Find the Output of the Following Code\nint Integer = 24;\n" +
                "char String  = ‘I’;\n" +
                "System.out.print(Integer);\n" +
                "System.out.print(String);",
        "Q.5. Find the Output of the Following Program\n" +
                "public class Solution{\n" +
                "public static void main(String[] args){\n" +
                "short x = 10;\n" +
                "x =  x * 5;\n" +
                "System.out.print(x);\n" +
                "}\n" +
                "}",
        "Q.6. Select the Valid Statement.",
        "Q.7. Select the Valid Statement to declare and Initialize an Array.",
        "Q.8. Arrays in Java are-",
        "Q.9. java.util.Collections is a:",
        "Q.10. Methods such as reverse, shuffle are offered in:",
        "Q.11. Which of those allows duplicate elements?",
        "Q.12. Which allows the storage of a null key and null values?",
        "Q.13. Which interface should be implemented for sorting on basis of many criteria’s?",
        "Q.14. What guarantees type-safety in a collection?",
        "Q.15. HashSet internally uses?",
        "Q.16. The most used interfaces from the collection framework are?",
        "Q.17. The root interface of Java collection framework hierarchy is –",
        "Q.18. Which of those is synchronized?",
        "Q.19. ArrayList implements that of the following?",
        "Q.20. Which of those permits the storage of the many null values?",
        "Q.21. nextIndex() and previousIndex() are methods of which interface?",
        "Q.22. Vector extends that of these?",
        "Q.23. Which allows the removal of elements from a collection?",
        "Q.24. Which permits the removal of elements from a collection?",
        "Q.25. The Comparator interface contains the method?,"
    )


    private var answer = arrayOf(
        "8",            //1
        "32 and 64",
        "Int to Long",
        "24I",          //4
        "Compile Error",
        "char[] ch=new char[5]",    //6
        "int[] A={1,2,3}",
        "objects",
        "Class",        //9
        "Collections",
        "List",
        "HashMap",  //12
        "Comparator",
        "Generics",//14
        "Set",
        "Map",
        "List/Set",     //17
        "Vector",
        "All of the Above",
        "All of the Above",//20
        "Iterator",
        "AbstractList",
        "None of the Above",//23
        "Iterator",
        "compare()"     //25
    )

    private var options = arrayOf(
        "6",
        "7",
        "8",
        "9",              //1
        "32 and 64",
        "32 and 32",
        "64 and 64",
        "64 and 32",         //2
        "Byte to Int",
        "Int to Long",
        "Long to Int",
        "Short to Int",          //3
        "Throws Exception",
        "Compile Error",
        "I",
        "24I",              //4
        "50",
        "10",
        "Compile Error",
        "Exception",                //5
        "char[] ch=new char[5]",
        "char[] ch=new char(5)",
        "char[] ch=new char()",
        "char[] ch=new char[]",     //6
        "int[] A=(1,2,3)",
        "int[] A={1,2,3}",
        "int[][]={1,2,3}",
        "Primitive Data Type",      //7
        "Object Reference",
        "objects",
        "None",
        "Object",           //8
        "Class",
        "Interface",
        "Object",
        "None of the Above",        //9
        "Object",
        "Apache Commons Collection",
        "Commom Collection",
        "Collections",      //10
        "Set",
        "List",
        "All",
        "None of the Above",        //11
        "HashMap",
        "Hashtable",
        "Both",
        "None of the Above",        //12
        "Comparator",
        "Comparable",
        "Serializable",
        "None of the Above",        //13
        "Collection",
        "Interfaces",
        "Abstract classes",
        "Generics",      //14
        "List",
        "HashMap",
        "Set",
        "Collection",            //15
        "List",
        "Set",
        "Map",
        "Array",                    //16
        "List/Set",
        "Collection",
        "List",
        "Root",          //17
        "LinkedList",
        "Vector",
        "ArrayList",
        "None of the Above",        //18
        "List",
        "Random Access",
        "Cloneable",
        "All of the Above",         //19
        "Set",
        "List",
        "None of the Above",
        "All of the Above",     //20
        "ListIterator",
        "IndexIterator",
        "Iterator",
        "None of the Above",        //21
        "ArrayList",
        "AbstractList",
        "LinkedList",
        "None",                 //22
        "Enumeration",
        "Iterator",
        "Both",
        "None of the Above",        //23
        "Enumeration",
        "Iterator",
        "Both",
        "None of the Above",        //24
        "compare()",
        "compareWith",
        "compareTo()",
        "compare"           //25
    )

    @SuppressLint("Range")
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        activityJavaQuestion=ActivityJavaQuestionBinding.inflate(layoutInflater)
        setContentView(activityJavaQuestion.root)




        initViews()
    }

    //    @SuppressLint("SetTextI18n")
    @SuppressLint("SetTextI18n")
    private fun showNextQuestion()
    {

        checkAnswer()

        activityJavaQuestion.apply {

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
                val intent = Intent(this@JavaQuestion, JavaQuizResult::class.java)
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
    @SuppressLint("SetTextI18n")
    private fun checkAnswer()
    {

        activityJavaQuestion.apply {

            if (radiogrp.checkedRadioButtonId == -1)
            {
                skip++
                timeOverAlertDialog()       //error
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
    @SuppressLint("SetTextI18n")
    private fun initViews()
    {

        activityJavaQuestion.apply {

            tvQuestion.text = questions[qIndex]
            radioButton1.text = options[0]
            radioButton2.text = options[1]
            radioButton3.text = options[2]
            radioButton4.text = options[3]

            nextQuestionBtn.setOnClickListener {
                if (radiogrp.checkedRadioButtonId == -1)
                {
                    Toast.makeText(this@JavaQuestion,
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

                activityJavaQuestion.apply {

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
                showNextQuestion()      //error

            }
        }.start()
    }

    @SuppressLint("SetTextI18n")
    private fun correctAlertDialog()
    {
        val builder = AlertDialog.Builder(this@JavaQuestion)
        val view = LayoutInflater.from(this@JavaQuestion).inflate(R.layout.correctansdialog, null)
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
        val builder = AlertDialog.Builder(this@JavaQuestion)
        val view = LayoutInflater.from(this@JavaQuestion).inflate(R.layout.wrongansdialog, null)

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

    private fun timeOverAlertDialog()
    {
        val builder = AlertDialog.Builder(this@JavaQuestion)
        val view = LayoutInflater.from(this@JavaQuestion).inflate(R.layout.timerdialog, null)

        builder.setView(view)

        val timeOverOk = view.findViewById<Button>(R.id.timeOver_ok)
        var alertDialog = builder.create()

        timeOverOk.setOnClickListener {
            timeLeftMilliSeconds = countDownInMilliSecond

            statCountDownTimer()
            alertDialog.dismiss()
        }
        alertDialog.show()          //error
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