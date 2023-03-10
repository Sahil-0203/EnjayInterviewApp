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


        if (!isNetworkAvailable == true) {
            AlertDialog.Builder(this)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle("Internet Connection Alert")
                .setMessage("Please Check Your Internet Connection")
                .setPositiveButton(
                    "Close"
                ) { dialogInterface, i -> finish() }.show()
        } else if (isNetworkAvailable == true) {




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

    val isNetworkAvailable: Boolean
        get() {
            val connectivityManager =
                getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            if (connectivityManager != null) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                    val capabilities =
                        connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
                    if (capabilities != null) {
                        if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) {
                            return true
                        } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {
                            return true
                        } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)) {
                            return true
                        }
                    }
                }
            }
            return false
        }
}