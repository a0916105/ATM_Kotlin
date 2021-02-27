package tw.idv.jew.atm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.KotlinModule
import com.fasterxml.jackson.module.kotlin.readValue
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import okhttp3.Request
import org.json.JSONArray
import java.io.BufferedReader
import java.net.URL

class TransActivity : AppCompatActivity() {
    companion object {
        val TAG = TransActivity::class.java.simpleName
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_trans)

        val client = OkHttpClient.Builder()
            .build()
        val request = Request.Builder()
            .url("https://atm201605.appspot.com/h")
            .build()

        CoroutineScope(Dispatchers.IO).launch {
            /*val reader = URL("https://atm201605.appspot.com/h")
                .openConnection()
                .getInputStream().bufferedReader()
            val json = reader.use(BufferedReader::readText)
            Log.d(TAG, json)*/
            var response = client.newCall(request).execute()
            response.body?.run {
//                Log.d(TAG, string())
                val json = string()
//                parseJSON(json)
//                parseGSON(json)
                parseJackson(json)
            }
        }
    }

    private fun parseJSON(json: String) {
        val trans = mutableListOf<Transaction>()
        val array = JSONArray(json)
        for (i in 0 until array.length()) {
            val obj = array.getJSONObject(i)
            val account = obj.getString("account")
            val date = obj.getString("date")
            val amount = obj.getInt("amount")
            val type = obj.getInt("type")
            val t = Transaction(account, date, amount, type)
            Log.d(TAG, t.toString())
            trans.add(t)
        }
    }

    private fun parseGSON(json: String) {
        val gson = Gson()
        val trans = gson.fromJson<ArrayList<Transaction>>(json,
                object : TypeToken<ArrayList<Transaction>>(){}.type)
        trans.forEach { t ->
            Log.d(TAG, t.toString())
        }
    }

    private fun parseJackson(json: String) {
        val mapper = ObjectMapper().registerModule(KotlinModule())
        val trans : List<Transaction> = mapper.readValue(json)
        trans.forEach { t ->
            Log.d(TAG, t.toString())
        }
    }
}