package credentials

import InternetConnection.NetworkChangedListener
import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.example.enjayinterviewapp.HomeActivity
import com.example.enjayinterviewapp.R
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Handler().postDelayed(
            {

                auth = FirebaseAuth.getInstance()

                if (auth.currentUser != null) {

                    startActivity(Intent(applicationContext, HomeActivity::class.java))
                    finish()
                    }
                    else
                    {

                        startActivity(Intent(this@MainActivity, login::class.java))
                        finish()
                    }
                }, 1000
            )
    }
}