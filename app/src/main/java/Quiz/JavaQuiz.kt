package Quiz

import Home.SQLiteHelper
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import QuizQuestion.JavaQuestion
import android.content.ContentValues
import com.example.enjayinterviewapp.R
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class JavaQuiz : AppCompatActivity() {
    private lateinit var database: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_java_quiz)


        database = FirebaseDatabase.getInstance().getReference("JavaQuiz")


//        ----------------------- Start Java Quiz Question---------------------------------------------------
        database.get().addOnSuccessListener {
            for (DataSnap in it.children) {

                val questionReal = DataSnap.child("question").value.toString()
                val option1Real = DataSnap.child("option1").value.toString()
                val option2Real = DataSnap.child("option2").value.toString()
                val option3Real = DataSnap.child("option3").value.toString()
                val option4Real = DataSnap.child("option4").value.toString()
                val answerReal = DataSnap.child("answer").value.toString()


//                --------------------------Java question---------------------------------------


//      store value in sqlite-----------------------------------------------------------------------

                val helper = SQLiteHelper(applicationContext)
                val db = helper.readableDatabase

                var cursor = db.rawQuery(
                    "SELECT * FROM JavaQuestion WHERE question =?",
                    arrayOf(questionReal.toString())
                )
                val exists = cursor.moveToNext()
                cursor.close()

                if (exists) {
//----------------------------value existing-----------------------
                } else {
                    var cv = ContentValues()
                    cv.put("question", questionReal)
                    cv.put("option1", option1Real)
                    cv.put("option2", option2Real)
                    cv.put("option3", option3Real)
                    cv.put("option4", option4Real)
                    cv.put("answer", answerReal)
                    db.insert("JavaQuestion", null, cv)


//  -----------------------finish sqlite store-----------------------------------------------------
                }
            }
//        -----------------------finish Java-----------------------------------------------------


//------------------------------------------------------------------------------------------------
            val start = findViewById<Button>(R.id.javaquizstart)

            start.setOnClickListener {

                intent = Intent(this@JavaQuiz, JavaQuestion::class.java)
                startActivity(intent)


            }
        }
    }
}