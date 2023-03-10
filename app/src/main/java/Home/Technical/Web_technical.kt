package Home.Technical

import AdapterClass.CommomAdapterClass
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ModelClass.CommomModelClass
import com.example.enjayinterviewapp.R

class Web_technical : AppCompatActivity()
{
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_technical)

        val recyclerview=findViewById<RecyclerView>(R.id.recyclerview)

        recyclerview.layoutManager=LinearLayoutManager(this)

        val data=ArrayList<CommomModelClass>()

        data.add(
            CommomModelClass("1. What are the prerequisites of being a web developer?",
                R.string.web1
            )
        )
        data.add(
            CommomModelClass("2. Enlist the advantages of HTTP/2 as compared with HTTP 1.1/.",
                R.string.web2
            )
        )
        data.add(CommomModelClass("3. What is HTML?", R.string.web3))
        data.add(CommomModelClass("4. What are Tags?", R.string.web4))
        data.add(CommomModelClass("5. What is CSS?", R.string.web5))
        data.add(CommomModelClass("6. What is the origin of CSS?", R.string.web6))

        val adapter= CommomAdapterClass(data)

        recyclerview.adapter=adapter
    }
}