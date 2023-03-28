package credentials

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatDelegate
import com.example.enjayinterviewapp.HomeActivity
import com.example.enjayinterviewapp.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference

class MainActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)





            Handler().postDelayed(


                {

                    auth = FirebaseAuth.getInstance()

                    if (auth.currentUser != null) {

                        startActivity(Intent(applicationContext, HomeActivity::class.java))
                        finish()
                    } else {

                        startActivity(Intent(this@MainActivity, login::class.java))
                        finish()
                    }
                }, 1000
            )

        }


}