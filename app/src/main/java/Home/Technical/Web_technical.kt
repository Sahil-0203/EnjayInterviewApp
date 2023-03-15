package Home.Technical

import AdapterClass.CommomAdapterClass
import Home.SQLiteHelper
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ModelClass.CommomModelClass
import android.annotation.SuppressLint
import android.widget.ImageView
import com.example.enjayinterviewapp.R

class Web_technical : AppCompatActivity()
{
    @SuppressLint("Range", "MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_technical)



//        ----------------------------Back Button---------------------------------------------------\

        val back_web=findViewById<ImageView>(R.id.back_web)
        back_web.setOnClickListener {
            onBackPressed()
        }
//        ------------------------------------------------------------------------------------------


        val recyclerview = findViewById<RecyclerView>(R.id.recyclerview)

        recyclerview.layoutManager = LinearLayoutManager(this)

        val data = ArrayList<CommomModelClass>()

        val helper = SQLiteHelper(applicationContext)
        val db = helper.readableDatabase
        val query = "SELECT * FROM WebQuestion"
        val cursor1 = db?.rawQuery(query, null)

        while (cursor1?.moveToNext() == true) {
            val que = cursor1.getString(cursor1.getColumnIndex("question"))
            val ans = cursor1.getString(cursor1.getColumnIndex("answer"))


            data.add(CommomModelClass(que, ans))
            /*data.add(
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
*/
            val adapter = CommomAdapterClass(data)

            recyclerview.adapter = adapter
        }
    }
}