package com.example.enjayinterviewapp


import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.example.enjayinterviewapp.databinding.ActivityAptitudeQuestionBinding
import com.example.enjayinterviewapp.databinding.ActivityLogicalQuestionBinding

class LogicalQuestion : AppCompatActivity() , View.OnClickListener{
    private lateinit var binding: ActivityLogicalQuestionBinding
    private var mcurrentPosition:Int=1
    private var mquestionList:ArrayList<QuestionModel>?=null
    private var mSelectedOption:Int=0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityLogicalQuestionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mquestionList=LogicalConstants.getQuestion()
        setQuestion()

        val op1=findViewById<TextView>(R.id.tv_option1)
        val op2=findViewById<TextView>(R.id.tv_option2)
        val op3=findViewById<TextView>(R.id.tv_option3)
        val op4=findViewById<TextView>(R.id.tv_option4)
        val btn_nextQuestion=findViewById<Button>(R.id.btn_Logical)

        op1.setOnClickListener(this@LogicalQuestion)
        op2.setOnClickListener(this@LogicalQuestion)
        op3.setOnClickListener(this@LogicalQuestion)
        op4.setOnClickListener(this@LogicalQuestion)
        btn_nextQuestion.setOnClickListener(this@LogicalQuestion)

    }
    private fun setQuestion(){

        defaultOptionView()

        val btn_nextQuestion=findViewById<Button>(R.id.btn_Logical)

        if (mcurrentPosition==mquestionList!!.size){
            btn_nextQuestion.text="Finish"
        }else{
            btn_nextQuestion.text="Submit"
        }

        val questionModel = mquestionList!![mcurrentPosition -1]
        binding.logicalProgress.progress=mcurrentPosition
        binding.tvProgress.text="$mcurrentPosition"+"/"+binding.logicalProgress.max

        binding.Logicalquestion .text=questionModel!!.question
        binding.tvOption1.text=questionModel!!.option1
        binding.tvOption2.text=questionModel!!.option2
        binding.tvOption3.text=questionModel!!.option3
        binding.tvOption4.text=questionModel!!.option4

    }

    private fun defaultOptionView() {
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
            option.background= ContextCompat.getDrawable(
                this,R.drawable.questionbackground
            )
        }
    }

    override fun onClick(v: View?) {
        val op1=findViewById<TextView>(R.id.tv_option1)
        val op2=findViewById<TextView>(R.id.tv_option2)
        val op3=findViewById<TextView>(R.id.tv_option3)
        val op4=findViewById<TextView>(R.id.tv_option4)
        val btn_nextQuestion=findViewById<Button>(R.id.btn_Logical)


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
            R.id.btn_Logical ->
            {
                if (mSelectedOption == 0)
                {
                    mcurrentPosition++

                    when{
                        mcurrentPosition <= mquestionList!!.size -> {
                            setQuestion()
                        }else ->{
                        Toast.makeText(applicationContext,"completequiz", Toast.LENGTH_SHORT).show()
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
                        btn_nextQuestion.text="GO to Next Question"
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