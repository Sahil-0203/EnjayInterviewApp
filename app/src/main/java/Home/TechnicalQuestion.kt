package Home


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.cardview.widget.CardView
import Home.Technical.Android_technical
import Home.Technical.Ios_technical
import com.example.enjayinterviewapp.R
import Home.Technical.Web_technical
import InternetConnection.NetworkChangedListener
import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.widget.ImageView
import com.example.enjayinterviewapp.HomeActivity
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class TechnicalQuestion : AppCompatActivity() {
    private lateinit var database1:DatabaseReference
    private lateinit var database2:DatabaseReference
    private lateinit var database3:DatabaseReference


    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_technical_question)

//        ----------------------------Back Button---------------------------------------------------\

        val back_technical=findViewById<ImageView>(R.id.back_tech)
        back_technical.setOnClickListener {
            onBackPressed()
        }
//        ------------------------------------------------------------------------------------------


        getQuestion()
        val android=findViewById<CardView>(R.id.android)
        val ios=findViewById<CardView>(R.id.ios)
        val web=findViewById<CardView>(R.id.web)


        android.setOnClickListener {
            startActivity(Intent(this@TechnicalQuestion, Android_technical::class.java))
        }

        ios.setOnClickListener {
            startActivity(Intent(this@TechnicalQuestion, Ios_technical::class.java))
        }

        web.setOnClickListener {
            startActivity(Intent(this@TechnicalQuestion, Web_technical::class.java))
        }
    }

    private fun getQuestion() {
        database1=FirebaseDatabase.getInstance().getReference("AndroidQuestion")
        database2=FirebaseDatabase.getInstance().getReference("IosQuestion")
        database3=FirebaseDatabase.getInstance().getReference("WebQuestion")


//        ------------------------start AndroidQuestion----------------------------------------------
        database1.get().addOnSuccessListener {
            for (DataSnap in it.children ){

//                Log.e("shhhhhhhh", "---------->>$it ", )
                val questionReal1=DataSnap.child("question").value.toString()
                val answerReal1=DataSnap.child("answer").value.toString()




//      store value in sqlite-----------------------------------------------------------------------

                val helper1=SQLiteHelper(applicationContext)
                val db1=helper1.readableDatabase
//                Log.e("1234", "=====>$db2", )

                var cursor1=db1.rawQuery("SELECT * FROM AndroidQuestion WHERE question =?", arrayOf(questionReal1.toString()))
                val exists1=cursor1.moveToNext()
                cursor1.close()

                if (exists1){
//----------------------------value existing-----------------------
                }else {
                    var cv1 = ContentValues()
                    cv1.put("question", questionReal1)
                    cv1.put("answer",answerReal1)
                    db1.insert("AndroidQuestion", null, cv1)

                }
//  -----------------------finish sqlite store-----------------------------------------------------
            }
        }

//        -------------------------end AndroidQuestion----------------------------------------------

//        ------------------------start IosQuestion----------------------------------------------
        database2.get().addOnSuccessListener {
            for (DataSnap in it.children ){

//                Log.e("shhhhhhhh", "---------->>$it ", )
                val questionReal2=DataSnap.child("question").value.toString()
                val answerReal2=DataSnap.child("answer").value.toString()




//      store value in sqlite-----------------------------------------------------------------------

                val helper2=SQLiteHelper(applicationContext)
                val db2=helper2.readableDatabase
//                Log.e("1234", "=====>$db2", )

                var cursor2=db2.rawQuery("SELECT * FROM IosQuestion WHERE question =?", arrayOf(questionReal2.toString()))
                val exists2=cursor2.moveToNext()
                cursor2.close()

                if (exists2){
//----------------------------value existing-----------------------
                }else {
                    var cv2 = ContentValues()
                    cv2.put("question", questionReal2)
                    cv2.put("answer",answerReal2)
                    db2.insert("IosQuestion", null, cv2)

                }
//  -----------------------finish sqlite store-----------------------------------------------------
            }
        }

//        -------------------------end IosQuestion--------------------------------------------------

//        ------------------------start WebQuestion----------------------------------------------
        database3.get().addOnSuccessListener {
            for (DataSnap in it.children ){

//                Log.e("shhhhhhhh", "---------->>$it ", )
                val questionReal3=DataSnap.child("question").value.toString()
                val answerReal3=DataSnap.child("answer").value.toString()




//      store value in sqlite-----------------------------------------------------------------------

                val helper3=SQLiteHelper(applicationContext)
                val db3=helper3.readableDatabase
//                Log.e("1234", "=====>$db2", )

                var cursor3=db3.rawQuery("SELECT * FROM WebQuestion WHERE question =?", arrayOf(questionReal3.toString()))
                val exists3=cursor3.moveToNext()
                cursor3.close()

                if (exists3){
//----------------------------value existing-----------------------
                }else {
                    var cv3 = ContentValues()
                    cv3.put("question", questionReal3)
                    cv3.put("answer",answerReal3)
                    db3.insert("WebQuestion", null, cv3)

                }
//  -----------------------finish sqlite store-----------------------------------------------------
            }
        }

//        -------------------------end WebQuestion-----------------------------------------------

    }
}