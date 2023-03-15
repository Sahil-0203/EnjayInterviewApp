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

class Ios_technical : AppCompatActivity() {
    @SuppressLint("Range", "MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ios_technical)


//        ----------------------------Back Button---------------------------------------------------\

        val back_ios=findViewById<ImageView>(R.id.back_ios)
        back_ios.setOnClickListener {
            onBackPressed()
        }
//        ------------------------------------------------------------------------------------------


        val recyclerView = findViewById<RecyclerView>(R.id.recyclerview)

        recyclerView.layoutManager = LinearLayoutManager(this)

        val data = ArrayList<CommomModelClass>()

        val helper = SQLiteHelper(applicationContext)
        val db = helper.readableDatabase
        val query = "SELECT * FROM IosQuestion"
        val cursor1 = db?.rawQuery(query, null)

        while (cursor1?.moveToNext() == true) {
            val que = cursor1.getString(cursor1.getColumnIndex("question"))
            val ans = cursor1.getString(cursor1.getColumnIndex("answer"))


            data.add(CommomModelClass(que, ans))
            /* data.add(CommomModelClass("1. What is ARC?", R.string.ios1))
        data.add(CommomModelClass("2. Define Bundle ID?", R.string.ios2))
        data.add(
            CommomModelClass("3. Name some important data types found in objective-C?",
                R.string.ios3
            )
        )
        data.add(CommomModelClass("4. Define Cocoa/Cocoa touch?", R.string.ios4))
        data.add(CommomModelClass("5. What are the methods to achieve concurrency?", R.string.ios5))
        data.add(
            CommomModelClass("6. When is an app said to be not running the state?",
                R.string.ios6
            )
        )

*/
            var adapter = CommomAdapterClass(data)
            recyclerView.adapter = adapter
        }
    }
}