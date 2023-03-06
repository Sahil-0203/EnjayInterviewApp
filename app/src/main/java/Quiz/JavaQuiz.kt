package Quiz

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import QuizQuestion.JavaQuestion
import com.example.enjayinterviewapp.R

class JavaQuiz : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_java_quiz)

        val start=findViewById<Button>(R.id.javaquizstart)

        start.setOnClickListener {

            intent= Intent(this@JavaQuiz, JavaQuestion::class.java)
            startActivity(intent)


        }
    }
}