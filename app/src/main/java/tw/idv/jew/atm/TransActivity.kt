package tw.idv.jew.atm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.BufferedReader
import java.net.URL

class TransActivity : AppCompatActivity() {
    companion object {
        val TAG = TransActivity::class.java.simpleName
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_trans)

        CoroutineScope(Dispatchers.IO).launch {
            val reader = URL("https://atm201605.appspot.com/h")
                .openConnection()
                .getInputStream().bufferedReader()
            val json = reader.use(BufferedReader::readText)
            Log.d(TAG, json)
        }
    }
}