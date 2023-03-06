package Quiz

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import QuizQuestion.CQuestion
import com.example.enjayinterviewapp.R


class CQuiz : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cquiz)

        val start=findViewById<Button>(R.id.cquizstart)

        start.setOnClickListener {
            intent= Intent(applicationContext, CQuestion::class.java)
            startActivity(intent)
        }
    }
}