package Home

import AdapterClass.CommomAdapterClass
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ModelClass.CommomModelClass
import com.example.enjayinterviewapp.R

class CommonQuestion : AppCompatActivity()
{
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_common_question)

        val recyclerView=findViewById<RecyclerView>(R.id.recyclerview)

        recyclerView.layoutManager=LinearLayoutManager(this)


        val data=ArrayList<CommomModelClass>()

        data.add(CommomModelClass("1. Can you tell me a little about yourself?", R.string.number1))
        data.add(CommomModelClass("2. How did you hear about the position?", R.string.number2))
        data.add(CommomModelClass("3. Why did you apply for this position?", R.string.number3))
        data.add(CommomModelClass("4. Why should we hire you?", R.string.number4))
        data.add(
            CommomModelClass("5. Why are you looking to leave your current company?",
                R.string.number5
            )
        )
        data.add(
            CommomModelClass("6. What are your greatest professional strengths?",
                R.string.number6
            )
        )
        data.add(
            CommomModelClass("7. Tell me about a challenge or conflict you've faced at work and how you dealt with it.",
                R.string.number7
            )
        )
        data.add(CommomModelClass("8. How much money are you looking to earn?", R.string.number8))
        data.add(CommomModelClass("9. Why do you want to work here?", R.string.number9))
        data.add(CommomModelClass("10. What is your dream job?", R.string.number10))
        data.add(CommomModelClass("11. Why did you leave your last job?", R.string.number11))
        data.add(
            CommomModelClass("12. What other companies are you interviewing with?",
                R.string.number12
            )
        )
        data.add(CommomModelClass("13. What is your greatest weakness?", R.string.number13))
        data.add(
            CommomModelClass("14. What type of work environment do you prefer",
                R.string.number14
            )
        )
        data.add(
            CommomModelClass("15. What's a time you disagreed with a decision that was made at work?",
                R.string.number15
            )
        )
        data.add(CommomModelClass("16. Where do you see yourself in 5 years?", R.string.number16))
        data.add(
            CommomModelClass("17. Can you explain why you changed career paths?",
                R.string.number17
            )
        )
        data.add(CommomModelClass("18. Tell Me About a Time You Failed", R.string.number18))
        data.add(
            CommomModelClass("19. How would our boss and co-workers describe you?",
                R.string.number19
            )
        )
        data.add(
            CommomModelClass("20. How do you deal with pressure or stressful situations?",
                R.string.number20
            )
        )
        data.add(
            CommomModelClass("21. What do you think we could do better or differently?",
                R.string.number21
            )
        )
        data.add(CommomModelClass("22. What do you like to do outside of work?", R.string.number22))
        data.add(CommomModelClass("23. What are Your Strength?", R.string.number23))
        data.add(CommomModelClass("24. What are your salary requirements", R.string.number24))
        data.add(CommomModelClass("25. Do you have any questions for us?", R.string.number25))

        var adapter= CommomAdapterClass(data)

        recyclerView.adapter=adapter

    }
}