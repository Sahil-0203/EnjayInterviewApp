package Quiz

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.example.enjayinterviewapp.R

class QuizFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_quiz, container, false)

        var imageView1: ImageView = view.findViewById(R.id.javaimage)

        imageView1.setOnClickListener {

            Log.d("clicked", "reached")
            val intent= Intent(activity, JavaQuiz::class.java)
            startActivity(intent)
        }

        var imageView2: ImageView = view.findViewById(R.id.pythonimg)

        imageView2.setOnClickListener {

            Log.d("clicked", "reached")
            val intent= Intent(activity, PythonQuiz::class.java)
            startActivity(intent)
        }
        var imageView3: ImageView = view.findViewById(R.id.phpimg)

        imageView3.setOnClickListener {

            val intent= Intent(activity, PhpQuiz::class.java)
            startActivity(intent)
        }
        var imageView4: ImageView = view.findViewById(R.id.kotlinimg)

        imageView4.setOnClickListener {

            val intent= Intent(activity, KotlinQuiz::class.java)
            startActivity(intent)
        }
        var imageView5: ImageView = view.findViewById(R.id.cplusimg)

        imageView5.setOnClickListener {

            val intent= Intent(activity, CplusQuiz::class.java)
            startActivity(intent)
        }
        var imageView6: ImageView = view.findViewById(R.id.cimg)

        imageView6.setOnClickListener {

            val intent= Intent(activity, CQuiz::class.java)
            startActivity(intent)
        }

        return view
    }
}