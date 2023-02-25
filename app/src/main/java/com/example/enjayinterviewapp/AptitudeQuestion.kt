package com.example.enjayinterviewapp

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.enjayinterviewapp.databinding.ActivityAptitudeQuestionBinding

class AptitudeQuestion : AppCompatActivity(), View.OnClickListener {
   private lateinit var binding: ActivityAptitudeQuestionBinding
   private var mcurrentPosition:Int=1
    private var mquestionList:ArrayList<QuestionModel>?=null
    private var mSelectedOption:Int=0
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityAptitudeQuestionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mquestionList=Constants.getQuestions()

        setQuestion()

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


    }
    private fun setQuestion(){

        defaultOptionView()

        val btn_nextQuestion=findViewById<Button>(R.id.btn_Aptitude)

        if (mcurrentPosition==mquestionList!!.size){
            btn_nextQuestion.text="Finish"
        }else{
            btn_nextQuestion.text="Submit"
        }

        val questionModel = mquestionList!![mcurrentPosition -1]
        binding.aptitudeProgress.progress=mcurrentPosition
        binding.tvProgress.text="$mcurrentPosition"+"/"+binding.aptitudeProgress.max

        binding.Aptitudequestion.text=questionModel!!.question
        binding.tvOption1.text=questionModel!!.option1
        binding.tvOption2.text=questionModel!!.option2
        binding.tvOption3.text=questionModel!!.option3
        binding.tvOption4.text=questionModel!!.option4

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
                option.setTextColor(Color.parseColor("#FFFFFF"))
                option.typeface= Typeface.DEFAULT
            option.background=ContextCompat.getDrawable(
                this,R.drawable.questionbackground
            )
        }
    }

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
                if (mSelectedOption == 0)
                {
                    mcurrentPosition++

                    when{
                        mcurrentPosition <= mquestionList!!.size -> {
                            setQuestion()
                        }else ->{

                        val intent= Intent(this@AptitudeQuestion, HomeActivity::class.java)
                        startActivity(intent)


                        }
                    }
                }else{
                    var question = mquestionList?.get(mcurrentPosition -1)
                    if (question!!.answer != mSelectedOption){
                        answerView(mSelectedOption,R.drawable.wrong_bg)
                    }
                    answerView(question.answer,R.drawable.right_bg)

                    if (mcurrentPosition ==mquestionList!!.size){
                        btn_nextQuestion.text="Finish"
                    }else{
                        btn_nextQuestion.text="GO to Nex\t Question"
                    }
                    mSelectedOption =0
                }
            }

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
            this,R.drawable.selected_option_border
        )
    }
}