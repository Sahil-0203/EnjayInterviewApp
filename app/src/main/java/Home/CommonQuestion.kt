package Home

import AdapterClass.CommomAdapterClass
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ModelClass.CommomModelClass
import android.annotation.SuppressLint
import android.widget.ImageView
import com.example.enjayinterviewapp.R

@Suppress("DEPRECATION")
class CommonQuestion : AppCompatActivity()
{
private var itemvalue:Int=0
    @SuppressLint("Range", "MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_common_question)

//        ----------------------------Back Button---------------------------------------------------

        val back_common=findViewById<ImageView>(R.id.common_Back)
        back_common.setOnClickListener {
            onBackPressed()
        }
//        ------------------------------------------------------------------------------------------

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerview)

        recyclerView.layoutManager = LinearLayoutManager(this)


        val data = ArrayList<CommomModelClass>()

        val helper = SQLiteHelper(applicationContext)
        val db = helper.readableDatabase
        val query = "SELECT * FROM cmnQuestion"
        val cursor1 = db?.rawQuery(query, null)

        while (cursor1?.moveToNext() == true) {
            val que = cursor1.getString(cursor1.getColumnIndex("question"))
            val ans = cursor1.getString(cursor1.getColumnIndex("answer"))


            data.add(CommomModelClass(que, ans))

            var adapter = CommomAdapterClass(data)

            recyclerView.adapter = adapter

        }
    }
}