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

class Android_technical : AppCompatActivity()
{
    @SuppressLint("Range", "MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_android_technical)


//        ----------------------------Back Button---------------------------------------------------\

        val back_android=findViewById<ImageView>(R.id.back_android)
        back_android.setOnClickListener {
            onBackPressed()
        }
//        ------------------------------------------------------------------------------------------


        val recyclerView=findViewById<RecyclerView>(R.id.recyclerview)

        recyclerView.layoutManager=LinearLayoutManager(this)

        val data=ArrayList<CommomModelClass>()

        val helper = SQLiteHelper(applicationContext)
        val db = helper.readableDatabase
        val query = "SELECT * FROM AndroidQuestion"
        val cursor1 = db?.rawQuery(query, null)

        while (cursor1?.moveToNext() == true) {
            val que = cursor1.getString(cursor1.getColumnIndex("question"))
            val ans = cursor1.getString(cursor1.getColumnIndex("answer"))


            data.add(CommomModelClass(que, ans))
      /*  data.add(CommomModelClass("1. What is Android?", R.string.android1))
        data.add(
            CommomModelClass("2. Official Programming Language used to build Android Application?",
                R.string.android2
            )
        )
        data.add(CommomModelClass("3. What is an activity?", R.string.android3))
        data.add(CommomModelClass("4. What is a service in Android?", R.string.android4))
        data.add(
            CommomModelClass("5. What are the core building blocks of android?",
                R.string.android5
            )
        )
        data.add(
            CommomModelClass("6. What are the life cycle methods of android activity?",
                R.string.android6
            )
        )
        data.add(CommomModelClass("7. What is intent?", R.string.android7))
*/

        var adapter= CommomAdapterClass(data)

        recyclerView.adapter=adapter
    }
}
}