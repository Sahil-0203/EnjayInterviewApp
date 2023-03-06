package Result

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Paint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.example.enjayinterviewapp.HomeActivity
import com.example.enjayinterviewapp.R
import com.example.enjayinterviewapp.databinding.ActivityJavaQuizResultBinding

class JavaQuizResult : AppCompatActivity()
{
    private lateinit var activityJavaQuizResult: ActivityJavaQuizResultBinding

    var totalScore = 0
    var correct = 0
    var wrong = 0
    var skip = 0
    var isKey = false

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        activityJavaQuizResult = ActivityJavaQuizResultBinding.inflate(layoutInflater)
        setContentView(activityJavaQuizResult.root)

        totalScore = intent.extras!!.getInt("correct")
        wrong = intent.extras!!.getInt("wrong")
        skip = intent.extras!!.getInt("skip")

        val home=findViewById<TextView>(R.id.home)
        val output=findViewById<TextView>(R.id.output)

        output.setPaintFlags(Paint.UNDERLINE_TEXT_FLAG)


        home.setOnClickListener {
            val intent= Intent(this@JavaQuizResult, HomeActivity::class.java)
            startActivity(intent)
        }

        initializeViews()
    }

    @SuppressLint("SetTextI18n")
    private fun initializeViews()
    {
        activityJavaQuizResult.apply {
            tvScore.text = "Score: $totalScore"
            tvright.text = "Correct: $totalScore"
            tvwrong.text = "Wrong: $wrong"
            tvSkip.text = "Skip: $skip"

            if (totalScore == 25)
            {
                activityJavaQuizResult.emojiReactionImg.setImageResource(R.drawable.excellentemoji)
                output.setText("EXCELLENT!")
            }
            else if (totalScore>=21)
            {
                activityJavaQuizResult.emojiReactionImg.setImageResource(R.drawable.impressiveemoji)
                output.setText("IMPRESSIVE!")
            }
            else if (totalScore>=13)
            {
                activityJavaQuizResult.emojiReactionImg.setImageResource(R.drawable.goodemoji)
                output.setText("QUITE GOOD")
            }
            else if (totalScore>=9)
            {
                activityJavaQuizResult.emojiReactionImg.setImageResource(R.drawable.needimprovementemoji)
                output.setText("NEED IMPROVEMENT")
            }
            else
            {
                emojiReactionImg.setImageResource(R.drawable.pooremoji)
                output.setText("POOR")
            }
            tvPlayAgain.setOnClickListener{
                finish()
            }
        }
    }
}