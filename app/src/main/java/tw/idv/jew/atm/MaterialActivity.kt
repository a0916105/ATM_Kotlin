package tw.idv.jew.atm

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity

class MaterialActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_material)
        setSupportActionBar(findViewById(R.id.toolbar))

        findViewById<FloatingActionButton>(R.id.fab).setOnClickListener { view ->
            Snackbar.make(view, getString(R.string.snackbar_message), Snackbar.LENGTH_LONG)
//                    .setAction("Action", null).show()
                .setAction("Action"){
                    Log.d("Snackbar", "按下Action")
                }
                .setActionTextColor(Color.YELLOW)
                .show()
        }
    }
}