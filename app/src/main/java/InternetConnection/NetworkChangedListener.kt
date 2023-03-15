package InternetConnection

import android.app.AlertDialog
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.view.Gravity
import android.view.LayoutInflater
import androidx.appcompat.widget.AppCompatButton
import com.example.enjayinterviewapp.R

class NetworkChangedListener : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        if (!Common.isConnectedToInternet(context)) {
            val builder = AlertDialog.Builder(context)
            val layout_dialog = LayoutInflater.from(context).inflate(R.layout.check_network, null)
            builder.setView(layout_dialog)
            val retry = layout_dialog.findViewById<AppCompatButton>(R.id.retry)
            val dialog = builder.create()
            dialog.show()
            dialog.setCancelable(false)
            dialog.window!!.setGravity(Gravity.CENTER)
            retry.setOnClickListener {
                dialog.dismiss()
                onReceive(context, intent)
            }
        }
    }
}